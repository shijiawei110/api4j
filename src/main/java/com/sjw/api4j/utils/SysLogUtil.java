package com.sjw.api4j.utils;

import java.text.MessageFormat;

/**
 * @author shijiawei
 * @version SysLogUtil.java -> v 1.0
 * @date 2019/7/17
 * 系统out模拟日志
 */
public class SysLogUtil {

    private static final String TPOIC = "api4j log -> ";

    public static void info(final String msg) {
        System.out.println(TPOIC + msg);
    }

    public static void info(final String msg, Object params) {
        System.out.println(MessageFormat.format(TPOIC + msg, params));
    }
}
