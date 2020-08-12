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
    private Fragment mCurrentFragment;
    private Fragment fragment0,fragment1,fragment2;

    public static void startAct(Context context) {
        context.startActivity(new Intent(context, MyFragmentActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: begin--" + subTag);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);

        if (savedInstanceState == null) {
            fragment0 = MyFragment.newInstance("Fragment0");
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, fragment0, "f0")
                    // 调用replace/add时不加addToBackStack(),会调用了onDestroy()和onDetach(),加了只调到了onDestroyView()
                    // 因此在Fragment事务中加不加addToBackStack()会影响Fragment的生命周期。
//                    .addToBackStack("") //是可选的，FragmentManager拥有回退栈（BackStack），类似于Activity的任务栈，
                    // 如果添加了该语句，就把该事务加入回退栈，当用户点击返回按钮，会回退该事务。
                    .commit();
        } else {
            //处理Fragment重叠问题第一种方式，这是由于Fragment被系统杀掉，并重新初始化时再次将fragment加入activity，
            fragment0 = getSupportFragmentManager().findFragmentByTag("f0");
            if (fragment0 != null){
                getSupportFragmentManager().beginTransaction().show(fragment0).commit();
            }
        }
        mCurrentFragment = fragment0;
        Log.e(TAG, "onCreate: end--" + subTag);

        //处理Fragment重叠问题第二种方式 在创建 Fragment 前添加判断，判断是否已经存在：
//        Fragment tempFragment = getSupportFragmentManager().findFragmentByTag("f0");
//        if (tempFragment == null){
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container,MyFragment.newInstance("Fragment0"),"f0")
//                    .commit();
//        } else {
//            MyFragment fragment0 = (MyFragment) tempFragment;
//            getSupportFragmentManager().beginTransaction().show(fragment0).commit();
//        }
    }

    //add()不会销毁当前fragment，创建新的
    public void addFragment1Click(View view){
        fragment1 = getSupportFragmentManager().findFragmentByTag("f1");
        if (fragment1 == null){
            fragment1 = MyFragment.newInstance("Fragment1");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,fragment1,"f1")
                    .hide(mCurrentFragment)
//                    .addToBackStack("")
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction().hide(mCurrentFragment).show(fragment1).commit();
        }
        mCurrentFragment = fragment1;
    }

    //replace()销毁当前fragment，创建新的
    public void replaceFragment2Click(View view){
        fragment2 = getSupportFragmentManager().findFragmentByTag("f2");
        if (fragment2 == null) {
            fragment2 = MyFragment.newInstance("Fragment2");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment2, "f2")
//                    .addToBackStack("")
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction().hide(mCurrentFragment).show(fragment2).commit();
        }
        mCurrentFragment = fragment2;
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart: " + subTag);
        super.onRestart();
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
