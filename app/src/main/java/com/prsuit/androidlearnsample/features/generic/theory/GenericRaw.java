package com.prsuit.androidlearnsample.features.generic.theory;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.prsuit.androidlearnsample.features.generic.wildchar.Apple;
import com.prsuit.androidlearnsample.features.generic.wildchar.ChildType;
import com.prsuit.androidlearnsample.features.generic.wildchar.Fruit;
import com.prsuit.androidlearnsample.features.generic.wildchar.GenericType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程咨询芊芊老师  QQ：2130753077 VIP课程咨询 依娜老师  QQ：2133576719
 * 类说明：
 */
public class GenericRaw<T extends ArrayList&Comparable> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void test(){
        //(Comparable)data.compareTo()
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public static void main(String[] args) {
        ChildType apple = new ChildType();
//        ChildType<Fruit>  = new ChildType<Apple>();
        Type superclass = apple.getClass().getGenericSuperclass();
        System.out.println("main: -->"+superclass);
        ParameterizedType genericSuperclass = (ParameterizedType) (apple.getClass().getGenericSuperclass());
        Type actualTypeArgument = genericSuperclass.getActualTypeArguments()[0];
        System.out.println("main: -RawType->"+ genericSuperclass.getRawType());
        System.out.println("main: -actualType->"+actualTypeArgument);

        apple.getType();
    }
}
