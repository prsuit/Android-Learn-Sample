package com.prsuit.androidlearnsample.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.prsuit.androidlearnsample.Constants.TAG;

/**
 * @Description: 使用Binder类通信的Service
 * @Author: sh
 * @Date: 2020/7/30
 */
public class BindService extends Service {
    //定义Binder对象
    private MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: " );
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: " );
        super.onDestroy();
    }

    //扩展 Binder 类
    class MyBinder extends Binder {
        //交互方法
        public void startTask(){
            Log.e(TAG, "startTask: " );
        }
    }
}
