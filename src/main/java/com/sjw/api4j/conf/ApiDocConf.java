package com.sjw.api4j.conf;

import lombok.Data;

/**
 * @author shijiawei
 * @version ApiDocConf.java -> v 1.0
 * @date 2019/7/28
 * 生成配置
 */
@Data
public class ApiDocConf {
    /**
     * 公共前缀路径
     */
    private String commonPath;
    /**
     * 生成文档还是输出到控制台
     */
    private boolean isOutPutDoc = false;
}
