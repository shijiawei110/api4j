package com.sjw.api4j.enums;

/**
 * @author shijiawei
 * @version ApiTagParamsEnum.java -> v 1.0
 * @date 2019/7/7
 */
public enum ApiTagParamsEnum {
    VALUE("value"),
    AUTHOR("author"),
    NAME("name"),
    PROTOCOL("protocol");

    private String value;

    ApiTagParamsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
