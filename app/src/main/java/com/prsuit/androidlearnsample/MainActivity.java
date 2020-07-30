package com.prsuit.androidlearnsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.prsuit.androidlearnsample.service.ServiceActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView serviceTv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        serviceTv.setOnClickListener(this);
    }

    private void initView() {
        serviceTv = findViewById(R.id.service_tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.service_tv:
                ServiceActivity.startAct(this);
                break;
        }
    }
}
