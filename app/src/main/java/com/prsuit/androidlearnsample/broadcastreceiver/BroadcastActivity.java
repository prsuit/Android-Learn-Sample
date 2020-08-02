package com.prsuit.androidlearnsample.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.prsuit.androidlearnsample.R;

import static com.prsuit.androidlearnsample.broadcastreceiver.MyBroadcastReceiver.MY_LOCAL_ACTION_NAME;
import static com.prsuit.androidlearnsample.broadcastreceiver.MyBroadcastReceiver.MY_NORMAL_ACTION_NAME;
import static com.prsuit.androidlearnsample.broadcastreceiver.MyBroadcastReceiver.MY_ORDERED_ACTION_NAME;

public class BroadcastActivity extends AppCompatActivity {

    private MyBroadcastReceiver myBroadcastReceiver;
    private MyBroadcastReceiver myLocalBroadcastReceiver;
    private LocalBroadcastManager localBroadcastManager;

    public static void startAct(Context context) {
        context.startActivity(new Intent(context,BroadcastActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注册广播接收器
        registerCustomReceiver();
        //注册本地广播接收器
        registerLocalBroadcastReceiver();
    }

    /**
     * 动态注册广播
     */
    private void registerCustomReceiver() {
        //定义BroadcastReceiver和IntentFilter
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        //定义接收广播类型
        intentFilter.addAction(MY_NORMAL_ACTION_NAME);
        intentFilter.addAction(MY_ORDERED_ACTION_NAME);
        //动态注册
        registerReceiver(myBroadcastReceiver,intentFilter);
    }

    /**
     * 动态注册本地广播接收器
     */
    private void registerLocalBroadcastReceiver(){
        //定义BroadcastReceiver和IntentFilter
        myLocalBroadcastReceiver = new MyBroadcastReceiver();
        //实例化LocalBroadcastManager的实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MY_LOCAL_ACTION_NAME);
        localBroadcastManager.registerReceiver(myLocalBroadcastReceiver,intentFilter);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        //取消注册
        unregisterReceiver(myBroadcastReceiver);
        //取消应用内广播接收器注册
        localBroadcastManager.unregisterReceiver(myLocalBroadcastReceiver);
    }

    //发送普通广播
    public void sendNormalBroadcast(View v){
        Intent intent = new Intent();
        intent.setAction(MY_NORMAL_ACTION_NAME);
        //适配7.0及以上静态注册的广播收不到，静态注册需发送显式广播，即给intent指定包名。若是动态注册的则不需要
//        intent.setComponent(new ComponentName(getPackageName(),getPackageName() + ".broadcastreceiver.MyBroadcastReceiver"));
        sendBroadcast(intent);
    }

    //发送有序广播
    public void sendOrderedBroadcast(View v){
        Intent intent = new  Intent();
        intent.setAction(MY_ORDERED_ACTION_NAME);
        sendOrderedBroadcast(intent,null);
    }

    //发送本地广播
    public void sendLocalBroadcast(View v){
        Intent intent = new Intent();
        intent.setAction(MY_LOCAL_ACTION_NAME);
        localBroadcastManager.sendBroadcast(intent);
    }
}
