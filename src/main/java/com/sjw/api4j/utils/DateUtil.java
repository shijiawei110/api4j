package com.sjw.api4j.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author shijiawei
 * @version DateUtil.java -> v 1.0
 * @date 2019/8/19
 */
public class DateUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String getTimeNowStr() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
