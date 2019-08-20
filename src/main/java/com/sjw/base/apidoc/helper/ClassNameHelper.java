package com.sjw.base.apidoc.helper;

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

    private static final String JAVA_LANG_STRING = "String";
    private static final String JAVA_INTEGER = "Integer";
    private static final String JAVA_INTEGER_BASE = "int";
    private static final String JAVA_LONG = "Long";
    private static final String JAVA_LONG_BASE = "long";
    private static final String JAVA_BOOL = "Boolean";
    private static final String JAVA_BOOL_BASE = "boolean";


    private static Set<String> EXCLUDE_INPUT_PARAM = Sets.newHashSet();

    private static Set<String> JAVA_BASE_TYPE = Sets.newHashSet();

    private static Set<String> EXCLUDE_FIELD_NAME = Sets.newHashSet();


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

        /**
         * 需要排除的变量名
         */
        EXCLUDE_FIELD_NAME.add("serialVersionUID");
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
        if (className.length() < 10) {
            return false;
        }
        className = className.substring(0, 10);
        return !StringUtils.isBlank(className) && JAVA_LANG_PATH.equals(className);
    }

    public static boolean isJavaUtil(String className) {
        if (className.length() < 10) {
            return false;
        }
        className = className.substring(0, 10);
        return !StringUtils.isBlank(className) && JAVA_COLLECTION_PATH.equals(className);
    }

    public static boolean isExcludeField(String fieldName) {
        return !StringUtils.isBlank(fieldName) && EXCLUDE_FIELD_NAME.contains(fieldName);
    }


    public static boolean isString(String typeName) {
        return !StringUtils.isBlank(typeName) && typeName.contains(JAVA_LANG_STRING);
    }

    public static boolean isInt(String typeName) {
        return !StringUtils.isBlank(typeName) && (typeName.contains(JAVA_INTEGER) || typeName.contains(JAVA_INTEGER_BASE));
    }

    public static boolean isLong(String typeName) {
        return !StringUtils.isBlank(typeName) && (typeName.contains(JAVA_LONG) || typeName.contains(JAVA_LONG_BASE));
    }

    public static boolean isBool(String typeName) {
        return !StringUtils.isBlank(typeName) && (typeName.contains(JAVA_BOOL) || typeName.contains(JAVA_BOOL_BASE));
    }

}
