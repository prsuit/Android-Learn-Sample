package com.prsuit.androidlearnsample.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.prsuit.androidlearnsample.R;

public class HandlerActivity extends AppCompatActivity {

    private static final String TAG = "HandlerActivity";
    private static final int SUB_TO_MAIN = 1;
    private static final int MAIN_TO_SUB = 2;
    private static final int SUB_TO_SUB = 3;
    private static final int DOWNLOAD_IMAGE_IN_THREAD = 4;//子线程开始工作下载图片消息
    private static final int LOAD_IMAGE = 5;//主线程更新UI消息

    public static void startAct(Context context) {
        context.startActivity(new Intent(context, HandlerActivity.class));
    }

    //主线程Handler重写自身的处理消息方法
    private Handler mainHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
            switch (msg.what){
                case SUB_TO_MAIN:
                    Log.e(TAG, "handleMessage: "+ msg.what +"--MainHandler 接收线程：-->"+Thread.currentThread().getName() );
                    break;
                case LOAD_IMAGE:
                    Log.e(TAG, "handleMessage: "+ msg.what +"--MainHandler 接收线程：-->"+Thread.currentThread().getName() );
                    //主线程更新UI，加载图片
                    //imageView.setImageBitmap((Bitmap) msg.obj);
                    break;
            }

            //Toast.makeText(HandlerActivity.this,"收到msg："+msg.obj,Toast.LENGTH_SHORT).show();
        }
    };

    //主线程Handler使用Handler.Callback()处理消息
//    private Handler mainHandler2 = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(@NonNull Message msg) {
//            //处理消息
//            return true;//返回true就不执行handler本身的handleMessage()方法，返回false继续执行handler本身的handleMessage方法
//        }
//    });

    private HandlerThread handlerThread;
    private Handler subHandler;
    private Handler subHandlerWithCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initSubHandler();
    }

    private void initSubHandler(){
        //创建HandlerThread实例
        handlerThread = new HandlerThread("MyHandlerThread");
        //必须开启HandlerThread子线程，在run()初始化Looper和开启loop消息
        handlerThread.start();
        //获取子线程Handler 将HandlerThread与Handler绑定：利用handlerThread获取子线程的Looper，创建子线程的Handler实例 在子线程中处理消息
        subHandler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
                switch (msg.what){
                    case MAIN_TO_SUB:
                        Log.e(TAG, "handleMessage: "+ msg.what+"--subHandler 接收线程：-->"+Thread.currentThread().getName() );
                        break;
                    case SUB_TO_SUB:
                        //
                        Log.e(TAG, "handleMessage: "+ msg.what +"--subHandler 接收线程：-->" +Thread.currentThread().getName() );
                        break;
                }
            }
        };

        //使用Handler.Callback处理消息
        subHandlerWithCallback = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                Log.e(TAG, "handleMessage: "+ msg.what+"--subHandlerWithCallback 接收线程：-->"+Thread.currentThread().getName() );
                if (msg.what == DOWNLOAD_IMAGE_IN_THREAD){
                    Log.e(TAG, "handleMessage: "+"--subHandlerWithCallback 收到下载图片消息--->" +"开始下载...");
                    //在子线程中进行网络请求下载图片
                    //Bitmap bitmap = downLoadUrl()
                    //下载完成通知主线程去更新UI界面
                    Message message = Message.obtain();
                    message.what = LOAD_IMAGE;
                    //message.obj = bitmap;
                    Log.e(TAG, "handleMessage: --下载完成通知主线程更新UI，发送线程：-->"+Thread.currentThread().getName());
                    mainHandler.sendMessage(message);
                }
                return true;
            }
        });
    }

    //在子线程中发消息给主线程处理
    public void subThreadSendMsgToMainClick(View view){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subThreadSendMsgToMainClick: --发送线程：-->"+Thread.currentThread().getName());
                mainHandler.sendEmptyMessage(SUB_TO_MAIN);
            }
        });
        thread1.start();
    }

    //在主线程中发消息给子线程处理
    public void mainThreadSendMsgToSubClick(View view){
        Log.e(TAG, "mainThreadSendMsgToSubClick: --发送线程：-->"+Thread.currentThread().getName());
        subHandler.sendEmptyMessage(MAIN_TO_SUB);
    }

    //在子线程中发消息给子线程处理
    public void subThreadSendMsgToSubClick(View view){
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subThreadSendMsgToSubClick: --发送线程：-->"+Thread.currentThread().getName());
                subHandler.sendEmptyMessage(SUB_TO_SUB);
            }
        });
        thread2.start();
    }

    //在主线程中通知子线程下载图片
    public void subThreadDownloadImageClick(View view){
        Log.e(TAG, "subThreadDownloadImage: --发送线程：-->"+Thread.currentThread().getName());
        subHandlerWithCallback.sendEmptyMessage(DOWNLOAD_IMAGE_IN_THREAD);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在不需要HandlerThread的时候需要手动停止掉
        handlerThread.quit();
        //移除所有消息，防止内存泄露
        mainHandler.removeCallbacksAndMessages(null);
    }
}
