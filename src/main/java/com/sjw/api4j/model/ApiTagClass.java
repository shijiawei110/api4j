package com.sjw.api4j.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author shijiawei
 * @version ApiTagClass.java -> v 1.0
 * @date 2019/7/28
 */
@Data
public class ApiTagClass {
    private String path;

    private List<ApiTagMethod> methods = Lists.newArrayList();

}
