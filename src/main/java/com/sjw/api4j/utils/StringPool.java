package com.sjw.api4j.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author shijiawei
 * @version StringPool.java, v 0.1
 * @date 2019/1/23
 */
public class StringPool {
    public static final String AMPERSAND = "&";
    public static final String AND = "and";
    public static final String AT = "@";
    public static final String ASTERISK = "*";
    public static final String STAR = "*";
    public static final String BACK_SLASH = "\\";
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String DASH = "-";
    public static final String DOLLAR = "$";
    public static final String DOT = ".";
    public static final String DOTDOT = "..";
    public static final String DOT_CLASS = ".class";
    public static final String DOT_JAVA = ".java";
    public static final String EMPTY = "";
    public static final String EQUALS_CHAR = "=";
    public static final String FALSE = "false";
    public static final String SLASH = "/";
    public static final String HASH = "#";
    public static final String HAT = "^";
    public static final String LEFT_BRACE = "{";
    public static final String LEFT_BRACKET = "(";
    public static final String LEFT_CHEV = "<";
    public static final String NEWLINE = "\n";
    public static final String N = "n";
    public static final String NO = "no";
    public static final String NULL = "null";
    public static final String OFF = "off";
    public static final String ON = "on";
    public static final String PERCENT = "%";
    public static final String PIPE = "|";
    public static final String PLUS = "+";
    public static final String QUESTION_MARK = "?";
    public static final String EXCLAMATION_MARK = "!";
    public static final String QUOTE = "\"";
    public static final String RETURN = "\r";
    public static final String TAB = "\t";
    public static final String RIGHT_BRACE = "}";
    public static final String RIGHT_BRACKET = ")";
    public static final String RIGHT_CHEV = ">";
    public static final String SEMICOLON = ";";
    public static final String SINGLE_QUOTE = "'";
    public static final String BACKTICK = "`";
    public static final String SPACE = " ";
    public static final String TILDA = "~";
    public static final String LEFT_SQ_BRACKET = "[";
    public static final String RIGHT_SQ_BRACKET = "]";
    public static final String TRUE = "true";
    public static final String UNDERSCORE = "_";
    public static final String UTF_8 = "UTF-8";
    public static final String US_ASCII = "US-ASCII";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String Y = "y";
    public static final String YES = "yes";
    public static final String ONE = "1";
    public static final String ZERO = "0";
    public static final String DOLLAR_LEFT_BRACE = "${";
    public static final String CRLF = "\r\n";
    public static final String HTML_NBSP = "&nbsp;";
    public static final String HTML_AMP = "&amp";
    public static final String HTML_QUOTE = "&quot;";
    public static final String HTML_LT = "&lt;";
    public static final String HTML_GT = "&gt;";
    public static final String SUCCESS = "success";
    public static final String QUOTATION = "\"";
    public static final String REAL_DOT = "\\.";
    public static final String YES_CN = "是";
    public static final String NO_CN = "否";


    public static String nullEmpty(String v) {
        if (StringUtils.isBlank(v)) {
            return EMPTY;
        }
        return v;
    }

    public static String trueFlase(boolean v) {
        if (v) {
            return YES_CN;
        } else {
            return NO_CN;
        }
    }
}
