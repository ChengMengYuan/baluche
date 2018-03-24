package com.baluche.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.app.MApplication;
import com.baluche.view.adapter.MainFragmentPageAdapter;
import com.baluche.view.fragment.HomePageFragment;
import com.baluche.view.fragment.MineFragment;
import com.baluche.view.fragment.NearbyFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

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
    private RelativeLayout rll_home;
    private RelativeLayout rll_nearby;
    private RelativeLayout rll_mine;
    //底部导航栏的图片
    private ImageView img_rll_home;
    private ImageView img_rll_nearby;
    private ImageView img_rll_mine;
    //底部导航栏的文字
    private TextView tv_rll_home;
    private TextView tv_rll_nearby;
    private TextView tv_rll_mine;

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

        rll_home = this.findViewById(R.id.rll_home);
        rll_nearby = this.findViewById(R.id.rll_nearby);
        rll_mine = this.findViewById(R.id.rll_mine);

        img_rll_home = this.findViewById(R.id.img_rll_home);
        img_rll_nearby = this.findViewById(R.id.img_rll_nearby);
        img_rll_mine = this.findViewById(R.id.img_rll_mine);

        tv_rll_home = this.findViewById(R.id.tv_rll_home);
        tv_rll_nearby = this.findViewById(R.id.tv_rll_nearby);
        tv_rll_mine = this.findViewById(R.id.tv_rll_mine);

        /**
         * 设置为选中色
         */
        img_rll_home.setImageDrawable(getResources().getDrawable(R.drawable.green_sye));
        tv_rll_home.setTextColor(getResources().getColor(R.color.colorGreen));

        mviewpager.setOnPageChangeListener(this);

        //        rll_home.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
        //        rll_nearby.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
        //        rll_mine.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());

        rll_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mviewpager.setCurrentItem(0);
                /**
                 * 设置为选中色
                 */
                img_rll_home.setImageDrawable(getResources().getDrawable(R.drawable.green_sye));
                tv_rll_home.setTextColor(getResources().getColor(R.color.colorGreen));
                /**
                 * 设置为未选中色
                 */
                img_rll_nearby.setImageDrawable(getResources().getDrawable(R.drawable.fujin));
                tv_rll_nearby.setTextColor(getResources().getColor(R.color.colorGray));

                img_rll_mine.setImageDrawable(getResources().getDrawable(R.drawable.wod));
                tv_rll_mine.setTextColor(getResources().getColor(R.color.colorGray));
            }
        });
        rll_nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mviewpager.setCurrentItem(1);
                /**
                 * 设置为选中色
                 */
                img_rll_nearby.setImageDrawable(getResources().getDrawable(R.drawable.green_fujin));
                tv_rll_nearby.setTextColor(getResources().getColor(R.color.colorGreen));
                /**
                 * 设置为未选中色
                 */
                img_rll_home.setImageDrawable(getResources().getDrawable(R.drawable.sye));
                tv_rll_home.setTextColor(getResources().getColor(R.color.colorGray));

                img_rll_mine.setImageDrawable(getResources().getDrawable(R.drawable.wod));
                tv_rll_mine.setTextColor(getResources().getColor(R.color.colorGray));
            }
        });
        rll_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mviewpager.setCurrentItem(2);
                /**
                 * 设置为选中色
                 */
                img_rll_mine.setImageDrawable(getResources().getDrawable(R.drawable.green_wod));
                tv_rll_mine.setTextColor(getResources().getColor(R.color.colorGreen));
                /**
                 * 设置为未选中色
                 */
                img_rll_home.setImageDrawable(getResources().getDrawable(R.drawable.sye));
                tv_rll_home.setTextColor(getResources().getColor(R.color.colorGray));

                img_rll_nearby.setImageDrawable(getResources().getDrawable(R.drawable.fujin));
                tv_rll_nearby.setTextColor(getResources().getColor(R.color.colorGray));
            }
        });

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MApplication.getInstance().destory();
    }


}
