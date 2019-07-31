package com.sjw.api4j.model;

import com.sjw.api4j.enums.ProtocolEnum;
import com.thoughtworks.qdox.model.JavaMethod;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author shijiawei
 * @version ApiTagMethod.java -> v 1.0
 * @date 2019/7/27
 * 需要生成文档的方法包装类
 */
@Data
public class ApiTagMethod {
    private JavaMethod javaMethod;
    private String tagValue;
    private String note;
    private ProtocolEnum protocol;
    private String author;

    public ApiTagMethod(JavaMethod javaMethod, String tagValue, ProtocolEnum protocol, String author) {
        this.javaMethod = javaMethod;
        this.tagValue = tagValue;
        this.protocol = protocol;
        this.author = author;
        this.note = javaMethod.getComment();
    }

    public ApiTagMethod(JavaMethod javaMethod, ApiTagPojo apiTagPojo) {
        this.javaMethod = javaMethod;
        this.note = javaMethod.getComment();
        this.tagValue = apiTagPojo.getValue();
        this.protocol = apiTagPojo.getProtocol();
        this.author = apiTagPojo.getAuthor();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
