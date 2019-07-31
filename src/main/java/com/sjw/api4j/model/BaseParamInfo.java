package com.sjw.api4j.model;

import com.sjw.api4j.utils.StringPool;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author shijiawei
 * @version BaseParamInfo.java -> v 1.0
 * @date 2019/7/27
 * 基础参数的信息
 */
@Data
public class BaseParamInfo {

    private static final String UNKNOWN_NAME = "[unknown]";

    public static BaseParamInfo baseJavaType(String type) {
        BaseParamInfo baseParamInfo = new BaseParamInfo();
        baseParamInfo.setType(type);
        return baseParamInfo;
    }

    /**
     * 参数名
     */
    private String name = StringPool.EMPTY;

    /**
     * 参数描述
     */
    private String desc = StringPool.EMPTY;

    /**
     * 参数类型
     */
    private String type = StringPool.EMPTY;

    /**
     * 是否必须
     */
    private boolean required = true;

    /**
     * 长度
     */
    private String length = StringPool.EMPTY;


    /**
     * 子参数
     */
    private List<BaseParamInfo> childrens = null;


    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
