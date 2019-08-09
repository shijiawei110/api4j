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

    private static final int MAX_STR_LENGTH = 4096;

    //todo 先直接写一个后面可以根据关键字路由

    public static String getStr(String key) {
        return getStr(key, null, null);
    }

    public static String getStr(String key, Integer min, Integer max) {
        if (null == min || min < 0) {
            min = 0;
        }
        if (null == max || max > MAX_STR_LENGTH) {
            max = MAX_STR_LENGTH;
        }
        if (max <= 0) {
            max = MAX_STR_LENGTH;
        }
        String result = FAKER.name().name();
        int size = result.length();

        if (size < min) {
            int dif = min - size;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < dif; i++) {
                sb.append(StringPool.ASTERISK);
            }
            result = result + sb.toString();
            return result;
        }

        if (size > max) {
            String re = result.substring(0, max);
            return re;
        }
        return result;
    }

    public static long getLong(String key) {
        return getLong(key, null, null);
    }

    public static long getLong(String key, Integer min, Integer max) {
        if (null == min) {
            min = 0;
        }
        if (null == max) {
            max = Integer.MAX_VALUE;
        }
        if (min < 0) {
            min = 0;
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
        if (min < 0) {
            min = 0;
        }
        return FAKER.number().numberBetween(min, max);
    }

    public static boolean getBoolean() {
        return FAKER.bool().bool();
    }
}
