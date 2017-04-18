package com.example.library.httpRequest;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ProjectName：IcexOne
 * Describe：使用注解
 * Author：Icex
 * CreationTime：2017/3/2
 */

@Documented //标记注解，被生成javadoc处理
@Retention(RetentionPolicy.RUNTIME)//运行是有效
@Target(ElementType.FIELD)//作用于成员变量
public @interface ParamNames {
    String value();
}
