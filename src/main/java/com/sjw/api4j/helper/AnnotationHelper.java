package com.sjw.api4j.helper;

import com.sjw.api4j.enums.ApiTagParamsEnum;
import com.sjw.api4j.enums.HttpTypeEnum;
import com.sjw.api4j.model.ApiTagPojo;
import com.sjw.api4j.model.LengthLimitInfoPojo;
import com.sjw.api4j.utils.StringPool;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author shijiawei
 * @version AnnotationHelper.java -> v 1.0
 * @date 2019/7/29
 */
public class AnnotationHelper {

    private static final String API_TAG_NAME = "com.sjw.api4j.annotation.ApiTag";

    private static final String WS_RS_PATH = "javax.ws.rs.Path";
    private static final String WS_RS_PATH_VALUE = "value";
    private static final String WS_RS_GET = "javax.ws.rs.GET";
    private static final String WS_RS_POST = "javax.ws.rs.POST";
    private static final String WS_RS_QUERY_PARAM = "javax.ws.rs.QueryParam";
    private static final String WS_RS_QUERY_VALUE = "value";

    private static final String MVC_PATH = "org.springframework.web.bind.annotation.RequestMapping";
    private static final String MVC_PATH_VALUE = "value";
    private static final String MVC_PATH_METHOD = "method";

    private static final String MVC_GET_PATH = "org.springframework.web.bind.annotation.GetMapping";
    private static final String MVC_POST_PATH = "org.springframework.web.bind.annotation.PostMapping";

    private static final String MVC_REQUEST_METHOD_GET = "RequestMethod.GET";
    private static final String MVC_REQUEST_METHOD_POST = "RequestMethod.post";
    private static final String MVC_REQUEST_PARAM = "org.springframework.web.bind.annotation.RequestParam";
    private static final String MVC_REQUEST_VALUE = "value";
    private static final String MVC_REQUEST_REQUIRED = "required";

    private static final String JACKSON_JSONPROPERTY = "com.fasterxml.jackson.annotation.JsonProperty";
    private static final String JACKSON_JSONPROPERTY_VALUE = "value";

    private static final String JAVAX_NOT_BLANK = "javax.validation.constraints.NotBlank";
    private static final String JAVAX_NOT_NULL = "javax.validation.constraints.NotNull";
    private static final String JAVAX_NOT_EMPTY = "javax.validation.constraints.NotEmpty";
    private static final String HIBERNATE_NOT_BLANK = "org.hibernate.validator.constraints.NotBlank";
    private static final String HIBERNATE_NOT_EMPTY = "org.hibernate.validator.constraints.NotEmpty";

    private static final String JAVAX_MAX = "javax.validation.constraints.Max";
    private static final String JAVAX_MIN = "javax.validation.constraints.Min";
    private static final String JAVAX_SIZE = "javax.validation.constraints.Size";
    private static final String HIBERNATE_LENGTH = "org.hibernate.validator.constraints.Length";
    private static final String JAVAX_MAX_MIN_VALUE = "value";
    private static final String JAVAX_SIZE_MIN = "min";
    private static final String JAVAX_SIZE_MAX = "max";
    private static final String HIBERNATE_LENGTH_MIN = "min";
    private static final String HIBERNATE_LENGTH_MAX = "max";


    /**
     * 是否标记 apiTag
     */
    public static ApiTagPojo isApiTag(List<JavaAnnotation> javaAnnotations) {
        if (CollectionUtils.isEmpty(javaAnnotations)) {
            return null;
        }
        for (JavaAnnotation javaAnnotation : javaAnnotations) {
            ApiTagPojo apiTagPojo = isApiTag(javaAnnotation);
            if (null != apiTagPojo) {
                return apiTagPojo;
            }
        }
        return null;
    }


