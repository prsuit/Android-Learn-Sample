package com.prsuit.androidlearnsample.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.prsuit.androidlearnsample.Constants.TAG;
import com.prsuit.androidlearnsample.R;

public class ServiceActivity extends AppCompatActivity {

    private Button bindServiceBtn;

    private Intent startIntent;
    private Intent bindIntent;
    private ServiceConnection connection;
    private BindService.MyBinder myBinder;

    public static void startAct(Context context) {
        context.startActivity(new Intent(context,ServiceActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        startIntent = new Intent(this, LocalService.class);
        bindIntent = new Intent(this, BindService.class);
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
        unbindService(connection);
    }
}
