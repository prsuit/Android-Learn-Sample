package com.prsuit.androidlearnsample.ipc.server;

import android.os.IInterface;
import android.os.RemoteException;

import com.prsuit.androidlearnsample.Book;

import java.util.List;

/**
 * @Description: 这个类用来定义服务端 RemoteService 具备什么样的能力
 * @Author: sh
 * @Date: 2020/8/21
 */
public interface BookManager extends IInterface {
    List<Book> getBooks() throws RemoteException;
    void addBook(Book book) throws RemoteException;
}
