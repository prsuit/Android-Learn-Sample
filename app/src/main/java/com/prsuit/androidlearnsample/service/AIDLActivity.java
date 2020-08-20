package com.prsuit.androidlearnsample.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.prsuit.androidlearnsample.Book;
import com.prsuit.androidlearnsample.IBookManager;
import com.prsuit.androidlearnsample.R;

import java.util.List;

/**
 * 客户端的AIDLActivity.java
 */
public class AIDLActivity extends AppCompatActivity {
    private final String TAG = "AIDLActivity";

    private IBookManager mBookManager = null;//服务端binder对象
    private boolean mBound = false;//与服务端连接
    private List<Book> mBooks;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBookManager = IBookManager.Stub.asInterface(service);
            mBound = true;
            if (mBookManager != null){
                try {
                  mBooks = mBookManager.getBooks();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
    }

    /**
     * 调用服务端的addBookIn方法
     * @param view
     */
    public void addBookIn(View view){
        if (!mBound){
            toBindService();
            return;
        }
        if (mBookManager == null){
            return;
        }
        Book book = new Book();
        book.setName("App 开发In");
        book.setPrice(30);
        try {
            //获得服务端执行方法的返回值，并打印输出
            Book returnBook = mBookManager.addBookIn(book);
            Log.e(TAG, "addBookIn: Client book："+book.toString() );
            Log.e(TAG, "addBookIn: server 返回book："+returnBook.toString() );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用服务端的addBookOut方法
     * @param view
     */
    public void addBookOut(View view){
        if (!mBound){
            toBindService();
            return;
        }
        if (mBookManager == null){
            return;
        }
        Book book = new Book();
        book.setName("App 开发Out");
        book.setPrice(30);
        try {
            //获得服务端执行方法的返回值，并打印输出
            Book returnBook = mBookManager.addBookOut(book);
            Log.e(TAG, "addBookOut: Client book："+book.toString() );
            Log.e(TAG, "addBookOut: server 返回book："+returnBook.toString() );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用服务端的addBookInOut方法
     * @param view
     */
    public void addBookInOut(View view){
        if (!mBound){
            toBindService();
            return;
        }
        if (mBookManager == null){
            return;
        }
        Book book = new Book();
        book.setName("App 开发InOut");
        book.setPrice(30);
        try {
            //获得服务端执行方法的返回值，并打印输出
            Book returnBook = mBookManager.addBookInOut(book);
            Log.e(TAG, "addBookInOut: Client book："+book.toString() );
            Log.e(TAG, "addBookInOut: server 返回book："+returnBook.toString() );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound){
            toBindService();
        }
    }

    /**
     * 尝试与服务端建立连接
     */
    private void toBindService() {
        Intent intent = new Intent();
        intent.setAction("com.prsuit.aidl.bookmanager");
        //5.0版本后隐式启动服务报错，必须给Intent设置包名
        intent.setPackage("com.prsuit.androidlearnsample");
        bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound){
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    public static void startAct(Context context) {
        context.startActivity(new Intent(context,AIDLActivity.class));
    }
}
