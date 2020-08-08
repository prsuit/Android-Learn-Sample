package com.prsuit.androidlearnsample.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.prsuit.androidlearnsample.R;

public class TestResultActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        mViewPager = findViewById(R.id.viewpager);
    }

    public void testSetResult (View view){
        Intent intent = new Intent();
        intent.putExtra("data","I' come from TestResultActivity");
        setResult(RESULT_OK,intent);
        finish();
    }
}
