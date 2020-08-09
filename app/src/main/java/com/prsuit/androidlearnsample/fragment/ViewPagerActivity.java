package com.prsuit.androidlearnsample.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prsuit.androidlearnsample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager+Fragment 懒加载
 */
public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();

    public static void startAct(Context context) {
        context.startActivity(new Intent(context,ViewPagerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        fragmentList.add(MyLazyFragment.newInstance(1));
        fragmentList.add(MyLazyFragment.newInstance(2));
        fragmentList.add(MyLazyFragment.newInstance(3));
        fragmentList.add(MyLazyFragment.newInstance(4));
        fragmentList.add(MyLazyFragment.newInstance(5));

        //androidx之前使用LazyFragment基类
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        //androidx之后 懒加载 使用AndroidxLazyFragment基类
//        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);//设置缓存当前页左右相邻各多少个
        mViewPager.addOnPageChangeListener(onPageChangeListener);
    }

    // tab 名称设置，例如： T1, T2, T3, T4, T5
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int itemId = R.id.fragment_1;
            switch (position){
                case 0:
                    itemId = R.id.fragment_1;
                    break;
                case 1:
                    itemId = R.id.fragment_2;
                    break;
                case 2:
                    itemId = R.id.fragment_3;
                    break;
                case 3:
                    itemId = R.id.fragment_4;
                    break;
                case 4:
                    itemId = R.id.fragment_5;
                    break;
            }
            bottomNavigationView.setSelectedItemId(itemId);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //当点击 tab1 的时候 就会 设置CurrentItem=0，来设置 ViewPager下标操作
   BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
       @Override
       public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           switch (item.getItemId()){
               case R.id.fragment_1:
                   mViewPager.setCurrentItem(0,true);
                   return true;
               case R.id.fragment_2:
                   mViewPager.setCurrentItem(1,true);
                   return true;
               case R.id.fragment_3:
                   mViewPager.setCurrentItem(2,true);
                   return true;
               case R.id.fragment_4:
                   mViewPager.setCurrentItem(3,true);
                   return true;
               case R.id.fragment_5:
                   mViewPager.setCurrentItem(4,true);
                   return true;
           }
           return false;
       }
   };
}
