package com.sjw.api4j.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author shijiawei
 * @version CustomMethodConfig.java -> v 1.0
 * @date 2019/8/14
 */
@Data
public class CustomMethodConfig {
    private String className;
    private List<String> methodNames;

    public CustomMethodConfig(String className, String methodName) {
        this.className = className;
        methodNames = Lists.newArrayList();
        methodNames.add(methodName);
    }

    public void addMethod(String methodName) {
        methodNames.add(methodName);
    }
}
