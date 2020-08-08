package com.prsuit.androidlearnsample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.prsuit.androidlearnsample.R;

/**
 * @Description:
 * @Author: sh
 * @Date: 2020/8/7
 */
public class MyDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_dialog,container,false);
        return view;
    }

    public static MyDialogFragment newInstance() {
        return new MyDialogFragment();
    }
}
