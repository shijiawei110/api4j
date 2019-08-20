package com.sjw.base.apidoc.model;

import lombok.Data;

import java.util.List;

/**
 * @author shijiawei
 * @version ApiClassInfo.java -> v 1.0
 * @date 2019/8/19
 * 最终输出的class信息
 */
@Data
public class ApiClassInfo {
    private String controllerName;
    private List<ApiMethodInfo> methodInfos;
    private int classIndex;
}
