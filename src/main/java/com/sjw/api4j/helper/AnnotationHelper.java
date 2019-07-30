package com.sjw.api4j.helper;

import com.sjw.api4j.enums.ApiTagParamsEnum;
import com.sjw.api4j.model.ApiTagPojo;
import com.sjw.api4j.utils.StringPool;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
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
    private static final String MVC_PATH = "org.springframework.web.bind.annotation.RequestMapping";
    private static final String MVC_PATH_VALUE = "value";


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
        if (API_TAG_NAME.equalsIgnoreCase(annotation.getType().toString())) {
            ApiTagPojo apiTagPojo = new ApiTagPojo();
            Object value = annotation.getNamedParameter(ApiTagParamsEnum.VALUE.getValue());
            if (null != value) {
                apiTagPojo.setValue((String) value);
            }
            Object protocol = annotation.getNamedParameter(ApiTagParamsEnum.PROTOCOL.getValue());
            if (null != protocol) {
                apiTagPojo.setProtocol((String) protocol);
            }

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
                classPath = (String) p.getProperty(WS_RS_PATH_VALUE).getParameterValue();
            }
            if (MVC_PATH.equalsIgnoreCase(getAnnoClassName(p))) {
                classPath = (String) p.getProperty(MVC_PATH_VALUE).getParameterValue();
            }
        }
        if (StringUtils.isNotBlank(classPath)) {
            classPath = handRawStr(classPath);
        }
        classPath = checkPath(classPath);
        return commonPath + classPath;

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

    /**
     * 去除qdox文件读取前后的引号
     */
    public static String handRawStr(String rawStr) {
        rawStr = rawStr.substring(1, rawStr.length());
        rawStr = rawStr.substring(0, rawStr.length() - 1);
        return rawStr;
    }

    /**
     * 获取全限注解名
     */
    public static String getAnnoClassName(JavaAnnotation javaAnnotation){
        if(null == javaAnnotation){
            return StringPool.EMPTY;
        }
        return javaAnnotation.getType().getFullyQualifiedName();
    }


}
