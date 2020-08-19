// IBookManager.aidl
package com.prsuit.androidlearnsample;
import com.prsuit.androidlearnsample.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
       //有返回值前都不需要加任何东西，不管是什么数据类型
        List<Book> getBooks();

        //如果有传输对象载体，就必须指明定向tag(in,out,inout)
        Book addBookIn(in Book book);//客户端->服务端
        //out和inout都需要重写MessageBean的readFromParcel方法
        Book addBookOut(out Book msg);//服务端->客户端
        Book addBookInOut(inout Book msg);//客户端<->服务端

}
