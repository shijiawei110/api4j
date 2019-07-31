package com.sjw.api4j.helper;

import com.google.common.collect.Lists;
import com.sjw.api4j.model.*;
import com.sjw.api4j.utils.SysLogUtil;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
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
            ApiTagClass apiTagClass = new ApiTagClass(AnnotationHelper.getClassPath(jc, commonPath), jc.getComment());
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
    public static void analyMethods(ApiTagClass apiTagClass) {
        String basePath = apiTagClass.getPath();
        //类注释暂时用不到
        String note = apiTagClass.getNote();
        List<ApiTagMethod> apiTagMethods = apiTagClass.getMethods();
        apiTagMethods.stream().forEach(apiTagMethod -> analyMethod(apiTagMethod, basePath));
    }

    private static void analyMethod(ApiTagMethod apiTagMethod, String basePath) {
        ApiMethodInfo apiMethodInfo = new ApiMethodInfo();
        JavaMethod javaMethod = apiTagMethod.getJavaMethod();
        apiMethodInfo.setAuthor(apiTagMethod.getAuthor());
        apiMethodInfo.setNote(apiTagMethod.getNote());
        apiMethodInfo.setTagValue(apiTagMethod.getTagValue());
        //获取http类型
        apiMethodInfo.setHttpTypeEnum(AnnotationHelper.getMethodHttpType(javaMethod));
        //获取path
        apiMethodInfo.setPath(basePath + AnnotationHelper.getMethodPath(javaMethod));
        //分析入参
        apiMethodInfo.setInputParams(analyInput(javaMethod, apiMethodInfo.getHttpTypeEnum().isGet()));
        //分析出参

        System.out.println(apiMethodInfo);
    }


    private static List<BaseParamInfo> analyInput(JavaMethod javaMethod, boolean isGet) {
        List<JavaParameter> parameters = javaMethod.getParameters();
        List<BaseParamInfo> result = Lists.newArrayList();
        for (JavaParameter parameter : parameters) {
            BaseParamInfo baseParamInfo = new BaseParamInfo();
            if (isGet) {
                //获取name
                baseParamInfo.setName(getInputParamName(parameter));
                //获取参数类型
                baseParamInfo.setType(parameter.getFullyQualifiedName());
                //get请求没有参数描述
                baseParamInfo.setDesc(baseParamInfo.getName());
                //查询是否必须
                baseParamInfo.setRequired(AnnotationHelper.reqGetRequired(parameter.getAnnotations()));
            } else {

            }
            result.add(baseParamInfo);
        }
        return result;
    }


    /**
     * get请求获取别名
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


}
