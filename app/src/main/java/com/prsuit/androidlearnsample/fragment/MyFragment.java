package com.prsuit.androidlearnsample.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.prsuit.androidlearnsample.R;

import static com.prsuit.androidlearnsample.Constants.TAG;

/**
 * @Description:
 * @Author: sh
 * @Date: 2020/8/5
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM = "arg_param";
    private String subTag = "MyFragment";
    private String argParam;
    private TextView nameTv;
    private Button fragStartBtn, getActStartBtn, showDialogBtn;

    @Override
    public void onAttach(@NonNull Context context) {
        Log.e(TAG, "onAttach: begin--" +subTag);
        super.onAttach(context);
        Log.e(TAG, "onAttach: end--" +subTag);
        argParam = getArguments().getString(ARG_PARAM);
        if (context instanceof OnFragmentListener){
            onFragmentListener = (OnFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
        fragStartBtn = viewRoot.findViewById(R.id.fragment_start_btn);
        getActStartBtn = viewRoot.findViewById(R.id.getActivity_start_btn);
        showDialogBtn = viewRoot.findViewById(R.id.show_dialog_btn);
        nameTv.setText(argParam);
        initListener();
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
        if (null != onFragmentListener){
            onFragmentListener.sendData("Fragment向Activity传递数据");
        }
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

    public static MyFragment newInstance(String str){
        MyFragment lazyFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM,str);
        lazyFragment.setArguments(bundle);
        return lazyFragment;
    }

    //定义与activity通信接口
    public interface OnFragmentListener{
        void sendData(String str);
    }
    OnFragmentListener onFragmentListener;
    public void setOnFragmentListener(OnFragmentListener onFragmentListener){
        this.onFragmentListener = onFragmentListener;
    }

    public void initListener() {
        fragStartBtn.setOnClickListener(this);
        getActStartBtn.setOnClickListener(this);
        showDialogBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_start_btn:
                fragmentStartActivityForResult();
                break;
            case R.id.getActivity_start_btn:
                getActivityStartActivityForResult();
                break;
            case R.id.show_dialog_btn:
                showDialogFragment();
                break;
        }
    }

    //fragment onActivityResult 收得到回调
    public void fragmentStartActivityForResult(){
        Intent intent = new Intent(getActivity(), TestResultActivity.class);
        startActivityForResult(intent,0x01);//mHost.onStartActivityFromFragment()
    }

    //fragment onActivityResult 收不到回调
    public void getActivityStartActivityForResult(){
        Intent intent = new Intent(getActivity(), TestResultActivity.class);
        getActivity().startActivityForResult(intent,0x02);//通过activity启动
    }

    public void showDialogFragment(){
        MyDialogFragment dialogFragment = MyDialogFragment.newInstance();
        dialogFragment.show(getActivity().getSupportFragmentManager(),"dialog");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: " + subTag +"--req->"+requestCode+"--res-->"+resultCode );
    }
}
