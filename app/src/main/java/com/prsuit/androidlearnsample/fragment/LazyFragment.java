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

import com.prsuit.androidlearnsample.R;

import static com.prsuit.androidlearnsample.Constants.TAG;

/**
 * @Description:
 * @Author: sh
 * @Date: 2020/8/5
 */
public class LazyFragment extends Fragment {
    private static final String ARG_PARAM = "arg_param";
    private String subTag = "LazyFragment";
    private String argParam;
    private TextView nameTv;

    @Override
    public void onAttach(@NonNull Context context) {
        Log.e(TAG, "onAttach: begin--" +subTag);
        super.onAttach(context);
        Log.e(TAG, "onAttach: end--" +subTag);
        argParam = getArguments().getString(ARG_PARAM);
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
        View viewRoot = inflater.inflate(R.layout.fragment_layout,container,false);
        nameTv = viewRoot.findViewById(R.id.name_tv);
        nameTv.setText(argParam);
        return viewRoot;
    }

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

    @Override
    public void onResume() {
        Log.e(TAG, "onResume: begin--"+subTag );
        super.onResume();
        Log.e(TAG, "onResume: end--"+subTag );
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause: "+subTag );
        super.onPause();
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

    public static LazyFragment newInstance(String str){
        LazyFragment lazyFragment = new LazyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM,str);
        lazyFragment.setArguments(bundle);
        return lazyFragment;
    }
}
