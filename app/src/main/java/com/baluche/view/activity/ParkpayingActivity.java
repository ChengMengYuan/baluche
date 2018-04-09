package com.baluche.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.baluche.R;
import com.baluche.view.fragment.InformFragment;
import com.baluche.view.fragment.PayingbillFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class ParkpayingActivity extends BaseActivity{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parkpaying);
    }

    @Override
    public void setActivityPre() {

    }

    public void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View view) {

    }

    public void initView() {
        tabLayout = findViewById(R.id.parkpaying_tayLayout);
        viewPager = findViewById(R.id.parkpaing_viewPager);
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //关联ViewPager和TabLayout
        tabLayout.setupWithViewPager(viewPager);
        tabs.add("通知");
        tabs.add("活动");
        fragments.add(new PayingbillFragment());
        fragments.add(new PayingbillFragment());

        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
    }

    class TabAdapter extends FragmentPagerAdapter {
        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        //显示标签上的文字
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
}
