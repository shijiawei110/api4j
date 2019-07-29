package com.sjw.api4j.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author shijiawei
 * @version BaseParamInfo.java -> v 1.0
 * @date 2019/7/27
 * 基础参数的信息
 */
@Data
public class BaseParamInfo {
    /**
     * 参数名
     */
    private String name;

    /**
     * 参数描述
     */
    private String desc;

    /**
     * 参数类型
     */
    private String type;

    /**
     * 是否必须
     */
    private boolean required = false;

    /**
     * 长度
     */
    private String length;

    /**
     * 子参数总长度
     */
    private Integer childSize = 0;

    /**
     * 子参数
     */
    private List<BaseParamInfo> childrens = Lists.newArrayList();
}
