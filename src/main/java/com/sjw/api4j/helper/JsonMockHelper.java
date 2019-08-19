package com.sjw.api4j.helper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.sjw.api4j.model.BaseParams;
import com.sjw.api4j.utils.DataFakerUtil;
import com.sjw.api4j.utils.StringPool;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author shijiawei
 * @version JsonMockHelper.java -> v 1.0
 * @date 2019/8/6
 * mock json请求或者返回的数据
 */
public class JsonMockHelper {

    private static final int ARRAY_NUM = 2;

    public static boolean isNeedJsonInput(boolean isGet, BaseParams baseParams) {
        if (isGet) {
            return false;
        }
        if (null == baseParams) {
            return false;
        }
        if (CollectionUtils.isEmpty(baseParams.getChilds())) {
            return false;
        }
        return true;
    }

    public static boolean isNeedJsonOutput(BaseParams baseParams) {
        if (null == baseParams) {
            return false;
        }
        if (baseParams.isJavaType()) {
            return false;
        }
        if (CollectionUtils.isEmpty(baseParams.getChilds())) {
            return false;
        }
        return true;
    }


    public static String makeMockJson(BaseParams baseParams) {
        if (null == baseParams) {
            return StringPool.EMPTY;
        }
        boolean isArray = baseParams.isArray();
        List<BaseParams> childs = baseParams.getChilds();
        if (CollectionUtils.isEmpty(childs)) {
            return StringPool.EMPTY;
        }
        if (isArray) {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < ARRAY_NUM; i++) {
                jsonArray.add(circleGet(childs));
            }
            return formatJson(jsonArray.toJSONString());
        } else {
            JSONObject jsonObject = circleGet(childs);
            return formatJson(jsonObject.toJSONString());
        }
    }

    private static JSONObject circleGet(List<BaseParams> baseParamsList) {
        JSONObject jb = new JSONObject();
        for (BaseParams baseParams : baseParamsList) {
            if (baseParams.isJavaType()) {
                if (baseParams.isArray()) {
                    //基本类型数组
                    putValueList(jb, baseParams);
                } else {
                    //基本类型非数组
                    putValue(jb, baseParams);
                }
            } else {
                if (baseParams.isArray()) {
                    //自定义类数组
                    putCustomValueList(jb, baseParams);
                } else {
                    //自定义类非数组
                    putCustomValue(jb, baseParams);
                }
            }
        }
        return jb;
    }

    private static void putValue(JSONObject jsonObject, BaseParams baseParams) {
        String type = baseParams.getType();
        String name = baseParams.getName();
        int min = baseParams.getMinLimit();
        int max = baseParams.getMaxLimit();
        Object v = fakerValue(type, name, min, max);
        jsonObject.put(name, v);
    }

    private static void putValueList(JSONObject jsonObject, BaseParams baseParams) {
        JSONArray jsonArray = new JSONArray();
        String type = baseParams.getType();
        String name = baseParams.getName();
        int min = baseParams.getMinLimit();
        int max = baseParams.getMaxLimit();
        for (int i = 0; i < ARRAY_NUM; i++) {
            jsonArray.add(fakerValue(type, name, min, max));
        }
        jsonObject.put(name, jsonArray);
    }

    private static void putCustomValue(JSONObject jsonObject, BaseParams baseParams) {
        String name = baseParams.getName();
        jsonObject.put(name, circleGet(baseParams.getChilds()));
    }

    private static void putCustomValueList(JSONObject jsonObject, BaseParams baseParams) {
        JSONArray jsonArray = new JSONArray();
        String name = baseParams.getName();
        for (int i = 0; i < ARRAY_NUM; i++) {
            jsonArray.add(circleGet(baseParams.getChilds()));
        }
        jsonObject.put(name, jsonArray);
    }


    private static Object fakerValue(String type, String name, Integer min, Integer max) {
        if (ClassNameHelper.isString(type)) {
            return DataFakerUtil.getStr(name, min, max);
        } else if (ClassNameHelper.isInt(type)) {
            return DataFakerUtil.getInt(name, min, max);
        } else if (ClassNameHelper.isLong(type)) {
            return DataFakerUtil.getLong(name, min, max);
        } else if (ClassNameHelper.isBool(type)) {
            return DataFakerUtil.getBoolean();
        }
        return DataFakerUtil.getStr(name, min, max);
    }

    /**
     * 格式化json(gson)
     */
    private static String formatJson(String jsonRaw) {
        if (StringUtils.isBlank(jsonRaw)) {
            return StringPool.EMPTY;
        }
        JsonParser jsonParser = new JsonParser();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonParser.parse(jsonRaw));
    }
}
