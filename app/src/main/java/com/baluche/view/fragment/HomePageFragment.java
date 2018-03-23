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
import com.baluche.http.http_methods.HttpMethods;
import com.baluche.model.entity.Weather;
import com.baluche.view.adapter.FrescoImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


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
    private Banner kj_banner;//轮播图框架

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
        kj_banner = v.findViewById(R.id.home_banner);
        before_price = v.findViewById(R.id.before_price);
        before_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//为原始价格添加删除线

        img_weather = v.findViewById(R.id.img_weather);
        tv_weather = v.findViewById(R.id.tv_weather);
        img_xiaoxi = v.findViewById(R.id.img_xiaoxi);
        img_xiaoxi.setOnClickListener(this);

        home_search = v.findViewById(R.id.home_search);

        nav_query = v.findViewById(R.id.nav_query);
        nav_query.setOnClickListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        images = new ArrayList();
        titles = new ArrayList();
        getData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //结束轮播
        kj_banner.stopAutoPlay();
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
    //    private void putImgInBanner(List<String> photos) {
    //        images = photos;
    //        //        // FIXME: 2018/3/22 0022 从后台获取图片
    //        //        images.add("http://d.hiphotos.baidu.com/image/pic/item/d833c895d143ad4b3ae286d88e025aafa50f06de.jpg");
    //        //        images.add("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd09ccfb7903b30f2442a70fa9.jpg");
    //        //        images.add("http://d.hiphotos.baidu.com/image/pic/item/f9198618367adab45913c15e87d4b31c8601e4e8.jpg");
    //        //        //        titles.add("+++++++");
    //        //        //        titles.add("-------");
    //        //        //        titles.add("=======");
    //    }
    @Override
    public void onClick(View view) {
        // TODO: 2018/3/22 0022 完成点击事件 
        switch (view.getId()) {
            case R.id.img_xiaoxi://消息提示
                Toast.makeText(context, "消息提示", Toast.LENGTH_LONG).show();
                Log.d("onClick", "消息提示");

                break;

            case R.id.nav_query://车位查询
                Toast.makeText(context, "车位查询", Toast.LENGTH_LONG).show();
                Log.d("onClick", "车位查询");

                //                getData();
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

    /**
     * 获取后台信息
     */
    public void getData() {
        /*获取天气信息*/
        HttpMethods.getInstance().getWeather(new Observer<Weather>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Weather weather) {
                String w = weather.getData().getWeather();
                tv_weather.setText(weather.getData().getTemp());
                changeWeatherImg(w);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });

        /*获取轮播图信息*/
        HttpMethods.getInstance().getBanner(new Observer<com.baluche.model.entity.Banner>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(com.baluche.model.entity.Banner banner) {
                for (int i = 0; i < banner.getData().size(); i++) {
                    images.add(banner.getData().get(i).getPhoto());
                    Log.d("banner", banner.getData().get(i).getPhoto() + "");
                    banner.getData().get(i).getLink();
                }
                //设置banner样式
                kj_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                kj_banner.setImageLoader(new FrescoImageLoader());
                //设置图片集合
                kj_banner.setImages(images);
                //设置banner动画效果
                kj_banner.setBannerAnimation(Transformer.DepthPage);
                //设置标题集合（当banner样式有显示title时）
                kj_banner.setBannerTitles(titles);
                //设置自动轮播，默认为true
                kj_banner.isAutoPlay(true);
                //设置轮播时间
                kj_banner.setDelayTime(1500);
                //设置指示器位置（当banner模式中有指示器时）
                kj_banner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                kj_banner.start();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 更改天气图片
     *
     * @param weather
     */
    private void changeWeatherImg(String weather) {
        // TODO: 2018/3/20 更新天气图标以及气温
        switch (weather) {
            case "0":
                //晴
                img_weather.setImageResource(R.drawable.dabaoyu);
                break;
            case "1":
                //多云
                break;
            case "2":
                //阴天
                break;
            case "3":
                //阵雨
                break;
            case "4":
                //雷阵雨
                break;
            case "5":
                //雷阵雨拌有冰雹
                break;
            case "6":
                //雨夹雪
                break;
            case "7":
                //小雨
                break;
            case "8":
                //中雨
                break;
            case "9":
                //大雨
                break;
            case "10":
                //暴雨
                break;
            case "11":
                //大暴雨
                break;
            case "12":
                //特大暴雨
                break;
            case "13":
                //阵雪
                break;
            case "14":
                //小雪
                break;
            case "15":
                //中雪
                break;
            case "16":
                //大雪
                break;
            case "17":
                //暴雪
                break;
            case "18":
                //雾
                break;
            case "19":
                //冻雨
                break;
            case "20":
                //沙尘暴
                break;
            case "21":
                //小雨-中雨
                break;
            case "22":
                //中雨-大雨
                break;
            case "23":
                //大雨-暴雨
                break;
            case "24":
                //暴雨-大暴雨
                break;
            case "25":
                //大暴雨-特大暴雨
                break;
            case "26":
                //小雪-中雪
                break;
            case "27":
                //中雪-大雪
                break;
            case "28":
                //大雪-暴雪
                break;
            case "29":
                //浮尘
                break;
            case "30":
                //扬沙
                break;
            case "31":
                //沙尘暴
                break;
            case "32":
                //浓雾
                break;
            case "49":
                //浓雾
                break;
            case "53":
                //霾
                break;
            case "54":
                //中毒霾
                break;
            case "55":
                //重度霾
                break;
            case "56":
                //严重霾
                break;
            case "57":
                //大雾
                break;
            case "58":
                //特强浓雾
                break;
            case "99":
                //无
                break;
            case "301":
                //雨
                break;
            case "302":
                //雪
                break;
            default:

                break;
        }
    }
}
