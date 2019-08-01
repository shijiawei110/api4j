package com.sjw.api4j.helper;

import com.google.common.collect.Sets;
import com.thoughtworks.qdox.model.JavaParameter;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * @author shijiawei
 * @version ClassNameHelper.java -> v 1.0
 * @date 2019/7/31
 */
public class ClassNameHelper {


    private static final String VOID = "void";

    private static final String JAVA_COLLECTION_PATH = "java.util.";
    private static final String JAVA_LANG_PATH = "java.lang.";


    private static Set<String> EXCLUDE_INPUT_PARAM = Sets.newHashSet();

    private static Set<String> JAVA_BASE_TYPE = Sets.newHashSet();


    static {
        /**
         * 不需要的入参
         */
        EXCLUDE_INPUT_PARAM.add("javax.servlet.http.HttpServletRequest");
        EXCLUDE_INPUT_PARAM.add("javax.servlet.http.HttpServletResponse");

        /**
         * 8种基本类型
         */
        JAVA_BASE_TYPE.add("int");
        JAVA_BASE_TYPE.add("double");
        JAVA_BASE_TYPE.add("long");
        JAVA_BASE_TYPE.add("short");
        JAVA_BASE_TYPE.add("byte");
        JAVA_BASE_TYPE.add("boolean");
        JAVA_BASE_TYPE.add("char");
        JAVA_BASE_TYPE.add("float");
    }

    public static boolean isNeedExcludeInputParam(JavaParameter parameter) {
        if (null == parameter) {
            return true;
        }
        String fullName = parameter.getFullyQualifiedName();
        return EXCLUDE_INPUT_PARAM.contains(fullName);
    }

    public static boolean isVoid(String className) {
        if (StringUtils.isBlank(className)) {
            return false;
        }
        if (VOID.equals(className)) {
            return true;
        }
        return false;
    }

    public static boolean isJavaBaseType(String className) {
        return !StringUtils.isBlank(className) && JAVA_BASE_TYPE.contains(className);
    }

    public static boolean isJavaLang(String className) {
        className = className.substring(0, 10);
        return !StringUtils.isBlank(className) && JAVA_LANG_PATH.equals(className);
    }

    public static boolean isJavaUtil(String className) {
        className = className.substring(0, 10);
        return !StringUtils.isBlank(className) && JAVA_COLLECTION_PATH.equals(className);
    }

}
