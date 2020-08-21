package com.prsuit.androidlearnsample.ipc.proxy;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.prsuit.androidlearnsample.Book;
import com.prsuit.androidlearnsample.ipc.server.BookManager;
import com.prsuit.androidlearnsample.ipc.server.Stub;

import java.util.List;

/**
 * @Description: Binder代理类
 * @Author: sh
 * @Date: 2020/8/21
 */
public class Proxy implements BookManager {

    private static final String DESCRIPTOR = "com.prsuit.androidlearnsample.ipc.bookManager";
    private IBinder mRemote;

    public Proxy(IBinder mRemote) {
        this.mRemote = mRemote;
    }

    @Override
    public List<Book> getBooks() throws RemoteException {
        //生成 _data输入数据流，存入客户端传的参数数据。
        Parcel _data = Parcel.obtain();
        // _reply 输出数据流，存服务端返回数据
        Parcel _reply = Parcel.obtain();
        List<Book> _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            //0表示双向数据流
            mRemote.transact(Stub.TRANSACTION_getBooks, _data, _reply, 0);
            _reply.readException();
            //接收 _reply 数据流，并从中取出服务端传回来的数据。
            _result = _reply.createTypedArrayList(Book.CREATOR);
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            if (book != null){
                //有参数
                _data.writeInt(1);
                book.writeToParcel(_data,0);
            } else {
                //无参数
                _data.writeInt(0);
            }
            mRemote.transact(Stub.TRANSACTION_addBook,_data,_reply,0);
            _reply.readException();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }

    public java.lang.String getInterfaceDescriptor()
    {
        return DESCRIPTOR;
    }
}
