package com.prsuit.androidlearnsample.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.prsuit.androidlearnsample.IMyAIDLService;

import static com.prsuit.androidlearnsample.Constants.TAG;

/**
 * @Description: 远程Service
 * @Author: sh
 * @Date: 2020/7/31
 */
public class RemoteService extends Service {
    //AIDL 服务端 Binder
    private IMyAIDLService.Stub mBinder = new IMyAIDLService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        //交互方法
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
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
}
