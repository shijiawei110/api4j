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
     * 子参数集 (只有在是java自定义类型的时候才有)
     */
    private List<BaseParams> childs = null;


    /**
     * 设置基础类型
     */
    public static BaseParams newjavaType(String type, String name, String desc) {
        BaseParams baseParams = newParam(type, name, desc);
        return baseParams;
    }

    /**
     * 设置List基础类型
     */
    public static BaseParams newjavaTypeList(String type, String name, String desc) {
        BaseParams baseParams = newParam(type, name, desc);
        baseParams.setArray(true);
        return baseParams;
    }

    /**
     * 设置自定义类型信息
     */
    public static BaseParams newCustomType(String type, String name, String desc) {
        BaseParams baseParams = newParam(type, name, desc);
        baseParams.setJavaType(false);
        return baseParams;
    }

    /**
     * 设置自定义List类型信息
     */
    public static BaseParams newCustomTypeList(String type, String name, String desc) {
        BaseParams baseParams = newParam(type, name, desc);
        baseParams.setJavaType(false);
        baseParams.setArray(true);
        return baseParams;
    }

    private static BaseParams newParam(String type, String name, String desc) {
        BaseParams baseParams = new BaseParams();
        if (StringUtils.isNotBlank(name)) {
            baseParams.setName(name);
        }
        if (StringUtils.isNotBlank(type)) {
            baseParams.setType(type);
        }
        if (StringUtils.isNotBlank(desc)) {
            baseParams.setDesc(desc);
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
