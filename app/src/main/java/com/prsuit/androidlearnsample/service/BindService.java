package com.prsuit.androidlearnsample.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.prsuit.androidlearnsample.Constants.TAG;

/**
 * @Description:
 * @Author: sh
 * @Date: 2020/7/30
 */
public class BindService extends Service {

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

    class MyBinder extends Binder {

        public void startTask(){
            Log.e(TAG, "startTask: " );
        }
    }
}