    private static ApiTagPojo isApiTag(JavaAnnotation annotation) {
        if (null == annotation) {
            return null;
        }
        if (isPointAnn(annotation, API_TAG_NAME)) {
            ApiTagPojo apiTagPojo = new ApiTagPojo();
            apiTagPojo.setValue(getAnnoParamValue(annotation, ApiTagParamsEnum.VALUE.getValue()));
            apiTagPojo.setProtocol(getAnnoParamValue(annotation, ApiTagParamsEnum.PROTOCOL.getValue()));
            apiTagPojo.setAuthor(getAnnoParamValue(annotation, ApiTagParamsEnum.AUTHOR.getValue()));
            return apiTagPojo;
        }
        return null;
    }

    /**
     * 寻找标记了类路径的path参数 默认为/
     */
    public static String getClassPath(JavaClass javaClass, String commonPath) {
        commonPath = checkPath(commonPath);
        String classPath = StringPool.EMPTY;
        List<JavaAnnotation> annos = javaClass.getAnnotations();
        for (JavaAnnotation p : annos) {
            if (WS_RS_PATH.equalsIgnoreCase(getAnnoClassName(p))) {
                classPath = getAnnoParamValue(p, WS_RS_PATH_VALUE);
            }
            if (MVC_PATH.equalsIgnoreCase(getAnnoClassName(p))) {
                classPath = getAnnoParamValue(p, MVC_PATH_VALUE);
            }
        }

        classPath = checkPath(classPath);
        return commonPath + classPath;
    }

    /**
     * 获取方法的get post类型
     */
    public static HttpTypeEnum getMethodHttpType(JavaMethod javaMethod) {
        List<JavaAnnotation> annos = javaMethod.getAnnotations();
        if (CollectionUtils.isEmpty(annos)) {
            return HttpTypeEnum.UNKNOWN;
        }
        for (JavaAnnotation javaAnnotation : annos) {
            if (isPointAnn(javaAnnotation, WS_RS_GET)) {
                return HttpTypeEnum.GET;
            }
            if (isPointAnn(javaAnnotation, WS_RS_POST)) {
                return HttpTypeEnum.POST;
            }
            if (isPointAnn(javaAnnotation, MVC_GET_PATH)) {
                return HttpTypeEnum.GET;
            }
            if (isPointAnn(javaAnnotation, MVC_POST_PATH)) {
                return HttpTypeEnum.POST;
            }
            if (isPointAnn(javaAnnotation, MVC_PATH)) {
                Object method = javaAnnotation.getNamedParameter(MVC_PATH_METHOD);
                if (null == method) {
                    return HttpTypeEnum.UNKNOWN;
                }
                String methodStr = String.valueOf(method);
                if (MVC_REQUEST_METHOD_GET.equalsIgnoreCase(methodStr)) {
                    return HttpTypeEnum.GET;
                }
                if (MVC_REQUEST_METHOD_POST.equalsIgnoreCase(methodStr)) {
                    return HttpTypeEnum.POST;
                }
            }
        }
        return HttpTypeEnum.UNKNOWN;
    }

    /**
     * 获取方法的path路径
     */
    public static String getMethodPath(JavaMethod javaMethod) {
        List<JavaAnnotation> annos = javaMethod.getAnnotations();
        String path = StringPool.EMPTY;
        if (CollectionUtils.isEmpty(annos)) {
            return path;
        }
        for (JavaAnnotation javaAnnotation : annos) {
            if (isPointAnn(javaAnnotation, WS_RS_PATH)) {
                path = getAnnoParamValue(javaAnnotation, WS_RS_PATH_VALUE);
            }
            if (isPointAnn(javaAnnotation, MVC_PATH)) {
                path = getAnnoParamValue(javaAnnotation, MVC_PATH_VALUE);
            }
            if (isPointAnn(javaAnnotation, MVC_GET_PATH)) {
                path = getAnnoParamValue(javaAnnotation, MVC_PATH_VALUE);
            }
            if (isPointAnn(javaAnnotation, MVC_POST_PATH)) {
                path = getAnnoParamValue(javaAnnotation, MVC_PATH_VALUE);
            }
        }
        path = checkPath(path);
        return path;
    }

