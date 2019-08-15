package com.sjw.api4j.utils.print;

import com.sjw.api4j.helper.JsonMockHelper;
import com.sjw.api4j.model.ApiMethodInfo;
import com.sjw.api4j.model.ApiTagClass;
import com.sjw.api4j.model.BaseParams;
import com.sjw.api4j.utils.StringPool;
import com.sjw.api4j.utils.SysLogUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author shijiawei
 * @version ConsoleUtil.java -> v 1.0
 * @date 2019/8/5
 * 最终文档输出util -- 控制台
 */
public class ConsoleUtil {

    private static final int JG_NUM = 15;
    private static final int TOP_NUM = 10;
    private static final int ALL_NUM = JG_NUM + TOP_NUM;
    private static final String CIRCLR_SJ = "- ";

    public static void print(ApiTagClass apiTagClass, List<ApiMethodInfo> apiMethodInfos) {
        String title = "controller name : " + apiTagClass.getControllerName();
        SysLogUtil.title(title);
        apiMethodInfos.forEach(p -> {
            printConsoleMethod(p);
            SysLogUtil.fg();
        });
    }

    private static void printConsoleMethod(ApiMethodInfo apiMethodInfo) {
        //common
        SysLogUtil.apiParamKv("接口名称", apiMethodInfo.getName());
        SysLogUtil.apiParamKv("接口路径", apiMethodInfo.getPath());
        SysLogUtil.apiParamKv("接口类型", apiMethodInfo.getHttpTypeEnum().getValue());
        SysLogUtil.apiParamKv("接口作者", apiMethodInfo.getAuthor());
        SysLogUtil.apiParamKv("接口注释", apiMethodInfo.getNote());
        SysLogUtil.apiParamKv("接口标注", apiMethodInfo.getTagValue());
        SysLogUtil.ln();
        BaseParams input = apiMethodInfo.getInputParams();
        BaseParams output = apiMethodInfo.getOutputParams();
        //input params
        printConsoleInput(input, apiMethodInfo.getHttpTypeEnum().isGet());
        //output params
        printConsoleOutput(apiMethodInfo.getOutputParams());
        //input json demo -> post请求才有
        if (JsonMockHelper.isNeedJsonInput(apiMethodInfo.getHttpTypeEnum().isGet(), input)) {
            SysLogUtil.reqDemo();
            SysLogUtil.infoNoLn(JsonMockHelper.makeMockJson(input));
            SysLogUtil.ln();
        }
        //output json demo  -> 返回为pojo或者 list<pojo>
        if (JsonMockHelper.isNeedJsonOutput(output)) {
            SysLogUtil.resDemo();
            SysLogUtil.infoNoLn(JsonMockHelper.makeMockJson(output));
            SysLogUtil.ln();
        }


    }

    private static void printConsoleInput(BaseParams input, boolean isGet) {
        SysLogUtil.request();
        printInputTop();
        if (null == input) {
            return;
        }
        if (isGet) {
            List<BaseParams> params = input.getChilds();
            if (CollectionUtils.isEmpty(params)) {
                return;
            }
            params.forEach(p -> printRecord(p, true, null));
        } else {
            circlePrint(input, true, 0);

        }
        SysLogUtil.ln();
    }

    private static void printConsoleOutput(BaseParams output) {
        SysLogUtil.response();
        if (null == output) {
            //void
            recordPrint("此方法出参为void");
            return;
        }
        printOutputTop();
        circlePrint(output, false, 0);
        SysLogUtil.ln();
    }


    private static void circlePrint(BaseParams baseParams, boolean isInput, int sjCount) {
        if (null == baseParams) {
            return;
        }
        StringBuilder sj = new StringBuilder(StringPool.EMPTY);
        for (int i = 0; i < sjCount; i++) {
            sj.append(CIRCLR_SJ);
        }
        printRecord(baseParams, isInput, sj.toString());
        List<BaseParams> childs = baseParams.getChilds();
        if (CollectionUtils.isNotEmpty(childs)) {
            for (BaseParams child : childs) {
                circlePrint(child, isInput, sjCount + 1);
            }
        }

    }


    private static void printInputTop() {
        topPrint("参数名称");
        topPrint("参数类型");
        topPrint("是否必须");
        topPrint("是否数组");
        topPrint("长度限制");
        topPrint("参数注释");
        SysLogUtil.ln();
    }

    private static void printOutputTop() {
        topPrint("参数名称");
        topPrint("参数类型");
        topPrint("是否数组");
        topPrint("参数注释");
        SysLogUtil.ln();
    }

    /**
     * 输出表记录
     */
    private static void printRecord(BaseParams baseParams, boolean isInput, String sj) {
        if (null == baseParams) {
            return;
        }
        String name = baseParams.getName();
        //填充名称
        if (StringUtils.isBlank(name)) {
            name = baseParams.getTypeAbbr();
        }
        if (StringUtils.isNotBlank(sj)) {
            name = sj + name;
        }
        recordPrint(name);
        if (baseParams.isJavaType()) {
            recordPrint(baseParams.getTypeAbbr());
        } else {
            recordPrint("[Bean]");
        }
        if (isInput) {
            recordPrint(String.valueOf(baseParams.isRequired()));
        }

        recordPrint(String.valueOf(baseParams.isArray()));
        if (isInput) {
            recordPrint(baseParams.getLength());
        }
        recordPrint(baseParams.getDesc());
        SysLogUtil.ln();
    }


    private static void topPrint(String top) {
        int topSize = top.length();
        int dif = TOP_NUM - topSize - 3;
        SysLogUtil.infoNoLn(top);
        SysLogUtil.jg(dif);
        SysLogUtil.jg(JG_NUM);
    }

    private static void recordPrint(String content) {
        int length = 0;
        if (StringUtils.isBlank(content)) {
            length = 0;
        } else {
            length = content.length();
        }
        int dif = ALL_NUM - length;
        SysLogUtil.infoNoLn(content);
        SysLogUtil.jg(dif);
    }


}
