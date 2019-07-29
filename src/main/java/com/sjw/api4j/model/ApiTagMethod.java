package com.sjw.api4j.model;

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

    public ApiTagMethod(JavaMethod javaMethod, String tagValue) {
        this.javaMethod = javaMethod;
        this.tagValue = tagValue;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
