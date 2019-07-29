package com.sjw.api4j.enums;

/**
 * @author shijiawei
 * @version UsdtTradeEnum.java -> v 1.0
 * @date 2019/7/7
 * usdt 币币交易对
 */
public enum ProtocolEnum {
    //REST协议 包括mvc和duubo-rest
    REST,
    //dubbo java调用协议,不生成path,直接带facade信息
    DUBBO
}
