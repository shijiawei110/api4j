package com.sjw.api4j.helper;

import com.thoughtworks.qdox.model.JavaAnnotation;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author shijiawei
 * @version AnnotationHelper.java -> v 1.0
 * @date 2019/7/29
 */
public class AnnotationHelper {

    private static final String API_TAG_NAME = "com.sjw.api4j.annotation.ApiTag";

    /**
     * 是否标记 apiTag
     */
    public static boolean isApiTag(List<JavaAnnotation> javaAnnotations) {
        if (CollectionUtils.isEmpty(javaAnnotations)) {
            return false;
        }
        for (JavaAnnotation javaAnnotation : javaAnnotations) {
            if (isApiTag(javaAnnotation)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isApiTag(JavaAnnotation annotation) {
        if (null == annotation) {
            return false;
        }
        return API_TAG_NAME.equals(annotation.getType().toString());
    }

    /**
     * 寻找标记了类路径的path参数 默认为/
     */

}
