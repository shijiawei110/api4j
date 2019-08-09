package com.sjw.api4j.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * @author shijiawei
 * @version DataFakerUtil.java -> v 1.0
 * @date 2019/8/7
 */
public class DataFakerUtil {

    private static final Faker FAKER_CN = new Faker(new Locale("zh-CN"));
    private static final Faker FAKER = new Faker();

    //todo 先直接写一个后面可以根据关键字路由

    public static String getStr(String key) {
        return FAKER.name().name();
    }

    public static long getLong(String key) {
        return getLong(key, null, null);
    }

    public static long getLong(String key, Long min, Long max) {
        if (null == min) {
            min = 0L;
        }
        if (null == max) {
            max = Long.MAX_VALUE;
        }
        return FAKER.number().numberBetween(min, max);
    }

    public static long getInt(String key) {
        return getInt(key, null, null);
    }

    public static long getInt(String key, Integer min, Integer max) {
        if (null == min) {
            min = 0;
        }
        if (null == max) {
            max = Integer.MAX_VALUE;
        }
        return FAKER.number().numberBetween(min, max);
    }

    public static boolean getBoolean() {
        return FAKER.bool().bool();
    }
}
