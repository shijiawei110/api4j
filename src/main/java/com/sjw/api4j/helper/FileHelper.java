package com.sjw.api4j.helper;

/**
 * @author shijiawei
 * @version FileHelper.java -> v 1.0
 * @date 2019/7/17
 */
public class FileHelper {
    /**
     * 获取当前根目录
     */
    public static String getRootPath() {
        return System.getProperty("user.dir");
    }
}
