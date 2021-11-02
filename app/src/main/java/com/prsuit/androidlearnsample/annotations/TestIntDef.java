package com.prsuit.androidlearnsample.annotations;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 使用@IntDef限定参数
 * @Author: sh
 * @Date: 2021/11/2
 */
public class TestIntDef {
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

    @IntDef({TYPE_1,TYPE_2})
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    public void setType(@Type int type) {

    }

    public void test(){
        setType(TYPE_1);
    }
}
