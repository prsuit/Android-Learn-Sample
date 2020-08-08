package com.prsuit.androidlearnsample.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.prsuit.androidlearnsample.R;

import static com.prsuit.androidlearnsample.Constants.TAG;

public class MyFragmentActivity extends AppCompatActivity implements MyFragment.OnFragmentListener {

    private String subTag = "MyFragmentActivity";

    public static void startAct(Context context) {
        context.startActivity(new Intent(context, MyFragmentActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: begin--" + subTag);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, MyFragment.newInstance("Fragment1"), "f1")
                    // 调用replace/add时不加addToBackStack(),会调用了onDestroy()和onDetach(),加了只调到了onDestroyView()
                    // 因此在Fragment事务中加不加addToBackStack()会影响Fragment的生命周期。
                    .addToBackStack("") //是可选的，FragmentManager拥有回退栈（BackStack），类似于Activity的任务栈，
                    // 如果添加了该语句，就把该事务加入回退栈，当用户点击返回按钮，会回退该事务。
                    .commit();
        } else {
            //处理Fragment重叠问题第一种方式，这是由于Fragment被系统杀掉，并重新初始化时再次将fragment加入activity，
            MyFragment fragment1 = (MyFragment) getSupportFragmentManager().findFragmentByTag("f1");
        }
        Log.e(TAG, "onCreate: end--" + subTag);

        //处理Fragment重叠问题第二种方式 在创建 Fragment 前添加判断，判断是否已经存在：
//        Fragment tempFragment = getSupportFragmentManager().findFragmentByTag("f1");
//        if (tempFragment == null){
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container,MyFragment.newInstance("Fragment1"),"f1")
//                    .commit();
//        } else {
//            MyFragment fragment1 = (MyFragment) tempFragment;
//            getSupportFragmentManager().beginTransaction().show(fragment1);
//        }
    }

    public void fragment1Click(View view){
        Fragment tempFragment = getSupportFragmentManager().findFragmentByTag("f1");
        if (tempFragment == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,MyFragment.newInstance("Fragment1"),"f1")
                    .addToBackStack("")
                    .commit();
        } else {
            MyFragment myFragment = (MyFragment) tempFragment;
            getSupportFragmentManager().beginTransaction().show(myFragment);
        }
    }

    public void fragment2Click(View view){
        Fragment tempFragment = getSupportFragmentManager().findFragmentByTag("f2");
        if (tempFragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, MyFragment.newInstance("Fragment2"), "f2")
                    .addToBackStack("")
                    .commit();
        } else {
            MyFragment myFragment = (MyFragment) tempFragment;
            getSupportFragmentManager().beginTransaction().show(myFragment);
        }
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: begin--" + subTag);
        super.onStart();
        Log.e(TAG, "onStart: end--" + subTag);
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        Log.e(TAG, "onAttachFragment: "+subTag);
        super.onAttachFragment(fragment);
        //处理Fragment重叠问题第三种方式，
        if (fragment instanceof MyFragment){
            MyFragment fragment1 = (MyFragment) fragment;
        }
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume: begin--" + subTag);
        super.onResume();
        Log.e(TAG, "onResume: end--" + subTag);
    }

    @Override
    public void onAttachedToWindow() {
        Log.e(TAG, "onAttachedToWindow: "+subTag );
        super.onAttachedToWindow();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause: begin--" + subTag);
        super.onPause();
        Log.e(TAG, "onPause: end--" + subTag);
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop: begin--" + subTag);
        super.onStop();
        Log.e(TAG, "onStop: end--" + subTag);
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: begin--" + subTag);
        super.onDestroy();
        Log.e(TAG, "onDestroy: end--" + subTag);
    }

    @Override
    public void sendData(String str) {
        Toast.makeText(this,"收到Fragment的数据-->"+str,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //根据requestCode判断是否分发给fragment，如果是来自fragment，真正的requestCode = (requestCode & 0xffff)
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: "+subTag +"--req->"+ requestCode +"--res-->"+resultCode );
    }
}
