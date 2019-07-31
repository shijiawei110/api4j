package com.sjw.api4j.model;

import com.sjw.api4j.enums.ProtocolEnum;
import com.sjw.api4j.helper.AnnotationHelper;
import com.sjw.api4j.utils.StringPool;
import org.apache.commons.lang3.StringUtils;

/**
 * @author shijiawei
 * @version ApiTagPojo.java -> v 1.0
 * @date 2019/7/27
 * ApiTagPojo的属性pojo
 */


public class ApiTagPojo {

    private static final String REST = "ProtocolEnum.REST";
    private static final String DUBBO = "ProtocolEnum.DUBBO";

    private String value = StringPool.EMPTY;

    private ProtocolEnum protocol = ProtocolEnum.REST;

    private String author = StringPool.EMPTY;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProtocolEnum getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        if (StringUtils.isBlank(protocol)) {
            return;
        }
        if (REST.equals(protocol)) {
            this.protocol = ProtocolEnum.REST;
        }
        if (DUBBO.equals(protocol)) {
            this.protocol = ProtocolEnum.DUBBO;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
