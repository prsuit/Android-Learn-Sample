package com.prsuit.androidlearnsample.ipc.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.prsuit.androidlearnsample.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 服务端
 * @Author: sh
 * @Date: 2020/8/21
 */
public class RemoteService extends Service {

    private List<Book> books = new ArrayList<>();
    private Stub bookManager = new Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            //每个Binder的Server进程会创建很多线程来处理Binder请求,是由Binder驱动进行管理的,最大是16
            synchronized (this){
                if (books != null){
                    return books;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this){
                if (book == null){
                    return;
                }
                book.setPrice(book.getPrice() * 2);
                books.add(book);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("Android");
        book.setPrice(2);
        books.add(book);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }
}
