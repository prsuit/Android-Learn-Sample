package com.prsuit.androidlearnsample.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static com.prsuit.androidlearnsample.Constants.TAG;

/**
 * @Description: 广播接收器
 * @Author: sh
 * @Date: 2020/8/2
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    public static final String  MY_NORMAL_ACTION_NAME = "com.prsuit.sample.normal_action_name";
    public static final String  MY_ORDERED_ACTION_NAME = "com.prsuit.sample.ordered_action_name";
    public static final String  MY_LOCAL_ACTION_NAME = "com.prsuit.sample.local_action_name";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: --action-->"+intent.getAction() );
        String action = intent.getAction();
        Toast.makeText(context,"收到广播action："+action,Toast.LENGTH_SHORT).show();
    }
}
