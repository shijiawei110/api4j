package com.sjw.api4j.model;

import com.google.common.collect.Lists;
import com.sjw.api4j.utils.StringPool;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author shijiawei
 * @version BaseParams.java -> v 1.0
 * @date 2019/8/1
 */
@Data
public class BaseParams {

    /**
     * 本身是否是list
     **/
    private boolean isArray = false;

    /**
     * 是否为java基本数据类型
     */
    private boolean isJavaType = true;

    /**
     * 是否为泛型
     */
    private boolean isFx = false;

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
     * 参数类型缩写
     */
    private String typeAbbr = StringPool.EMPTY;

    /**
     * 是否必须
     * 1:String类型不能为empty
     * 3:普通类型不能为null
     * 3:如果是list类型 则list不能为空
     */
    private boolean required = false;

    /**
     * 长度
     */
    private String length = StringPool.EMPTY;

    /**
     * 长度参数(json mock使用)
     */
    private int minLimit = -1;
    private int maxLimit = -1;


    /**
     * 子参数集 (只有在是java自定义类型的时候才有)
     */
    private List<BaseParams> childs = null;


    /**
     * 设置基础类型
     */
    public static BaseParams newjavaType(String type, String abbrType, String name, String desc, boolean required, LengthLimitInfoPojo length) {
        BaseParams baseParams = newParam(type, abbrType, name, desc, required, length);
        return baseParams;
    }

    /**
     * 设置List基础类型
     */
    public static BaseParams newjavaTypeList(String type, String abbrType, String name, String desc, boolean required, LengthLimitInfoPojo length) {
        BaseParams baseParams = newParam(type, abbrType, name, desc, required, length);
        baseParams.setArray(true);
        return baseParams;
    }

    /**
     * 设置自定义类型信息
     */
    public static BaseParams newCustomType(String type, String abbrType, String name, String desc, boolean required, LengthLimitInfoPojo length) {
        BaseParams baseParams = newParam(type, abbrType, name, desc, required, length);
        baseParams.setJavaType(false);
        return baseParams;
    }

    /**
     * 设置自定义List类型信息
     */
    public static BaseParams newCustomTypeList(String type, String abbrType, String name, String desc, boolean required, LengthLimitInfoPojo length) {
        BaseParams baseParams = newParam(type, abbrType, name, desc, required, length);
        baseParams.setJavaType(false);
        baseParams.setArray(true);
        return baseParams;
    }

    /**
     * 设置为泛型类型
     */
    public static BaseParams newFxType(String type, String abbrType, String name, String desc, boolean required, LengthLimitInfoPojo length) {
        BaseParams baseParams = newParam(type, abbrType, name, desc, required, length);
        baseParams.setFx(true);
        baseParams.setJavaType(false);
        return baseParams;
    }

    private static BaseParams newParam(String type, String abbrType, String name, String desc, boolean required, LengthLimitInfoPojo length) {
        BaseParams baseParams = new BaseParams();
        if (StringUtils.isNotBlank(name)) {
            baseParams.setName(name);
        }
        if (StringUtils.isNotBlank(type)) {
            baseParams.setType(type);
        }
        if (StringUtils.isNotBlank(abbrType)) {
            baseParams.setTypeAbbr(abbrType);
        }
        if (StringUtils.isNotBlank(desc)) {
            baseParams.setDesc(desc);
        }
        baseParams.setRequired(required);
        if (null != length) {
            baseParams.setLength(length.getLimitStr());
            baseParams.setMinLimit(length.getMin());
            baseParams.setMaxLimit(length.getMax());
        }
        return baseParams;
    }


    public BaseParams() {
    }

    public void addChild(BaseParams child) {
        if (null == childs) {
            childs = Lists.newArrayList();
        }
        childs.add(child);
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
