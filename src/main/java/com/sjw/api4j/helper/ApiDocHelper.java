package com.sjw.api4j.helper;

import com.google.common.collect.Lists;
import com.sjw.api4j.constants.ClassNameConstant;
import com.sjw.api4j.model.ApiTagMethod;
import com.sjw.api4j.utils.StringPool;
import com.sjw.api4j.utils.SysLogUtil;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * @author shijiawei
 * @version ApiDocHelper.java -> v 1.0
 * @date 2019/7/28
 */
public class ApiDocHelper {

    private static final String API_TAG_VALUE = "value";

    public static List<ApiTagMethod> getAllMethods() {
        String rootPath = FileHelper.getRootPath();
        long start = System.currentTimeMillis();
        JavaProjectBuilder rootBuilder = new JavaProjectBuilder();
        rootBuilder.setEncoding(StringPool.UTF_8);
        rootBuilder.addSourceTree(new File(rootPath));
        Collection<JavaClass> classes = rootBuilder.getClasses();
        List<JavaMethod> allMethods = Lists.newArrayList();
        List<ApiTagMethod> apiMethods = Lists.newArrayList();
        for (JavaClass jc : classes) {
            List<JavaMethod> methodList = jc.getMethods();
            allMethods.addAll(methodList);
        }
        allMethods.stream().forEach(p -> {
            List<JavaAnnotation> anns = p.getAnnotations();
            anns.stream().forEach(a -> {
                if (ClassNameConstant.API_TAG_NAME.equals(a.getType().toString())) {
                    apiMethods.add(new ApiTagMethod(p, a.getProperty(API_TAG_VALUE).toString()));
                }
            });
        });
        SysLogUtil.duration("read all java methods", start);
        return apiMethods;
    }
}