    /**
     * 获取get请求参数别名
     */
    public static String reqGetAliasName(List<JavaAnnotation> javaAnnotations) {
        if (CollectionUtils.isEmpty(javaAnnotations)) {
            return null;
        }
        for (JavaAnnotation javaAnnotation : javaAnnotations) {
            if (isPointAnn(javaAnnotation, WS_RS_QUERY_PARAM)) {
                String aliasName = getAnnoParamValue(javaAnnotation, WS_RS_QUERY_VALUE);
                return aliasName;
            }
            if (isPointAnn(javaAnnotation, MVC_REQUEST_PARAM)) {
                String aliasName = getAnnoParamValue(javaAnnotation, MVC_REQUEST_VALUE);
                return aliasName;
            }
        }
        return null;
    }

    /**
     * 参数实体获取别名
     */
    public static String paramsAliasName(List<JavaAnnotation> javaAnnotations) {
        if (CollectionUtils.isEmpty(javaAnnotations)) {
            return null;
        }
        for (JavaAnnotation javaAnnotation : javaAnnotations) {
            if (isPointAnn(javaAnnotation, JACKSON_JSONPROPERTY)) {
                String aliasName = getAnnoParamValue(javaAnnotation, JACKSON_JSONPROPERTY_VALUE);
                return aliasName;
            }
        }
        return null;
    }

