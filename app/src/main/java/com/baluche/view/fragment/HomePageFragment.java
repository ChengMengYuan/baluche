package com.baluche.view.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.view.adapter.FrescoImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends Fragment implements View.OnClickListener {
    private Context context;
    /* 首页Title控件 */
    private ImageView img_weather;//天气图标
    private TextView tv_weather;//气温
    private ImageView img_xiaoxi;//消息图标
    /* 轮播图控件 */
    /**
     * https://github.com/youth5201314/banner
     */
    private List images;//轮播图图片集合
    private List titles;//轮播图图片标题集合
    private Banner banner;//轮播图框架

    /* 四大模块控件 */
    private RelativeLayout nav_query;//车位查询
    private RelativeLayout nav_pay;//停车缴费
    private RelativeLayout nav_coupon;//车位预约
    private RelativeLayout nav_help;//使用帮助


    private TextView before_price;//原始价格

    private SearchView home_search;//搜索框

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_homepage, container, false);
        context = getContext();
        initView(v);
        return v;
    }

    private void initView(View v) {
        banner = v.findViewById(R.id.home_banner);
        before_price = v.findViewById(R.id.before_price);
        before_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//为原始价格添加删除线

        img_weather = v.findViewById(R.id.img_weather);
        tv_weather = v.findViewById(R.id.tv_weather);
        img_xiaoxi = v.findViewById(R.id.img_xiaoxi);
        img_xiaoxi.setOnClickListener(this);

        home_search = v.findViewById(R.id.home_search);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        images = new ArrayList();
        titles = new ArrayList();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        putImgInBanner();
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new FrescoImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //结束轮播
        banner.stopAutoPlay();
    }


    /**
     * 更改天气图片以及温度显示
     *
     * @param i
     */
    private void changeWeatherImg(int i) {
        // TODO: 2018/3/20 更新天气图标以及气温
        switch (i) {
            case 1:

                break;
            case 2:

                break;
            default:

                break;
        }
    }

    /**
     * 更改位置信息
     *
     * @param s
     */
    private void changeLocation(String s) {
        // TODO: 2018/3/20 更新位置信息
    }

    /**
     * 放置广告图片链接
     */
    private void putImgInBanner() {
        // FIXME: 2018/3/22 0022 从后台获取图片
        images.add("http://d.hiphotos.baidu.com/image/pic/item/d833c895d143ad4b3ae286d88e025aafa50f06de.jpg");
        images.add("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd09ccfb7903b30f2442a70fa9.jpg");
        images.add("http://d.hiphotos.baidu.com/image/pic/item/f9198618367adab45913c15e87d4b31c8601e4e8.jpg");
        //        titles.add("+++++++");
        //        titles.add("-------");
        //        titles.add("=======");
    }


    @Override
    public void onClick(View view) {
        // TODO: 2018/3/22 0022 完成点击事件 
        switch (view.getId()) {
            case R.id.img_xiaoxi://消息提示
                Toast.makeText(context, "img_xiaoxi", Toast.LENGTH_LONG).show();
                Log.d("onClick", "img_xiaoxi");

                break;

            case R.id.nav_query://车位查询

                break;

            case R.id.nav_pay://停车缴费
                break;

            case R.id.nav_coupon://车位预约

                break;
            case R.id.nav_help://使用帮助

                break;

            default:

                break;
        }
    }
}
