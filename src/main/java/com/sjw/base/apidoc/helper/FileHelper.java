package com.sjw.base.apidoc.helper;

import com.sjw.base.apidoc.expection.ApiForjException;
import com.sjw.base.apidoc.utils.DateUtil;
import com.sjw.base.apidoc.utils.StringPool;
import com.sjw.base.apidoc.utils.SysLogUtil;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.parser.ParseException;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * @author shijiawei
 * @version FileHelper.java -> v 1.0
 * @date 2019/7/17
 */
public class FileHelper {

    private static final Configuration FREEMARKER_CONF = new Configuration(Configuration.getVersion());

    private static final String DEFAULT_FM_TEMPLATE_NAME = "template_doc.ftl";

    private static final String DEFAULT_RESOURCE_PATH = "/src/test/resources/apidoc";

    static {
        FREEMARKER_CONF.setTemplateLoader(new ClassTemplateLoader(FileHelper.class, "/template"));
        FREEMARKER_CONF.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        FREEMARKER_CONF.setDefaultEncoding(StringPool.UTF_8);
    }

    /**
     * 获取当前根目录
     */
    public static String getRootPath() {
        return System.getProperty("user.dir");
    }


    public static Collection<JavaClass> getAllClassses(String... paths) {
        JavaProjectBuilder javaProjectBuilder = new JavaProjectBuilder();
        javaProjectBuilder.setEncoding(StringPool.UTF_8);
        for (String path : paths) {
            if (StringUtils.isBlank(path)) {
                continue;
            }
            try {
                javaProjectBuilder.addSourceTree(new File(path));
            } catch (ParseException e) {
                SysLogUtil.info("扫描文件发生错误,请保证不要有java文件是被注释状态 msg : " + e.getMessage());
            }
        }
        Collection<JavaClass> classes = javaProjectBuilder.getClasses();
        return classes;
    }

    public static Template getFmTemplate(String templateName) {
        try {
            return FREEMARKER_CONF.getTemplate(templateName);
        } catch (IOException e) {
            SysLogUtil.info("get fm template io error : ", e.getMessage());
            throw ApiForjException.GET_FM_TEMPLATE_IO_ERROR;
        }
    }

    public static Template getFmTemplate() {
        try {
            return FREEMARKER_CONF.getTemplate(DEFAULT_FM_TEMPLATE_NAME);
        } catch (IOException e) {
            SysLogUtil.info("get fm template io error : ", e.getMessage());
            throw ApiForjException.GET_FM_TEMPLATE_IO_ERROR;
        }
    }

    /**
     * 获取生成文档
     * 如果未指定 就生成在项目的 test resources下
     */
    public static File getOutputDocFile(String path) {
        String docName = "/api_doc_" + DateUtil.getTimeNowStr() + ".md";
        if (StringUtils.isBlank(path)) {
            path = getRootPath() + DEFAULT_RESOURCE_PATH;
        } else {
            if (path.endsWith(StringPool.SLASH)) {
                path = path.substring(0, path.length() - 1);
            }
        }
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.mkdir();
        }
        return new File(path + docName);
    }
}
