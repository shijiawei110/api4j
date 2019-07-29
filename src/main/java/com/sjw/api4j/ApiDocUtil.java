package com.sjw.api4j;

import com.sjw.api4j.conf.ApiDocConf;
import com.sjw.api4j.helper.ApiDocHelper;
import com.sjw.api4j.model.ApiTagClass;
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

    public static void main(String[] args) {
        ApiDocConf apiDocConf = new ApiDocConf();
        makeApiDoc(apiDocConf);
    }

    public static void makeApiDoc(ApiDocConf apiDocConf) {
        if (null == apiDocConf) {
            apiDocConf = new ApiDocConf();
        }
        doMakeApiDoc(apiDocConf);
    }

    public static void makeApiDoc() {
        doMakeApiDoc(new ApiDocConf());
    }

    private static void doMakeApiDoc(ApiDocConf apiDocConf) {
        SysLogUtil.sysStart();
        //获取方法集
        List<ApiTagClass> apiTagClassList = ApiDocHelper.getAllMethods(apiDocConf.getCommonPath());
        if (CollectionUtils.isEmpty(apiTagClassList)) {
            SysLogUtil.info("没有找到注解标记的需要生成文档的方法");
            return;
        }
        //将包装method build成参数集

        apiTagClassList.stream().forEach(p->{
            System.out.println(p.toString());
        });

        SysLogUtil.sysEnd();
    }

}
