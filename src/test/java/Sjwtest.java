import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sjw.api4j.model.template.MdTemplatePojo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * Created by shijiawei on 2019/4/1.
 */
public class Sjwtest {

    public static void main(String[] args) throws IOException, TemplateException {
        // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File("/Users/shijiawei/javaProject/sjwOpen/api4j/src/test/resources/template"));
        // 第三步：设置模板文件使用的字符集。一般就是utf-8.
        configuration.setDefaultEncoding("utf-8");
        // 第四步：加载一个模板，创建一个模板对象。
        Template template = configuration.getTemplate("test.ftl");
        // 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
        Map<String, Object> dataModel = Maps.newHashMap();
        //向数据集中添加数据
        List<MdTemplatePojo> list = Lists.newArrayList();
        MdTemplatePojo pojo = new MdTemplatePojo();
        pojo.setIndex("1.1");
        pojo.setTitle("查询用户姓名");
        pojo.setPath("/service/mvc-controller/getTest2");
        pojo.setType("POST");
        pojo.setAuthor("shijiawei");
        pojo.setDesc("查询用户姓名接口注释");
        pojo.setTagNote("查询用户姓名接口标注");
        MdTemplatePojo pojo2 = new MdTemplatePojo();
        pojo2.setIndex("1.2");
        pojo2.setTitle("查询用户姓名");
        pojo2.setPath("/service/mvc-controller/getTest2");
        pojo2.setType("POST");
        pojo2.setAuthor("shijiawei");
        pojo2.setDesc("查询用户姓名接口注释");
        pojo2.setTagNote("查询用户姓名接口标注");
        list.add(pojo);
        list.add(pojo2);
        dataModel.put("pojos", list);
        // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        Writer out = new FileWriter(new File("/Users/shijiawei/javaProject/sjwOpen/api4j/src/test/resources/doc/test_doc.md"));
        // 第七步：调用模板对象的process方法输出文件。
        template.process(dataModel, out);
        // 第八步：关闭流。
        out.close();
        System.out.println("执行完成");

    }


}
