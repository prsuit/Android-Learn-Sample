package com.prsuit.androidlearnsample.annotations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @Description:
 * @Author: sh
 * @Date: 2021/11/2
 */
public class InjectUtils {
    public static void injectView(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        //获得此类所有的成员
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            //判断成员是否被InjectView注解声明
            if (field.isAnnotationPresent(InjectView.class)) {
                //获取InjectView注解
                InjectView injectView = field.getAnnotation(InjectView.class);
                //获取注解中设置的值id
                int id = injectView.value();
                View view = activity.findViewById(id);
                //设置访问权限允许访问private的属性
                field.setAccessible(true);
                try {
                    //反射设置属性的值
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 获取Intent的getExtras值自动赋给成员变量
    public static void injectAutoWired(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        //获取数据
        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        //获取类的所有成员
        Field[] declaredFields = cls.getDeclaredFields();
        String key;
        for (Field field : declaredFields) {
            //判断是否标有自定义AutoWired注解
            if (field.isAnnotationPresent(AutoWired.class)) {
                AutoWired autoWired = field.getAnnotation(AutoWired.class);
                //获取注解上的值
                key = TextUtils.isEmpty(autoWired.value()) ? field.getName() : autoWired.value();
                //从extras中获取key对应的值
                if (extras.containsKey(key)) {
                    Object obj = extras.get(key);
                    // Parcelable数组类型Parcelable[]不能直接设置，其他的都可以.
                    //获得数组单个元素类型
                    Class<?> componentType = field.getType().getComponentType();
                    //当前属性是数组并且是 Parcelable（子类）数组
                    if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        //转成数组
                        Object[] objs = (Object[]) obj;
                        //创建对应类型的数组并由objs拷贝
                        Object[] objects = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) field.getType());
                        obj = objects;
                    }
                    //设置访问权限
                    field.setAccessible(true);
                    try {
                        field.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
