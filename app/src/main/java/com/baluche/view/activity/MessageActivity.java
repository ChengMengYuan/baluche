package com.baluche.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.view.fragment.InformFragment;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();
    private RelativeLayout message_return_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_message);
    }

    public void initData() {

    }


    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.message_return_left:
                finish();
                break;
        }
    }

    public void initView() {
        tabLayout = findViewById(R.id.tayLayout);
        viewPager = findViewById(R.id.viewPager);
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //关联ViewPager和TabLayout
        tabLayout.setupWithViewPager(viewPager);
        tabs.add("通知");
        tabs.add("活动");
        fragments.add(new InformFragment());
        fragments.add(new InformFragment());

        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));

        message_return_left = findViewById(R.id.message_return_left);
        message_return_left.setOnClickListener(this);
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
