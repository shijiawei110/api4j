package com.sjw.api4j.model.template;

import com.sjw.api4j.model.BaseParams;
import com.sjw.api4j.utils.StringPool;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author shijiawei
 * @version MdTemplateParamPojo.java -> v 1.0
 * @date 2019/8/16
 */
@Data
public class MdTemplateParamPojo {

    private static final String NAME_WRAPPER_TIP = "<strong>";
    private static final String TYPE_BEAN_VAL = "[Bean]";

    /**
     * 参数名
     */
    private String name = StringPool.EMPTY;
    /**
     * 参数类型
     */
    private String type = StringPool.EMPTY;

    /**
     * 是否必须
     */
    private String isRequired = StringPool.EMPTY;

    /**
     * 是否为array
     */
    private String isArray = StringPool.EMPTY;

    /**
     * 长度限制
     */
    private String lengthLimit = StringPool.EMPTY;

    /**
     * 描述
     */
    private String desc = StringPool.EMPTY;

    /**
     * 缩进格数
     */
    private int sjNum = 0;

    /**
     * 参数名的合并格数 = 总格数 - 缩进格数
     */
    private int hbNum = 0;


    public MdTemplateParamPojo() {

    }

    public MdTemplateParamPojo(BaseParams baseParams) {
        String name;
        String type;
        if (baseParams.isJavaType()) {
            name = wrapperName(baseParams.getName());
            type = wrapperType();
        } else {
            name = baseParams.getName();
            type = baseParams.getTypeAbbr();
        }
        boolean isRequired = baseParams.isRequired();
        boolean isArray = baseParams.isArray();
        String lengthLimit = baseParams.getLength();
        String desc = baseParams.getDesc();
        this.name = StringPool.nullEmpty(name);
        this.type = StringPool.nullEmpty(type);
        this.lengthLimit = StringPool.nullEmpty(lengthLimit);
        this.desc = StringPool.nullEmpty(desc);
        this.isRequired = StringPool.trueFlase(isRequired);
        this.isArray = StringPool.trueFlase(isArray);

    }

    private String wrapperName(String name) {
        if (StringUtils.isBlank(name)) {
            return StringPool.EMPTY;
        }
        return NAME_WRAPPER_TIP + name;
    }

    private String wrapperType() {
        return NAME_WRAPPER_TIP + TYPE_BEAN_VAL;
    }


}
