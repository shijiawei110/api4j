package com.sjw.api4j.utils;

import com.sjw.api4j.expection.ApiForJException;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * @author shijiawei
 * @version SysLogUtil.java -> v 1.0
 * @date 2019/7/17
 * 系统out模拟日志
 */
public class SysLogUtil {

    private static final String TPOIC = "api4j log -> ";

    private static final int DEFAULT_SJ_NUM = 10;
    private static final String TITLE_STAR = "*******************************";
    private static final String FEN_GE = "-----------------------------------------------------------------";

    public static void sysStart() {
        System.out.println(TPOIC + ">>> system start .");
    }

    public static void sysEnd(long start) {
        long duration = System.currentTimeMillis() - start;
        System.out.println(TPOIC + ">>> system end -> duration = " + duration + " ms");
    }

    public static void info(final String msg) {
        System.out.println(TPOIC + msg);
    }

    public static void infoNoLn(final String msg) {
        System.out.print(msg);
    }

    public static void info(final String msg, Object... params) {
        System.out.println(MessageFormat.format(TPOIC + msg, params));
    }

    public static void printExpection(ApiForJException e) {
        System.out.println(TPOIC + "take a expection : " + e.getMessage());
    }

    public static void duration(String topic, long start) {
        long duration = System.currentTimeMillis() - start;
        System.out.println(TPOIC + "task【" + topic + "】" + "duration = " + duration + " ms");
    }

    public static void title(String title) {
        String content = TITLE_STAR + StringPool.EMPTY + title + StringPool.EMPTY + TITLE_STAR;
        System.out.println(content);
        System.out.println();
    }

    public static void fg() {
        System.out.println();
        System.out.println(FEN_GE);
        System.out.println();
    }

    public static void ln() {
        System.out.println();
    }

    public static void sj() {
        System.out.print("--");
    }

    public static void jg() {
        for (int i = 0; i < DEFAULT_SJ_NUM; i++) {
            System.out.print(" ");
        }
    }

    public static void jg(int num) {
        if (num <= 0) {
            num = DEFAULT_SJ_NUM;
        }
        for (int i = 0; i < num; i++) {
            System.out.print(" ");
        }
    }

    public static void apiParamKv(String key, String value) {
        if (StringUtils.isBlank(value)) {
            value = StringPool.EMPTY;
        }
        System.out.println(StringPool.STAR + key + StringPool.SPACE + StringPool.COLON + StringPool.SPACE + value);
    }

    public static void request() {
        System.out.println("【request】");
        System.out.println();
    }

    public static void response() {
        System.out.println("【response】");
        System.out.println();
    }

    public static void reqDemo() {
        System.out.println("【请求示例】");
        System.out.println();
    }

    public static void resDemo() {
        System.out.println("【返回示例】");
        System.out.println();
    }
}
