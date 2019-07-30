package com.sjw.api4j.helper;

import com.google.common.collect.Lists;
import com.sjw.api4j.model.ApiTagClass;
import com.sjw.api4j.model.ApiTagMethod;
import com.sjw.api4j.model.ApiTagPojo;
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

    public static List<ApiTagClass> getAllMethods(String commonPath) {
        long start = System.currentTimeMillis();

        String rootPath = FileHelper.getRootPath();
        Collection<JavaClass> classes = FileHelper.getAllClassses(rootPath);
        List<ApiTagClass> result = Lists.newArrayList();

        for (JavaClass jc : classes) {
            ApiTagClass apiTagClass = new ApiTagClass(AnnotationHelper.getClassPath(jc, commonPath), jc.getComment());
            boolean isApiClass = false;
            List<JavaMethod> methods = jc.getMethods();
            //首先判断class是否标记
            List<JavaAnnotation> anns = jc.getAnnotations();
            ApiTagPojo apiTagPojo = AnnotationHelper.isApiTag(anns);
            if (null != apiTagPojo) {
                isApiClass = true;
                for (JavaMethod p : methods) {
                    ApiTagMethod apiTagMethod = new ApiTagMethod(p, apiTagPojo.getValue(), apiTagPojo.getProtocol());
                    apiTagClass.addMethods(apiTagMethod);
                }
            } else {
                //其次判断方法是否标记
                for (JavaMethod p : methods) {
                    List<JavaAnnotation> mAnns = p.getAnnotations();
                    ApiTagPojo apiTagPojoMethod = AnnotationHelper.isApiTag(mAnns);
                    if (null != apiTagPojoMethod) {
                        isApiClass = true;
                        ApiTagMethod apiTagMethod = new ApiTagMethod(p, apiTagPojoMethod.getValue(), apiTagPojoMethod.getProtocol());
                        apiTagClass.addMethods(apiTagMethod);
                    }
                }
            }
            if (isApiClass) {
                result.add(apiTagClass);
            }

        }

        SysLogUtil.duration("read all java class and methods", start);
        return result;
    }

    /**
     * 解析方法
     * @param apiTagMethod
     */
    public static void analyMethod(ApiTagMethod apiTagMethod){

    }
}
