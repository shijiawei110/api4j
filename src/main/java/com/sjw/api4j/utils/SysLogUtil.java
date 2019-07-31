package com.sjw.api4j.utils;

import com.sjw.api4j.expection.ApiForJException;

import java.text.MessageFormat;

/**
 * @author shijiawei
 * @version SysLogUtil.java -> v 1.0
 * @date 2019/7/17
 * 系统out模拟日志
 */
public class SysLogUtil {

    private static final String TPOIC = "api4j log -> ";

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

    public static void info(final String msg, Object params) {
        System.out.println(MessageFormat.format(TPOIC + msg, params));
    }

    public static void printExpection(ApiForJException e) {
        System.out.println(TPOIC + "take a expection : " + e.getMessage());
    }

    public static void duration(String topic, long start) {
        long duration = System.currentTimeMillis() - start;
        System.out.println(TPOIC + "task【" + topic + "】" + "duration = " + duration + " ms");
    }
}
