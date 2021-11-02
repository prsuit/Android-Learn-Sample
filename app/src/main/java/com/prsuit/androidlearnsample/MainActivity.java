package com.prsuit.androidlearnsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.prsuit.androidlearnsample.annotations.AutoWired;
import com.prsuit.androidlearnsample.annotations.InjectUtils;
import com.prsuit.androidlearnsample.annotations.InjectView;
import com.prsuit.androidlearnsample.broadcastreceiver.BroadcastActivity;
import com.prsuit.androidlearnsample.contentprovider.ContentProviderActivity;
import com.prsuit.androidlearnsample.fragment.MyFragmentActivity;
import com.prsuit.androidlearnsample.handler.HandlerActivity;
import com.prsuit.androidlearnsample.service.ServiceActivity;

import static com.prsuit.androidlearnsample.Constants.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String subTag = "MainActivity";
    private TextView serviceTv;
    private TextView broadcastTv;
    private TextView contentProviderTv;
    private TextView lazyFragmentTv;
    private TextView handlerTv;

    @InjectView(R.id.annotation_tv)
    private TextView annotationTv;

    @AutoWired
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: " + subTag);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        InjectUtils.injectView(this);
        annotationTv.setText("自定义注解");
    }

    private void initListener() {
        serviceTv.setOnClickListener(this);
        broadcastTv.setOnClickListener(this);
        contentProviderTv.setOnClickListener(this);
        lazyFragmentTv.setOnClickListener(this);
        handlerTv.setOnClickListener(this);
    }

    private void initView() {
        serviceTv = findViewById(R.id.service_tv);
        broadcastTv = findViewById(R.id.sendBroadcast_tv);
        contentProviderTv = findViewById(R.id.content_provider_tv);
        lazyFragmentTv = findViewById(R.id.lazy_fragment_tv);
        handlerTv = findViewById(R.id.handler_tv);
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart: " + subTag);
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: " + subTag);
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.e(TAG, "onRestoreInstanceState: " + subTag);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume: " + subTag);
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause: " + subTag);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.e(TAG, "onSaveInstanceState: " + subTag);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop: " + subTag);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: " + subTag);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.service_tv:
                ServiceActivity.startAct(this);
                break;
            case R.id.sendBroadcast_tv:
                BroadcastActivity.startAct(this);
                break;
            case R.id.content_provider_tv:
                ContentProviderActivity.startAct(this);
                break;
            case R.id.lazy_fragment_tv:
                MyFragmentActivity.startAct(this);
                break;
            case R.id.handler_tv:
                HandlerActivity.startAct(this);
                break;
        }
    }
}
