package com.sjw.api4j.model.template;

import com.google.common.collect.Lists;
import com.sjw.api4j.model.ApiMethodInfo;
import com.sjw.api4j.utils.StringPool;
import lombok.Data;

import java.util.List;

/**
 * @author shijiawei
 * @version MdTemplatePojo.java -> v 1.0
 * @date 2019/8/16
 * md生成标准pojo
 */
@Data
public class MdTemplatePojo {
    /**
     * 标题序号
     */
    private String index = StringPool.EMPTY;

    /**
     * 标题
     */
    private String title = StringPool.EMPTY;

    /**
     * 请求路径
     */
    private String path = StringPool.EMPTY;

    /**
     * 接口类型
     */
    private String type = StringPool.EMPTY;

    /**
     * 接口作者
     */
    private String author = StringPool.EMPTY;

    /**
     * 接口注释
     */
    private String desc = StringPool.EMPTY;

    /**
     * 接口注解标注
     */
    private String tagNote = StringPool.EMPTY;

    /**
     * 参数列表
     */
    private List<MdTemplateParamPojo> inputParams = Lists.newArrayList();
    private List<MdTemplateParamPojo> outputParams = Lists.newArrayList();

    /**
     * 入参最大递归栈深
     */
    private int inputDeepMax;

    /**
     * 出参最大递归栈深
     */
    private int outputDeepMax;

    /**
     * 入参示例json
     */
    private String inputJson = StringPool.EMPTY;

    /**
     * 出参示例json
     */
    private String outputJson = StringPool.EMPTY;

    public MdTemplatePojo() {

    }

    public MdTemplatePojo(ApiMethodInfo apiMethodInfo, String index) {
        String title = apiMethodInfo.getName();
        String path = apiMethodInfo.getPath();
        String type = apiMethodInfo.getHttpTypeEnum().getValue();
        String author = apiMethodInfo.getAuthor();
        String desc = apiMethodInfo.getNote();
        String tagNote = apiMethodInfo.getTagValue();
        this.index = StringPool.nullEmpty(index);
        this.title = StringPool.nullEmpty(title);
        this.path = StringPool.nullEmpty(path);
        this.type = StringPool.nullEmpty(type);
        this.author = StringPool.nullEmpty(author);
        this.desc = StringPool.nullEmpty(desc);
        this.tagNote = StringPool.nullEmpty(tagNote);
    }

}
