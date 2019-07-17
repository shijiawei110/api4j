package com.sjw.api4j.enums;

/**
 * @author shijiawei
 * @version UsdtTradeEnum.java -> v 1.0
 * @date 2019/7/7
 * usdt 币币交易对
 */
public enum AccountTypeEnum {
    SPOT("spot");

    private String value;

    AccountTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
