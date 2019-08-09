package com.sjw.api4j.helper;

import com.alibaba.fastjson.JSONObject;
import com.sjw.api4j.model.BaseParams;
import com.sjw.api4j.utils.StringPool;

import java.util.List;

/**
 * @author shijiawei
 * @version JsonMockHelper.java -> v 1.0
 * @date 2019/8/6
 * mock json请求或者返回的数据
 */
public class JsonMockHelper {

    public static String makeMockJson(BaseParams baseParams) {
        if (null == baseParams) {
            return StringPool.EMPTY;
        }
        return null;
    }


    private static void circleGetJson(BaseParams baseParams, JSONObject jsonObject) {
        boolean isArray = baseParams.isArray();
        String name = baseParams.getName();
        List<BaseParams> childs = baseParams.getChilds();
    }
}
