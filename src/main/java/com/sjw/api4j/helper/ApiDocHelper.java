package com.sjw.api4j.helper;

import com.google.common.collect.Lists;
import com.sjw.api4j.model.*;
import com.sjw.api4j.utils.StringPool;
import com.sjw.api4j.utils.SysLogUtil;
import com.thoughtworks.qdox.model.*;
import com.thoughtworks.qdox.model.impl.DefaultJavaParameterizedType;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author shijiawei
 * @version ApiDocHelper.java -> v 1.0
 * @date 2019/7/28
 */
public class ApiDocHelper {

    public static List<ApiTagClass> getAllMethods(String commonPath) {
        long start = System.currentTimeMillis();

        String rootPath = FileHelper.getRootPath();
        Collection<JavaClass> classes = FileHelper.getAllClassses(rootPath);
        List<ApiTagClass> result = Lists.newArrayList();

        for (JavaClass jc : classes) {
            ApiTagClass apiTagClass = new ApiTagClass(AnnotationHelper.getClassPath(jc, commonPath), jc.getComment(), jc.getName());
            boolean isApiClass = false;
            List<JavaMethod> methods = jc.getMethods();
            //首先判断class是否标记
            List<JavaAnnotation> anns = jc.getAnnotations();
            ApiTagPojo apiTagPojo = AnnotationHelper.isApiTag(anns);
            if (null != apiTagPojo) {
                isApiClass = true;
                for (JavaMethod p : methods) {
                    ApiTagMethod apiTagMethod = new ApiTagMethod(p, apiTagPojo);
                    apiTagClass.addMethods(apiTagMethod);
                }
            } else {
                //其次判断方法是否标记
                for (JavaMethod p : methods) {
                    List<JavaAnnotation> mAnns = p.getAnnotations();
                    ApiTagPojo apiTagPojoMethod = AnnotationHelper.isApiTag(mAnns);
                    if (null != apiTagPojoMethod) {
                        isApiClass = true;
                        ApiTagMethod apiTagMethod = new ApiTagMethod(p, apiTagPojoMethod);
                        apiTagClass.addMethods(apiTagMethod);
                    }
                }
            }
            if (isApiClass) {
                result.add(apiTagClass);
            }

        }

        SysLogUtil.duration("read all java class and methods", start);
        return result;
    }

    /**
     * 解析每个方法
     */
    public static List<ApiMethodInfo> analyMethods(ApiTagClass apiTagClass) {
        String basePath = apiTagClass.getPath();
        //类注释暂时用不到
        String note = apiTagClass.getNote();
        List<ApiTagMethod> apiTagMethods = apiTagClass.getMethods();
        List<ApiMethodInfo> apiMethodInfos = Lists.newArrayList();
        apiTagMethods.stream().forEach(apiTagMethod -> analyMethod(apiTagMethod, basePath, apiMethodInfos));
        return apiMethodInfos;
    }

    private static void analyMethod(ApiTagMethod apiTagMethod, String basePath, List<ApiMethodInfo> apiMethodInfos) {
        try {
            ApiMethodInfo apiMethodInfo = new ApiMethodInfo(apiTagMethod);
            JavaMethod javaMethod = apiTagMethod.getJavaMethod();
            //获取http类型
            apiMethodInfo.setHttpTypeEnum(AnnotationHelper.getMethodHttpType(javaMethod));
            //获取path
            apiMethodInfo.setPath(basePath + AnnotationHelper.getMethodPath(javaMethod));
            //分析入参
            apiMethodInfo.setInputParams(analyInput(javaMethod, apiMethodInfo.getHttpTypeEnum().isGet()));
            //分析出参
            apiMethodInfo.setOutputParams(analyOutput(javaMethod));
            apiMethodInfos.add(apiMethodInfo);
        } catch (Exception e) {
            SysLogUtil.info("分析方法 {0} 失败 -> msg = {1}", apiTagMethod.getJavaMethod().getName(), e.getMessage());
        }
    }


