package com.prsuit.androidlearnsample.service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.prsuit.androidlearnsample.Constants.TAG;
import com.prsuit.androidlearnsample.R;
import com.prsuit.androidlearnsample.aidl.IMyAIDLService;

public class ServiceActivity extends AppCompatActivity {

    private String  subTag = "ServiceActivity";
    private Button bindServiceBtn;

    private Intent startIntent;
    private Intent bindIntent;
    private Intent remoteIntent;
    private ServiceConnection connection;
    private BindService.MyBinder myBinder;

    private ServiceConnection remoteConn;
    //aidl
    private IMyAIDLService myAIDLService;
    Intent intentService;

    public static void startAct(Context context) {
        context.startActivity(new Intent(context,ServiceActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: "+ subTag);
        setContentView(R.layout.activity_service);
        startIntent = new Intent(this, LocalService.class);
        bindIntent = new Intent(this, BindService.class);
        remoteIntent = new Intent(this, RemoteService.class);
        intentService = new Intent(this,MyIntentService.class);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG, "onServiceConnected: ");
                myBinder = (BindService.MyBinder) service;
                myBinder.startTask();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG, "onServiceDisconnected: ");
                myBinder = null;
            }
        };

        remoteConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myAIDLService = IMyAIDLService.Stub.asInterface(service);
                try {
                    int result = myAIDLService.plus(1,2);
                    Log.e(TAG, "onServiceConnected:  myAIDLService.plus-->"+result );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myAIDLService = null;
            }
        };
    }

    public void startServiceClick(View view){
        startService(startIntent);
    }

    public void stopServiceClick(View view){
        stopService(startIntent);
    }

    public void bindServiceClick(View view){
        bindService(bindIntent,connection,BIND_AUTO_CREATE);
    }

    public void unbindServiceClick(View view){
        if (null != connection){
            unbindService(connection);
        }
    }

    public void bindRemoteServiceClick(View view){
        bindService(remoteIntent,remoteConn,BIND_AUTO_CREATE);
    }

    public void unbindRemoteServiceClick(View view){
        if (null != remoteConn){
            unbindService(remoteConn);
        }
    }

    public void startIntentServiceClick(View view){
        startService(intentService);
        Intent intent2 = new Intent(this,MyIntentService.class);
        startService(intent2);//多次启动，顺序执行，全部执行完自动结束
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart: " + subTag);
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " + subTag);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState: " + subTag);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + subTag);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " + subTag);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState: " + subTag);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " + subTag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " + subTag);
    }
}
