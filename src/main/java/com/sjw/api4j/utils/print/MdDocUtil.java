package com.sjw.api4j.utils.print;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sjw.api4j.helper.DocPrintHelper;
import com.sjw.api4j.helper.JsonMockHelper;
import com.sjw.api4j.model.ApiClassInfo;
import com.sjw.api4j.model.ApiMethodInfo;
import com.sjw.api4j.model.BaseParams;
import com.sjw.api4j.model.template.MdTemplateParamPojo;
import com.sjw.api4j.model.template.MdTemplatePojo;
import com.sjw.api4j.utils.DateUtil;
import com.sjw.api4j.utils.SysLogUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * @author shijiawei
 * @version MdDocUtil.java -> v 1.0
 * @date 2019/8/16
 * macdown文档输出工具
 */
public class MdDocUtil {

    public static void print(List<ApiClassInfo> pojos, String outputPath) {
        List<MdTemplatePojo> mdTemplatePojos = Lists.newArrayList();
        pojos.stream().forEach(pojo -> {
            int methodIndex = 1;
            for (ApiMethodInfo apiMethodInfo : pojo.getMethodInfos()) {
                if (null == apiMethodInfo) {
                    continue;
                }
                //计算序号
                String index = DocPrintHelper.getIndex(pojo.getClassIndex(), methodIndex);
                //构建输出的各项参数
                MdTemplatePojo mdTemplatePojo = buildTemplatePojo(apiMethodInfo, index);
                mdTemplatePojos.add(mdTemplatePojo);
                methodIndex++;
            }
        });
        //执行文档生成
        doMdTemplate(mdTemplatePojos);
    }

    /**
     * 生成单个数据model
     */
    private static MdTemplatePojo buildTemplatePojo(ApiMethodInfo apiMethodInfo, String index) {
        MdTemplatePojo result = new MdTemplatePojo(apiMethodInfo, index);
        BaseParams baseParamsInput = apiMethodInfo.getInputParams();
        BaseParams baseParamsOutput = apiMethodInfo.getOutputParams();

        //获取最大递归深度 1就是没有递归
        int deepCircleMaxInput = baseParamsInput.getCircleDeep();
        int deepCircleMaxOutput = baseParamsOutput.getCircleDeep();
        result.setInputDeepMax(deepCircleMaxInput);
        result.setOutputDeepMax(deepCircleMaxOutput);
        List<MdTemplateParamPojo> paramsInput = Lists.newArrayList();
        List<MdTemplateParamPojo> paramsOutput = Lists.newArrayList();
        //压栈递归取参数列表
        if (baseParamsInput.isJavaType()) {
            //不是对象入参也就是Get请求
            baseParamsInput.getChilds().stream().forEach(p -> buildParam(p, paramsInput, 0, deepCircleMaxInput));
        } else {
            buildParam(baseParamsInput, paramsInput, 0, deepCircleMaxInput);
        }
        buildParam(baseParamsOutput, paramsOutput, 0, deepCircleMaxOutput);
        result.setInputParams(paramsInput);
        result.setOutputParams(paramsOutput);
        if (JsonMockHelper.isNeedJsonInput(apiMethodInfo.getHttpTypeEnum().isGet(), baseParamsInput)) {
            result.setInputJson(JsonMockHelper.makeMockJson(baseParamsInput));
        }
        if (JsonMockHelper.isNeedJsonOutput(baseParamsOutput)) {
            result.setOutputJson(JsonMockHelper.makeMockJson(baseParamsOutput));
        }
        return result;
    }


    /**
     * 递归压栈
     */
    private static void buildParam(BaseParams baseParams, List<MdTemplateParamPojo> result, int deep, int deepMax) {
        if (null == baseParams) {
            return;
        }
        MdTemplateParamPojo mdTemplateParamPojo = new MdTemplateParamPojo(baseParams);
        //计算md渲染的各项参数
        mdTemplateParamPojo.setSjNum(deepMax - deep);
        mdTemplateParamPojo.setHbNum(deep);

        result.add(mdTemplateParamPojo);
        List<BaseParams> childs = baseParams.getChilds();
        if (CollectionUtils.isNotEmpty(childs)) {
            childs.stream().forEach(p -> buildParam(p, result, deep + 1, deepMax));
        }
    }


    /**
     * 执行模板文档生成
     */
    private static void doMdTemplate(List<MdTemplatePojo> mdTemplatePojos) {
        if (CollectionUtils.isEmpty(mdTemplatePojos)) {
            SysLogUtil.info("md no doc to output");
            return;
        }
        try {
            Configuration configuration = new Configuration(Configuration.getVersion());
            //todo 需要弄清楚如何在本项目中获取模板路径  和如何在被依赖的项目中获取本项目的路径???
            configuration.setDirectoryForTemplateLoading(new File("/Users/shijiawei/javaProject/sjwOpen/api4j/src/main/resources/template"));
            configuration.setDefaultEncoding("utf-8");
            Template template = configuration.getTemplate("template_doc.ftl");
            Map<String, Object> dataModel = Maps.newHashMap();
            dataModel.put("pojos", mdTemplatePojos);
            //todo 需要弄清楚如何在本项目中获取模板路径  和如何在被依赖的项目中获取本项目的路径???
            String docName = "test_doc_" + DateUtil.getTimeNowStr() + ".md";
            Writer out = new FileWriter(new File("/Users/shijiawei/javaProject/sjwOpen/api4j/src/test/resources/doc/" + docName));
            template.process(dataModel, out);
            out.close();
        } catch (Exception e) {
            SysLogUtil.info("do md doc out error -> msg={0} , stack={1}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
    }


}
