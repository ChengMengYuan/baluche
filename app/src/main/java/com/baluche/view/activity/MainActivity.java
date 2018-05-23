package com.baluche.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
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

import com.afollestad.materialdialogs.MaterialDialog;
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
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import static com.baluche.app.MApplication.getContext;

/**
 * @author ：     cmy
 * @version :     2018/3/19.
 * @e-mil ：      mengyuan.cheng.mier@gmail.com
 * @Description : 首页Activity
 */

public class MainActivity extends BaseActivity implements OnPageChangeListener {

    private CustomViewPager mviewpager;
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

        RelativeLayout rll_home = this.findViewById(R.id.rll_home);
        rll_home.setOnClickListener(this);
        RelativeLayout rll_nearby = this.findViewById(R.id.rll_nearby);
        rll_nearby.setOnClickListener(this);
        RelativeLayout rll_mine = this.findViewById(R.id.rll_mine);
        rll_mine.setOnClickListener(this);

        img_rll_home = this.findViewById(R.id.img_rll_home);
        img_rll_nearby = this.findViewById(R.id.img_rll_nearby);
        img_rll_mine = this.findViewById(R.id.img_rll_mine);

        tv_rll_home = this.findViewById(R.id.tv_rll_home);
        tv_rll_nearby = this.findViewById(R.id.tv_rll_nearby);
        tv_rll_mine = this.findViewById(R.id.tv_rll_mine);

        /*
         * 设置为选中色
         */
        img_rll_home.setImageDrawable(getResources().getDrawable(R.drawable.green_sye));
        tv_rll_home.setTextColor(getResources().getColor(R.color.colorGreen));
//        mviewpager.setOnPageChangeListener(this);
        //        rll_home.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
        //        rll_nearby.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
        //        rll_mine.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomePageFragment());
        fragments.add(new NearbyFragment());
        fragments.add(new MineFragment());
        mviewpager.setOffscreenPageLimit(2);//设置viewpager缓存页数.
        mviewpager.setAdapter(new MainFragmentPageAdapter(getSupportFragmentManager(), fragments));

    }

    @SuppressLint("CheckResult")
    @Override
    public void initData() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {//同意权限
                        Log.d("rxPermissions", "tongyi");
                    } else {//拒绝权限
                        Log.d("rxPermissions", "jujue");
                        MaterialDialog materialDialog = new MaterialDialog.Builder(getContext())
                                .title("权限提示")
                                .content("该权限是软件必须获取的权限，如果拒绝可能导致核心功能不可使用，请在设置中赋予该权限。")
                                .positiveText("去设置")
                                .onPositive((dialog, which) -> {
                                    Log.d("materialDialog", "去设置");
                                    Intent localIntent = new Intent();
                                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    if (Build.VERSION.SDK_INT >= 9) {
                                        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                        localIntent.setData(Uri.fromParts("package", getContext().getPackageName(), null));
                                    } else if (Build.VERSION.SDK_INT <= 8) {
                                        localIntent.setAction(Intent.ACTION_VIEW);
                                        localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                                        localIntent.putExtra("com.android.settings.ApplicationPkgName", getContext().getPackageName());
                                    }
                                    getContext().startActivity(localIntent);
                                })
                                .negativeText("取消")
                                .onNegative((dialog, which) -> Log.d("materialDialog", "取消"))
                                .show();
                    }
                });
    }

    @Override
    public void widgetClick(View view) {
        LogD("Main-widgetClick");
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
            LogD("onPageSelected: 0");
            setHomeClick();
        } else if (position == 1) {
            LogD("onPageSelected: 1");
            setNearbyClick();
        } else if (position == 2) {
            LogD("onPageSelected: 2");
            setMineClikc();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setNearbyClick() {
        mviewpager.setCurrentItem(1);
        /*
         * 设置为选中色
         */
        img_rll_nearby.setImageDrawable(getResources().getDrawable(R.drawable.green_fujin));
        tv_rll_nearby.setTextColor(getResources().getColor(R.color.colorGreen));
        /*
         * 设置为未选中色
         */
        img_rll_home.setImageDrawable(getResources().getDrawable(R.drawable.sye));
        tv_rll_home.setTextColor(getResources().getColor(R.color.colorGray));

        img_rll_mine.setImageDrawable(getResources().getDrawable(R.drawable.wod));
        tv_rll_mine.setTextColor(getResources().getColor(R.color.colorGray));
    }

    private void setHomeClick() {
        mviewpager.setCurrentItem(0);
        /*
         * 设置为选中色
         */
        img_rll_home.setImageDrawable(getResources().getDrawable(R.drawable.green_sye));
        tv_rll_home.setTextColor(getResources().getColor(R.color.colorGreen));
        /*
         * 设置为未选中色
         */
        img_rll_nearby.setImageDrawable(getResources().getDrawable(R.drawable.fujin));
        tv_rll_nearby.setTextColor(getResources().getColor(R.color.colorGray));

        img_rll_mine.setImageDrawable(getResources().getDrawable(R.drawable.wod));
        tv_rll_mine.setTextColor(getResources().getColor(R.color.colorGray));
    }

    private void setMineClikc() {
        mviewpager.setCurrentItem(2);
        /*
         * 设置为选中色
         */
        img_rll_mine.setImageDrawable(getResources().getDrawable(R.drawable.green_wod));
        tv_rll_mine.setTextColor(getResources().getColor(R.color.colorGreen));
        /*
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
        MApplication.getInstance().destroy();
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
