import com.sjw.base.apidoc.ApiDocUtil;
import com.sjw.base.apidoc.conf.ApiDocConf;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by shijiawei on 2019/4/1.
 */
public class Sjwtest {

    public static void main(String[] args) throws IOException, TemplateException {

    }


    /**
     * 指定扫描路径为本项目
     */
    private static void defaultDo(){
        String rootPath = "/Users/javaproject/project";
        ApiDocConf apiDocConf = ApiDocConf.customConf(rootPath);
        apiDocConf.addMethod("ClassName1.MethodName1");
        apiDocConf.addMethod("ClassName1.MethodName2");
        ApiDocUtil.makeApiDoc(apiDocConf);



        apiDocConf.mdSet().setCommonPath("service-test");
//        apiDocConf.setDocOutputPath("/Users/shijiawei/javaProject/sjwPerson/Sjw_Java/src/main/resources/test");
        ApiDocUtil.makeApiDoc(apiDocConf);

    }

    /**
     * 指定扫描路径为本项目
     */


}
