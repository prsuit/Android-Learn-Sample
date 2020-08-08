package com.prsuit.androidlearnsample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.prsuit.androidlearnsample.R;
import static com.prsuit.androidlearnsample.Constants.TAG;

/**
 * @Description: 继承懒加载的fragment
 * @Author: sh
 * @Date: 2020/8/8
 */
public class MyLazyFragment extends LazyFragment {

    private static final String ARG_PARAM = "arg_param";
    private String subTag = "MyLazyFragment";
    private TextView textView;
    int tabIndex;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_lazy;
    }

    @Override
    protected void initView(View rootView) {
        textView = rootView.findViewById(R.id.tv_loading);
        tabIndex = getArguments().getInt(ARG_PARAM);
        Log.e(TAG, "initView: "+ tabIndex +" fragment " + "initView: ");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),TestResultActivity.class));
            }
        });
    }

    @Override
    public void onFragmentLoad() {
        super.onFragmentLoad();
        tabIndex = getArguments().getInt(ARG_PARAM);
        Log.e(TAG, "onFragmentLoad: "+tabIndex+" fragment "+"真正更新界面" );
        textView.setText("onFragmentLoad " + tabIndex+" fragment");
    }

    @Override
    public void onFragmentLoadStop() {
        super.onFragmentLoadStop();
        tabIndex = getArguments().getInt(ARG_PARAM);
        Log.e(TAG, "onFragmentLoadStop: "+tabIndex +" fragment "+"暂停一切操作 pause");
    }

    public static LazyFragment newInstance(int pos){
        MyLazyFragment myLazyFragment = new MyLazyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PARAM,pos);
        myLazyFragment.setArguments(bundle);
        return myLazyFragment;
    }
}
