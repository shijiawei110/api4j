package com.sjw.api4j.model;

import com.sjw.api4j.enums.HttpTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author shijiawei
 * @version ApiMethodInfo.java -> v 1.0
 * @date 2019/7/30
 */
@Data
public class ApiMethodInfo {
    private String author;
    private String note;
    private String tagValue;
    private HttpTypeEnum httpTypeEnum;
    private String path;
    private List<BaseParamInfo> inputParams;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
