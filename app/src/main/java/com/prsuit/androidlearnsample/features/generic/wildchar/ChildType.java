package com.prsuit.androidlearnsample.features.generic.wildchar;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Description:
 * @Author: sh
 * @Date: 2021/12/3
 */
public class ChildType extends GenericType<Apple>{
    public void getType() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        System.out.println("--getType actualTyp->"+actualTypeArgument);
    }
}
