package com.sjw.api4j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : shijiawei
 * Date: 2019/7/15
 * Description: 需要使用api4j的方法
 * 源码级别,编译后注解消失
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface ApiTag {
    String value();
}
