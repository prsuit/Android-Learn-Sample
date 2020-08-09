package com.prsuit.androidlearnsample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Description: androidx之后懒加载
 * @Author: sh
 * @Date: 2020/8/9
 */
public abstract class AndroidxLazyFragment extends Fragment {
    private View rootView = null;
    private boolean isFirstLoad = true;//是否第一次加载

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(getLayoutRes(),container,false);
        }
        initView(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad){
            // 将数据加载逻辑放到onResume()方法中
            initData();
            isFirstLoad = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true;
    }

    protected void initData() {

    }

    protected abstract int getLayoutRes();

    protected abstract void initView(View rootView);
}
