package com.sjw.base.apidoc.enums;

/**
 * @author shijiawei
 * @version ProtocolEnum.java -> v 1.0
 * @date 2019/7/7
 */
public enum ProtocolEnum {
    //REST协议 包括mvc和duubo-rest
    REST,
    //dubbo java调用协议,不生成path,直接带facade信息
    DUBBO
}
