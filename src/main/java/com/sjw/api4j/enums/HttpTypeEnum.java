package com.sjw.api4j.enums;

/**
 * @author shijiawei
 * @version HttpTypeEnum.java -> v 1.0
 * @date 2019/7/30
 */
public enum HttpTypeEnum {
    POST("post"),
    UNKNOWN("unknown"),
    GET("get");

    private String value;

    HttpTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isGet() {
        if (this == GET) {
            return true;
        } else {
            return false;
        }
    }
}
