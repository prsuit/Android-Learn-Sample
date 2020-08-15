package com.prsuit.androidlearnsample.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @Description:
 * @Author: sh
 * @Date: 2020/8/15
 */
public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

   public MyIntentService(){
       super("MyIntentService");
   }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //在这里通过intent携带的数据，开进行任务的操作。
        Log.e(TAG, "onHandleIntent: 开启任务操作线程-->"+ Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: --->MyIntentService");
    }
}
