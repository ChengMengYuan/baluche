package com.baluche.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.app.MApplication;
import com.baluche.util.CustomViewPager;
import com.baluche.util.SnackbarUtil;
import com.baluche.base.BaseActivity;
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

public class MainActivity extends BaseActivity implements OnPageChangeListener {

    private CustomViewPager mviewpager;
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
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        Log.d("http+Token", MApplication.Token);
        mviewpager = this.findViewById(R.id.mviewpage);
        mviewpager.setScanScroll(false);//设置viewPage禁止滑动，避免和地图起冲突

        rll_home = this.findViewById(R.id.rll_home);
        rll_home.setOnClickListener(this);
        rll_nearby = this.findViewById(R.id.rll_nearby);
        rll_nearby.setOnClickListener(this);
        rll_mine = this.findViewById(R.id.rll_mine);
        rll_mine.setOnClickListener(this);

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

        fragments = new ArrayList<>();
        fragments.add(new HomePageFragment());
        fragments.add(new NearbyFragment());
        fragments.add(new MineFragment());
        mviewpager.setOffscreenPageLimit(2);//设置viewpager缓存页数.
        mviewpager.setAdapter(new MainFragmentPageAdapter(getSupportFragmentManager(), fragments));

    }

    @Override
    public void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View view) {
        $Log("Main-widgetClick");
        switch (view.getId()) {
            case R.id.rll_home:
                setHomeClick();
                break;
            case R.id.rll_nearby:
                setNearbyClick();
                break;
            case R.id.rll_mine:
                setMineClikc();
                break;
            default:
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            $Log("onPageSelected: 0");
            setHomeClick();
        } else if (position == 1) {
            $Log("onPageSelected: 1");
            setNearbyClick();
        } else if (position == 2) {
            $Log("onPageSelected: 2");
            setMineClikc();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setNearbyClick() {
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

    private void setHomeClick() {
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

    private void setMineClikc() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MApplication.getInstance().destory();
    }

    //--------------使用onKeyDown()干掉他--------------

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /*双击退出APP*/
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                SnackbarUtil.showIndefiniteSnackbar(mviewpager,
                        "再按一次退出程序",
                        2000,
                        getResources().getColor(R.color.colorGreen),
                        Color.WHITE);
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
