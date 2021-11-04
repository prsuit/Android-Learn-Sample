package com.prsuit.androidlearnsample.features.generic.theory;

import java.util.List;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程咨询芊芊老师  QQ：2130753077 VIP课程咨询 依娜老师  QQ：2133576719
 * 类说明：
 */
public class Conflict {

    public static String method(List<String> stringList){
        System.out.println("List");
        return "OK";
    }

//    public static Integer method(List<Integer> stringList){
//        System.out.println("List");
//        return 1;
//    }

//    上面这段代码是不能被编译的，因为参数List＜Integer＞和List＜String＞编译之后都被擦除了，变成了一样的原生类型List＜E＞，
//    擦除动作导致这两种方法的特征签名变得一模一样。

    /*
    * Signature (弱记忆)
    *
    * ? super xxxx
    *
    * */

}
