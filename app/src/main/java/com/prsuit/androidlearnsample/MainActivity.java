package com.prsuit.androidlearnsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.prsuit.androidlearnsample.broadcastreceiver.BroadcastActivity;
import com.prsuit.androidlearnsample.service.ServiceActivity;

import static com.prsuit.androidlearnsample.Constants.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String  subTag = "MainActivity";
    private TextView serviceTv;
    private TextView broadcastTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " + subTag);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        serviceTv.setOnClickListener(this);
        broadcastTv.setOnClickListener(this);
    }

    private void initView() {
        serviceTv = findViewById(R.id.service_tv);
        broadcastTv = findViewById(R.id.sendBroadcast_tv);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.service_tv:
                ServiceActivity.startAct(this);
                break;
            case R.id.sendBroadcast_tv:
                BroadcastActivity.startAct(this);
                break;
        }
    }
}
