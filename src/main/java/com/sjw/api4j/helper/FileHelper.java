package com.sjw.api4j.helper;

import com.sjw.api4j.utils.StringPool;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Collection;

/**
 * @author shijiawei
 * @version FileHelper.java -> v 1.0
 * @date 2019/7/17
 */
public class FileHelper {
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
            javaProjectBuilder.addSourceTree(new File(path));
        }
        Collection<JavaClass> classes = javaProjectBuilder.getClasses();
        return classes;
    }
}