    /**
     * 获取get请求参数是否必须
     */
    public static boolean reqGetRequired(List<JavaAnnotation> javaAnnotations) {
        if (CollectionUtils.isEmpty(javaAnnotations)) {
            return true;
        }
        for (JavaAnnotation javaAnnotation : javaAnnotations) {
            if (isPointAnn(javaAnnotation, MVC_REQUEST_PARAM)) {
                String required = getAnnoParamValue(javaAnnotation, MVC_REQUEST_REQUIRED);
                if (StringUtils.isBlank(required)) {
                    return true;
                }
                if (StringPool.TRUE.equalsIgnoreCase(required)) {
                    return true;
                }
                if (StringPool.FALSE.equalsIgnoreCase(required)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 自定义类参数是否必须
     */
    public static boolean paramRequired(List<JavaAnnotation> javaAnnotations) {
        if (CollectionUtils.isEmpty(javaAnnotations)) {
            return false;
        }
        for (JavaAnnotation javaAnnotation : javaAnnotations) {

            if (isPointAnn(javaAnnotation, JAVAX_NOT_BLANK)) {
                return true;
            }
            if (isPointAnn(javaAnnotation, JAVAX_NOT_EMPTY)) {
                return true;
            }
            if (isPointAnn(javaAnnotation, JAVAX_NOT_NULL)) {
                return true;
            }
            if (isPointAnn(javaAnnotation, HIBERNATE_NOT_BLANK)) {
                return true;
            }
            if (isPointAnn(javaAnnotation, HIBERNATE_NOT_EMPTY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 自定义类参数长度限制
     */
    public static LengthLimitInfoPojo paramLengthLimit(List<JavaAnnotation> javaAnnotations) {
        StringBuilder result = new StringBuilder(StringPool.EMPTY);
        LengthLimitInfoPojo pojo = new LengthLimitInfoPojo();
        if (CollectionUtils.isEmpty(javaAnnotations)) {
            return null;
        }
        for (JavaAnnotation javaAnnotation : javaAnnotations) {
            if (isPointAnn(javaAnnotation, JAVAX_MIN)) {
                String min = getAnnoParamValue(javaAnnotation, JAVAX_MAX_MIN_VALUE);
                if (StringUtils.isNotBlank(min)) {
                    pojo.setMin(Integer.parseInt(min));
                    result.append("min-val:");
                    result.append(min);
                    result.append(StringPool.SPACE);
                }
            }
            if (isPointAnn(javaAnnotation, JAVAX_MAX)) {
                String max = getAnnoParamValue(javaAnnotation, JAVAX_MAX_MIN_VALUE);
                if (StringUtils.isNotBlank(max)) {
                    pojo.setMax(Integer.parseInt(max));
                    result.append("max-val:");
                    result.append(max);
                    result.append(StringPool.SPACE);
                }
            }

            if (isPointAnn(javaAnnotation, JAVAX_SIZE)) {
                String min = getAnnoParamValue(javaAnnotation, JAVAX_SIZE_MIN);
                String max = getAnnoParamValue(javaAnnotation, JAVAX_SIZE_MAX);
                if (StringUtils.isNotBlank(min)) {
                    pojo.setMin(Integer.parseInt(min));
                    result.append("min-size:");
                    result.append(min);
                    result.append(StringPool.SPACE);
                }
                if (StringUtils.isNotBlank(max)) {
                    pojo.setMax(Integer.parseInt(max));
                    result.append("max-size:");
                    result.append(max);
                    result.append(StringPool.SPACE);
                }
                pojo.setLimitStr(result.toString());
                return pojo;
            }

            if (isPointAnn(javaAnnotation, HIBERNATE_LENGTH)) {
                String min = getAnnoParamValue(javaAnnotation, HIBERNATE_LENGTH_MIN);
                String max = getAnnoParamValue(javaAnnotation, HIBERNATE_LENGTH_MAX);
                if (StringUtils.isNotBlank(min)) {
                    pojo.setMin(Integer.parseInt(min));
                    result.append("min-len:");
                    result.append(min);
                    result.append(StringPool.SPACE);
                }
                if (StringUtils.isNotBlank(max)) {
                    pojo.setMax(Integer.parseInt(max));
                    result.append("max-len:");
                    result.append(max);
                    result.append(StringPool.SPACE);
                }
                pojo.setLimitStr(result.toString());
                return pojo;
            }
        }
        pojo.setLimitStr(result.toString());
        return pojo;
    }


    private static String checkPath(String path) {
        if (StringUtils.isBlank(path)) {
            return StringPool.EMPTY;
        }
        if (path.startsWith(StringPool.SLASH)) {
            return path;
        } else {
            return StringPool.SLASH + path;
        }
    }

    private static boolean isPointAnn(JavaAnnotation javaAnnotation, String target) {
        if (StringUtils.isBlank(target)) {
            return false;
        }
        if (null == javaAnnotation) {
            return false;
        }
        String name = getAnnoClassName(javaAnnotation);
        if (StringUtils.isBlank(name)) {
            return false;
        }
        if (target.equalsIgnoreCase(name)) {
            return true;
        }
        //万一获取不到全名的问题
        String[] targetSplit = target.split("\\.");
        int size = targetSplit.length;
        if (size <= 0) {
            return false;
        }
        String lastName = targetSplit[size - 1];
        if (StringUtils.isBlank(lastName)) {
            return false;
        }
        if (lastName.equals(name)) {
            return true;
        }
        return false;
    }

    /**
     * 获取全限注解名
     */
    private static String getAnnoClassName(JavaAnnotation javaAnnotation) {
        if (null == javaAnnotation) {
            return StringPool.EMPTY;
        }
        return javaAnnotation.getType().getFullyQualifiedName();
    }

    private static String getAnnoParamValue(JavaAnnotation javaAnnotation, String param) {
        Object obj = javaAnnotation.getNamedParameter(param);
        if (null == obj) {
            return StringPool.EMPTY;
        }
        String str = String.valueOf(obj);
        return handRawStr(str);
    }

    private static String handRawStr(String rawStr) {
        if (StringUtils.isBlank(rawStr)) {
            return StringPool.EMPTY;
        }
        if (rawStr.startsWith(StringPool.QUOTATION)) {
            rawStr = rawStr.substring(1, rawStr.length());
            rawStr = rawStr.substring(0, rawStr.length() - 1);
        }
        return rawStr;
    }


}
