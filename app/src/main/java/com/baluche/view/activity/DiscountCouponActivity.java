package com.baluche.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.view.fragment.AvailableDiscountFragment;
import com.baluche.view.fragment.PayingbillFragment;
import com.baluche.view.fragment.PayrecordFragment;
import com.baluche.view.fragment.UnavailableDiscountFragment;

import java.util.ArrayList;
import java.util.List;

public class DiscountCouponActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_coupon);

    }

    @Override
    public void initView() {
        tabLayout = findViewById(R.id.discount_coupon_tayLayout);
        viewPager = findViewById(R.id.discount_coupon_viewPager);
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //关联ViewPager和TabLayout
        tabLayout.setupWithViewPager(viewPager);
        tabs.add("缴费车辆");
        tabs.add("缴费记录");
        fragments.add(new AvailableDiscountFragment());
        fragments.add(new UnavailableDiscountFragment());

        viewPager.setAdapter(new DiscountCouponActivity.TabAdapter(getSupportFragmentManager()));
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View view) {

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
