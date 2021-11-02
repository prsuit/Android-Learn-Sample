package com.prsuit.androidlearnsample.generic;

import java.util.List;

/**
 * @Description:
 * @Author: sh
 * @Date: 2021/11/2
 */
public class NormalGeneric<T> {
    private T data;

    public NormalGeneric(T data) {
        this.data = data;
    }

    // 普通方法
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 泛型方法
    public <T> void genericMethod(T t) {

    }

    //泛型接口
    public interface genericInterface<T> {
        T method();
    }

   /*
   public void method(List<String> stringList){

    }

    public void method(List<Integer> integerList){

    }
    */
    //上面这段代码是不能被编译的，因为参数List＜Integer＞和List＜String＞编译之后都被擦除了，变成了一样的原生类型List＜E＞，
    // 擦除动作导致这两种方法的特征签名变得一模一样。
}
