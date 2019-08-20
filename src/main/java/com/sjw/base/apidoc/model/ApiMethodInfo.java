package com.sjw.base.apidoc.model;

import com.sjw.base.apidoc.enums.HttpTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author shijiawei
 * @version ApiMethodInfo.java -> v 1.0
 * @date 2019/7/30
 */
@Data
public class ApiMethodInfo {
    private String author;
    private String name;
    private String note;
    private String tagValue;
    private HttpTypeEnum httpTypeEnum;
    private String path;

    private BaseParams inputParams;
    private BaseParams outputParams;

    public ApiMethodInfo(ApiTagMethod apiTagMethod) {
        this.author = apiTagMethod.getAuthor();
        this.note = apiTagMethod.getNote();
        this.tagValue = apiTagMethod.getTagValue();
        this.name = apiTagMethod.getName();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
