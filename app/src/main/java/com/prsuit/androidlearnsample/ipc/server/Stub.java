package com.prsuit.androidlearnsample.ipc.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.prsuit.androidlearnsample.Book;
import com.prsuit.androidlearnsample.ipc.proxy.Proxy;

import java.util.List;

/**
 * @Description: 服务端处理请求类
 * @Author: sh
 * @Date: 2020/8/21
 */
public abstract class Stub extends Binder implements BookManager{

    private static final String DESCRIPTOR = "com.prsuit.androidlearnsample.ipc.bookManager";

    public Stub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static BookManager asInterface(IBinder binder){
        return new Proxy(binder);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code){
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
            case TRANSACTION_getBooks:
                data.enforceInterface(DESCRIPTOR);
                //调用具体方法
                List<Book> _result = this.getBooks();
                reply.writeNoException();
                //写入返回数据
                reply.writeTypedList(_result);
                return true;
            case TRANSACTION_addBook:
                data.enforceInterface(DESCRIPTOR);
                Book _arg0;
                if (0!= data.readInt()){
                    _arg0 = Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.addBook(_arg0);
                reply.writeNoException();
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    public static final int TRANSACTION_getBooks = FIRST_CALL_TRANSACTION + 0;
    public static final int TRANSACTION_addBook = FIRST_CALL_TRANSACTION + 1;
}
