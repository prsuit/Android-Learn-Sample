package com.prsuit.androidlearnsample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.prsuit.androidlearnsample.Constants.TAG;

/**
 * @Description: 懒加载基类
 * @Author: sh
 * @Date: 2020/8/5
 */
public abstract class LazyFragment extends Fragment {
    private static final String ARG_PARAM = "arg_param";
    private String subTag = "LazyFragment";
    private int index;
    private TextView nameTv;
    private View rootView = null;
    private boolean isViewCreated = false; //UI是否创建
    private boolean isVisibleStateUp = false;//记录上一次可见的状态 解决fragment重复加载/重复暂停 不可见->可见(加载) 可见->不可见(停止)

    @Override
    public void onAttach(@NonNull Context context) {
        Log.e(TAG, "onAttach: begin--" +subTag);
        super.onAttach(context);
        Log.e(TAG, "onAttach: end--" +subTag);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: begin--" +subTag);
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: end--" +subTag);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " +subTag);
//        View viewRoot = inflater.inflate(R.layout.fragment_layout,container,false);
//        nameTv = viewRoot.findViewById(R.id.name_tv);
//        nameTv.setText(argParam);
        if (null == rootView){
            rootView = inflater.inflate(getLayoutRes(),container,false);
        }
        isViewCreated = true;
        initView(rootView);

        //解决第一次一直初始化loading一直显示的问题
        if (getUserVisibleHint()){
            //手动来分发下
            setUserVisibleHint(true);
        }
        return rootView;
    }

    //判断 Fragment 是否可见
    // setUserVisibleHint会在onCreateView之前调用一次，此时UI还没创建，就不需要做数据加载
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        index = getArguments().getInt(ARG_PARAM);
        Log.e(TAG, "setUserVisibleHint: "+ index + "  " + subTag +" isVisibleToUser-->"+ isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        //在UI已经创建的前提下
        if (isViewCreated) {
            // 记录上一次可见的状态: && isVisibleStateUP
            if (isVisibleToUser && !isVisibleStateUp) {
                //分发可见动作
                dispatchUserVisibleHint(true);
            } else if (!isVisibleToUser && isVisibleStateUp){
                //分发不可见动作
                dispatchUserVisibleHint(false);
            }
        }
    }

    //分发 可见 不可见 的动作
    private void dispatchUserVisibleHint(boolean visibleState){
        //记录上一次可见的状态 实时更新状态
        this.isVisibleStateUp = visibleState;

        if (visibleState){
            //加载数据
            onFragmentLoad();
        } else {
            //停止一切操作
            onFragmentLoadStop();
        }
    }

    //停止网络数据请求
    public void onFragmentLoadStop() {
        Log.e(TAG, "onFragmentLoadStop: " );
    }

    //加载网络数据请求
    public void onFragmentLoad() {
        Log.e(TAG, "onFragmentLoad: " );
    }


    //让子类完成，初始化布局，初始化控件
    protected abstract int getLayoutRes();
    protected abstract void initView(View rootView);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onActivityCreated: " +subTag);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart: begin--"+subTag );
        super.onStart();
        Log.e(TAG, "onStart: end--"+subTag );
    }

    //当返回fragment时分发可见动作
    @Override
    public void onResume() {
        Log.e(TAG, "onResume: begin--"+subTag );
        super.onResume();
        Log.e(TAG, "onResume: end--"+subTag );
        //从不可见 到 可见 变化过程  说明可见
        Log.e(TAG, "onResume: "+getUserVisibleHint()+"--->"+isVisibleStateUp );
        if (getUserVisibleHint() && !isVisibleStateUp){
            dispatchUserVisibleHint(true);
        }

    }

    //当离开fragment时分发不可见动作
    @Override
    public void onPause() {
        Log.e(TAG, "onPause: "+subTag );
        super.onPause();
        //从可见 到 不可见 变化过程  说明 不可见
        Log.e(TAG, "onPause: "+getUserVisibleHint()+"--->"+isVisibleStateUp );
        if (getUserVisibleHint() && isVisibleStateUp){
            dispatchUserVisibleHint(false);
        }
    }

    @Override
    public void onStop() {
        Log.e(TAG, "onStop: "+subTag );
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView: "+subTag );
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: "+subTag );
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "onDetach: "+subTag );
        super.onDetach();
    }

}
