package com.prsuit.androidlearnsample.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.prsuit.androidlearnsample.Book;
import com.prsuit.androidlearnsample.IBookManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 服务端的AIDLService.java
 * @Author: sh
 * @Date: 2020/8/19
 */
public class AIDLService extends Service {

    private final String TAG = "AIDLService";
    private List<Book> mBooks = new ArrayList<>();

    //由AIDL文件生成的BookManager
    private IBookManager.Stub mBookManager = new IBookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this) {
                Log.e(TAG, "getBooks: " + mBooks.toString());
                if (mBooks != null) {
                    return mBooks;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public Book addBookIn(Book book) throws RemoteException {
            synchronized(this) {
                Log.e(TAG, "addBookIn: 接收到的对象-->" + book.toString());
                if (book == null) {
                    Log.e(TAG, "addBookIn: book is null in In");
                    book = new Book();
                }
                //尝试修改book的参数，主要是为了观察其到客户端的反馈
                book.setPrice(2333);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                Log.e(TAG, "addBookIn: now the list is " + mBooks.toString());
                return book;
            }
        }

        @Override
        public Book addBookOut(Book book) throws RemoteException {
            synchronized(this) {
                Log.e(TAG, "addBookOut: 接收到的对象-->" + book.toString());
                if (book == null) {
                    Log.e(TAG, "addBookOut: book is null in Out");
                    book = new Book();
                }
                //尝试修改book的参数，主要是为了观察其到客户端的反馈
                book.setPrice(2333);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                Log.e(TAG, "addBookOut: now the list is " + mBooks.toString());
                return book;
            }
        }

        @Override
        public Book addBookInOut(Book book) throws RemoteException {
            synchronized(this) {
                Log.e(TAG, "addBookInOut: 接收到的对象-->" + book.toString());
                if (book == null) {
                    Log.e(TAG, "addBookInOut: book is null in InOut");
                    book = new Book();
                }
                //尝试修改book的参数，主要是为了观察其到客户端的反馈
                book.setPrice(2333);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                Log.e(TAG, "addBookInOut: now the list is " + mBooks.toString());
                return book;
            }
        }
    };

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: AIDLService" );
        super.onCreate();
        Book book = new Book();
        book.setName("Android 开发艺术探索");
        book.setPrice(20);
        mBooks.add(book);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: AIDLService" );
        return mBookManager;
    }
}
