package com.prsuit.androidlearnsample.annotations;

import androidx.annotation.IdRes;
import androidx.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 自定义注解
 * @Author: sh
 * @Date: 2021/11/1
 */
@Target(ElementType.FIELD) //标记注解的作用范围，Java元素类型范围
@Retention(RetentionPolicy.RUNTIME) //标记注解的存储方式，注解保留级别
public @interface InjectView {
    @IdRes int value();
}