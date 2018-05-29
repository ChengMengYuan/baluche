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
import com.baluche.view.fragment.CommonProblemFragment;
import com.baluche.view.fragment.OnlineFeedbackFragment;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();
    private RelativeLayout feedback_return_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void initView() {
        tabLayout = findViewById(R.id.feedback_tayLayout);
        viewPager = findViewById(R.id.feedback_viewPager);
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //关联ViewPager和TabLayout
        tabLayout.setupWithViewPager(viewPager);
        tabs.add("常见问题");
        tabs.add("在线反馈");
        fragments.add(new CommonProblemFragment());
        fragments.add(new OnlineFeedbackFragment());

        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));

        feedback_return_left = findViewById(R.id.feedback_return_left);
        feedback_return_left.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.feedback_return_left:
                finish();
                break;
        }
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
