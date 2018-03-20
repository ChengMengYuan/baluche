package com.baluche.view.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.baluche.R;
import com.baluche.app.MApplication;
import com.baluche.view.adapter.MainFragmentPageAdapter;
import com.baluche.view.fragment.HomePageFragment;
import com.baluche.view.fragment.MineFragment;
import com.baluche.view.fragment.NearbyFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

/**
 * @author ：     cmy
 * @version :     2018/3/19.
 * @e-mil ：      mengyuan.cheng.mier@gmail.com
 * @Description : 首页Activity
 */

public class MainActivity extends FragmentActivity implements OnPageChangeListener {

    private ViewPager mviewpager;
    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;
    //选项卡中的按钮
    private RadioButton btn_home;
    private RadioButton btn_nearby;
    private RadioButton btn_mine;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Fresco
        Fresco.initialize(MainActivity.this);
        MApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        mviewpager = this.findViewById(R.id.mviewpage);

        btn_home = this.findViewById(R.id.btn_home);
        btn_nearby = this.findViewById(R.id.btn_nearby);
        btn_mine = this.findViewById(R.id.btn_mine);


        mviewpager.setOnPageChangeListener(this);

        btn_home.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
        btn_nearby.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
        btn_mine.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());

        fragments = new ArrayList<>();

        fragments.add(new HomePageFragment());
        fragments.add(new NearbyFragment());
        fragments.add(new MineFragment());

        mviewpager.setOffscreenPageLimit(2);//设置viewpager缓存页数.

        mviewpager.setAdapter(new MainFragmentPageAdapter(getSupportFragmentManager(), fragments));

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class InnerOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.btn_home:
                    //单选按钮通过参数isChecked去得到当前到底是选中还是未选中
                    if (isChecked) {
                        mviewpager.setCurrentItem(0);
                    }

                    break;
                case R.id.btn_nearby:
                    //单选按钮通过参数isChecked去得到当前到底是选中还是未选中
                    if (isChecked) {
                        mviewpager.setCurrentItem(1);
                    }

                    break;
                case R.id.btn_mine:
                    //单选按钮通过参数isChecked去得到当前到底是选中还是未选中
                    if (isChecked) {
                        mviewpager.setCurrentItem(2);
                    }

                    break;

                default:
                    break;
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MApplication.getInstance().destory();
    }
}
