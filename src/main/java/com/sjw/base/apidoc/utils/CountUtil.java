package com.sjw.base.apidoc.utils;

/**
 * @author shijiawei
 * @version CountUtil.java -> v 1.0
 * @date 2019/8/16
 * 计数器
 */
public class CountUtil {

    public CountUtil() {

    }

    public CountUtil(int num) {
        this.num = num;
    }

    private int num;

    public void incre() {
        num++;
    }

    public int get() {
        return num;
    }
}
