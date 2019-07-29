package com.sjw.api4j;

import com.sjw.api4j.helper.ApiDocHelper;
import com.sjw.api4j.model.ApiTagMethod;
import com.sjw.api4j.utils.SysLogUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author shijiawei
 * @version ApiDocUtil.java -> v 1.0
 * @date 2019/7/27
 * 主入口程序
 * 1:可以再控制台输出
 * 2:也可以输出到文件
 */
public class ApiDocUtil {
    public static void makeApiDoc() {
        SysLogUtil.sysStart();
        //获取方法集
        List<ApiTagMethod> methods = ApiDocHelper.getAllMethods();
        if (CollectionUtils.isEmpty(methods)) {
            SysLogUtil.info("没有找到注解标记的需要生成文档的方法");
            return;
        }
        //将包装method build成参数集

        SysLogUtil.sysEnd();
    }

    public static void main(String[] args) {
        makeApiDoc();
    }
}
