package com.sjw.api4j.helper;

import com.google.common.collect.Lists;
import com.sjw.api4j.model.ApiTagClass;
import com.sjw.api4j.model.ApiTagMethod;
import com.sjw.api4j.utils.StringPool;
import com.sjw.api4j.utils.SysLogUtil;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import java.util.Collection;
import java.util.List;

/**
 * @author shijiawei
 * @version ApiDocHelper.java -> v 1.0
 * @date 2019/7/28
 */
public class ApiDocHelper {

    private static final String API_TAG_VALUE = "value";

    public static List<ApiTagClass> getAllMethods(String commonPath) {
        long start = System.currentTimeMillis();

        String rootPath = FileHelper.getRootPath();
        Collection<JavaClass> classes = FileHelper.getAllClassses(rootPath);
        List<ApiTagClass> result = Lists.newArrayList();

        for (JavaClass jc : classes) {
            List<JavaMethod> methods = jc.getMethods();

            //首先判断class是否标记
            List<JavaAnnotation> anns = jc.getAnnotations();
            if (AnnotationHelper.isApiTag(anns)) {
                methods.stream().forEach(p -> {
                    ApiTagMethod apiTagMethod = new ApiTagMethod(p, StringPool.EMPTY);
                    ApiTagClass apiTagClass = new ApiTagClass();
                    apiTagClass.addMethods(apiTagMethod);
                    result.add(apiTagClass);
                });
            } else {
                //其次判断方法是否标记
                methods.stream().forEach(p -> {
                    List<JavaAnnotation> mAnns = p.getAnnotations();
                    if (AnnotationHelper.isApiTag(mAnns)) {
                        ApiTagMethod apiTagMethod = new ApiTagMethod(p, StringPool.EMPTY);
                        ApiTagClass apiTagClass = new ApiTagClass();
                        apiTagClass.addMethods(apiTagMethod);
                        result.add(apiTagClass);
                    }
                });
            }

        }
//        allMethods.stream().forEach(p -> {
//            List<JavaAnnotation> anns = p.getAnnotations();
//            anns.stream().forEach(a -> {
//                if (ClassNameConstant.API_TAG_NAME.equals(a.getType().toString())) {
//                    apiMethods.add(new ApiTagMethod(p, a.getProperty(API_TAG_VALUE).toString()));
//                }
//            });
//        });

        SysLogUtil.duration("read all java class and methods", start);
        return null;
    }
}