    private static BaseParams analyInput(JavaMethod javaMethod, boolean isGet) {
        List<JavaParameter> parameters = javaMethod.getParameters();
        BaseParams result = new BaseParams();
        for (JavaParameter parameter : parameters) {
            //判断是否需要去除的参数 (比如servelt这种)
            if (ClassNameHelper.isNeedExcludeInputParam(parameter)) {
                continue;
            }
            BaseParams param = new BaseParams();
            if (isGet) {
                //获取name
                param.setName(getInputParamName(parameter));
                //获取参数类型
                param.setType(parameter.getFullyQualifiedName());
                //get请求没有参数描述
                param.setDesc(param.getName());
                //查询是否必须
                param.setRequired(AnnotationHelper.reqGetRequired(parameter.getAnnotations()));
                result.addChild(param);
            } else {
                result = commonGetParams(parameter.getJavaClass(), null);
            }
        }
        return result;
    }

    private static BaseParams analyOutput(JavaMethod javaMethod) {
        JavaClass javaClass = javaMethod.getReturns();
        String fullName = javaClass.getFullyQualifiedName();
        //判断空返回
        if (ClassNameHelper.isVoid(fullName)) {
            return null;
        }
        return commonGetParams(javaClass, null);
    }

    /**
     * 标准统一递归遍历出参
     */
    private static BaseParams commonGetParams(JavaClass javaClass, JavaField javaField) {
        BaseParams result;
        String fullName = javaClass.getFullyQualifiedName();

        //判断java基本型
        if (ClassNameHelper.isJavaBaseType(fullName) || ClassNameHelper.isJavaLang(fullName)) {
            result = BaseParams.newjavaType(fullName, getFieldName(javaField), getFieldComment(javaField));
            return result;
        }

        //判断是否为java.util集合类型
        if (ClassNameHelper.isJavaUtil(fullName)) {
            //取泛型实际类
            JavaClass actualClass = (JavaClass) ((DefaultJavaParameterizedType) javaClass).getActualTypeArguments().get(0);
            String actualClassName = actualClass.getFullyQualifiedName();
            if (ClassNameHelper.isJavaBaseType(actualClassName) || ClassNameHelper.isJavaLang(actualClassName)) {
                result = BaseParams.newjavaTypeList(actualClassName, getFieldName(javaField), getFieldComment(javaField));
                return result;
            } else {
                //为List<自定义类>
                result = BaseParams.newCustomTypeList(actualClassName, getFieldName(javaField), getFieldComment(javaField));
                result.setDesc(getFieldComment(javaField));
                //对自定义类进行拆解递归分析
                List<JavaField> fields = actualClass.getFields();
                for (JavaField field : fields) {
                    if (ClassNameHelper.isExcludeField(field.getName())) {
                        //排除序列化id属性
                        continue;
                    }
                    result.addChild(commonGetParams(field.getType(), field));
                }
                return result;
            }
        } else {
            //为自定义类
            result = BaseParams.newCustomType(fullName, getFieldName(javaField), getFieldComment(javaField));
            result.setDesc(getFieldComment(javaField));
            //对自定义类进行拆解递归分析
            List<JavaField> fields = javaClass.getFields();
            for (JavaField field : fields) {
                //排除序列化id属性
                if (ClassNameHelper.isExcludeField(field.getName())) {
                    continue;
                }
                //排除类变量
                if (field.isStatic()) {
                    continue;
                }
                result.addChild(commonGetParams(field.getType(), field));
            }
            return result;
        }

    }


    /**
     * get入参请求获取参数名
     */
    private static String getInputParamName(JavaParameter parameter) {
        //获取别名(存在的情况下)
        String aliasName = AnnotationHelper.reqGetAliasName(parameter.getAnnotations());
        if (StringUtils.isNotBlank(aliasName)) {
            return aliasName;
        }
        //获取真名
        return parameter.getName();
    }

    /**
     * 获取成员变量参数名
     */
    private static String getFieldName(JavaField javaField) {
        if (null == javaField) {
            return null;
        }
        //获取别名(存在的情况下)
        String aliasName = AnnotationHelper.paramsAliasName(javaField.getAnnotations());
        if (StringUtils.isNotBlank(aliasName)) {
            return aliasName;
        }
        //获取真名
        return javaField.getName();
    }

    /**
     * 获取成员变量注释
     */
    private static String getFieldComment(JavaField javaField) {
        if (null == javaField) {
            return StringPool.EMPTY;
        }
        return javaField.getComment();
    }

    /**
     * 获取成员变量是否必须 todo:待完成
     */
    private static boolean getFieldIsMust(JavaField javaField) {
        if (null == javaField) {
            return false;
        }
        return false;
    }


}
