package com.baluche.view.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.model.http.http.HttpMethods;
import com.baluche.model.http.entity.Weather;
import com.baluche.view.activity.MainActivity;
import com.baluche.view.activity.MessageActivity;
import com.baluche.view.activity.ParkpayingActivity;
import com.baluche.view.activity.SearchActivity;
import com.baluche.view.activity.SignActivity;
import com.baluche.view.activity.UsehelpActivity;
import com.baluche.view.adapter.FrescoImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class HomePageFragment extends Fragment implements View.OnClickListener {
    /* 首页Title控件 */
//    private ImageView img_weather;//天气图标
//    private TextView tv_weather;//气温
//    private TextView tv_location;//地理位置
    private ImageView img_xiaoxi;//消息图标
    private ImageView xiaohongdian;
    private ImageView img_choice_city;//地理下拉框
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
    private RelativeLayout nav_sign;//签到有礼
    private RelativeLayout nav_help;//使用帮助

    private TextView before_price;//原始价格

    private ImageView home_search;//搜索框

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_homepage, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        kj_banner = v.findViewById(R.id.home_banner);
        before_price = v.findViewById(R.id.before_price);
        before_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//为原始价格添加删除线

//        img_weather = v.findViewById(R.id.img_weather);
//        tv_weather = v.findViewById(R.id.tv_weather);
//        tv_location = v.findViewById(R.id.tv_location);
        img_choice_city = v.findViewById(R.id.img_choice_city);
        img_choice_city.setVisibility(View.INVISIBLE);
        img_xiaoxi = v.findViewById(R.id.img_xiaoxi);
        img_xiaoxi.setOnClickListener(this);

        xiaohongdian = v.findViewById(R.id.xiaohongdian);
        xiaohongdian.setVisibility(View.INVISIBLE);//设置红点为不可见

        home_search = v.findViewById(R.id.home_search);
        home_search.setOnClickListener(this);

        nav_query = v.findViewById(R.id.nav_query);
        nav_query.setOnClickListener(this);

        nav_pay = v.findViewById(R.id.nav_pay);
        nav_pay.setOnClickListener(this);

        nav_help = v.findViewById(R.id.nav_help);
        nav_help.setOnClickListener(this);

        nav_sign = v.findViewById(R.id.nav_sign);
        nav_sign.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        Log.d("test", "onClick: ");
        switch (view.getId()) {
            case R.id.img_xiaoxi://消息提示
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_query://车位查询
                ((MainActivity) getActivity()).setNearbyClick();
                break;

            case R.id.nav_pay://停车缴费
                Log.d("停车缴费", "onClick: ");
                Intent intent1 = new Intent(getActivity(), ParkpayingActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_help://使用帮助
                Log.d("使用帮助", "onClick: ");
                Intent intent3 = new Intent(getActivity(), UsehelpActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_sign://签到有礼
                Log.d("签到有礼", "onClick: ");
                Intent intent4 = new Intent(getActivity(), SignActivity.class);
                startActivity(intent4);
                break;

            case R.id.home_search://搜索框
                Intent intent2 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent2);
            default:

                break;
        }
    }

    /**
     * 获取后台信息
     */
    public void getData() {
//        /*获取天气信息*/
//        HttpMethods.getInstance().getWeather(new Observer<Weather>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Weather weather) {
//                Log.d("http+weather", "" + weather.getMessage());
//                if (weather.getCode() == 200) {
//                    String w = weather.getData().getWeather();
//                    tv_weather.setText(weather.getData().getTemp());
//                    tv_location.setText(weather.getData().getCity());
//                    changeWeatherImg(w);
//                } else {//如果接口签名校验不通过
//                    changeWeatherImg("99");
//                    tv_weather.setText("??");
//                    tv_location.setText("??");
//                }
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        /*获取轮播图信息*/
        HttpMethods.getInstance().getBanner(new Observer<com.baluche.model.http.entity.Banner>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(com.baluche.model.http.entity.Banner banner) {
                Log.d("http+banner", "" + banner.getMessage());
                Log.d("http+banner", "" + banner.getCode());
                switch (banner.getCode()) {
                    case 200:
                        for (int i = 0; i < banner.getData().size(); i++) {
                            Log.d("getPhoto", "" + banner.getData().get(i).getPhoto());
                            Log.d("getLink", "" + banner.getData().get(i).getLink());
                            images.add(banner.getData().get(i).getPhoto());
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
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
        });
    }

//    /**
//     * 更改天气图片
//     *
//     * @param weather
//     */
//    private void changeWeatherImg(String weather) {
//        switch (weather) {
//            case "0":
//                //晴
//                img_weather.setImageResource(R.drawable.qing);
//                break;
//            case "1":
//                //多云
//                img_weather.setImageResource(R.drawable.duoyun);
//                break;
//            case "2":
//                //阴天
//                img_weather.setImageResource(R.drawable.yintian);
//                break;
//            case "3":
//                //阵雨
//                img_weather.setImageResource(R.drawable.zhenyu);
//                break;
//            case "4":
//                //雷阵雨
//                img_weather.setImageResource(R.drawable.leizhenyu);
//                break;
//            case "5":
//                //雷阵雨拌有冰雹
//                img_weather.setImageResource(R.drawable.leizhenyubanyoubingbao);
//                break;
//            case "6":
//                //雨夹雪
//                img_weather.setImageResource(R.drawable.yujiaxue);
//                break;
//            case "7":
//                //小雨
//                img_weather.setImageResource(R.drawable.xiaoyu);
//                break;
//            case "8":
//                //中雨
//                img_weather.setImageResource(R.drawable.zhongyu);
//                break;
//            case "9":
//                //大雨
//                img_weather.setImageResource(R.drawable.dayu);
//                break;
//            case "10":
//                //暴雨
//                img_weather.setImageResource(R.drawable.baoyu);
//                break;
//            case "11":
//                //大暴雨
//                img_weather.setImageResource(R.drawable.dabaoyu);
//                break;
//            case "12":
//                //特大暴雨
//                img_weather.setImageResource(R.drawable.tedabaoyu);
//                break;
//            case "13":
//                //阵雪
//                img_weather.setImageResource(R.drawable.zhenxue);
//                break;
//            case "14":
//                //小雪
//                img_weather.setImageResource(R.drawable.xiaoxue);
//                break;
//            case "15":
//                //中雪
//                img_weather.setImageResource(R.drawable.zhongxue);
//                break;
//            case "16":
//                //大雪
//                img_weather.setImageResource(R.drawable.daxue);
//                break;
//            case "17":
//                //暴雪
//                img_weather.setImageResource(R.drawable.baoxue);
//                break;
//            case "18":
//                //雾
//                img_weather.setImageResource(R.drawable.wu);
//                break;
//            case "19":
//                //冻雨
//                img_weather.setImageResource(R.drawable.dongyu);
//                break;
//            case "20":
//                //沙尘暴
//                img_weather.setImageResource(R.drawable.shachenbao);
//                break;
//            case "21":
//                //小雨-中雨
//                img_weather.setImageResource(R.drawable.xiaoyudaozhongyu);
//                break;
//            case "22":
//                //中雨-大雨
//                img_weather.setImageResource(R.drawable.zhongyudaodayu);
//                break;
//            case "23":
//                //大雨-暴雨
//                img_weather.setImageResource(R.drawable.dayudaobaoyu);
//                break;
//            case "24":
//                //暴雨-大暴雨
//                img_weather.setImageResource(R.drawable.baoyudaodabaoyu);
//                break;
//            case "25":
//                //大暴雨-特大暴雨
//                img_weather.setImageResource(R.drawable.dabaoyudaotedabaoyu);
//                break;
//            case "26":
//                //小雪-中雪
//                img_weather.setImageResource(R.drawable.xiaoxuedaozhongxue);
//                break;
//            case "27":
//                //中雪-大雪
//                img_weather.setImageResource(R.drawable.zhongxuedaodaxue);
//                break;
//            case "28":
//                //大雪-暴雪
//                img_weather.setImageResource(R.drawable.daxuedaobaoxue);
//                break;
//            case "29":
//                //浮尘
//                img_weather.setImageResource(R.drawable.fuchen);
//                break;
//            case "30":
//                //扬沙
//                img_weather.setImageResource(R.drawable.yangsha);
//                break;
//            case "31":
//                //沙尘暴
//                img_weather.setImageResource(R.drawable.qiangshachenbao);
//                break;
//            case "32":
//                //浓雾
//                img_weather.setImageResource(R.drawable.nongwu);
//                break;
//            case "49":
//                //浓雾
//                img_weather.setImageResource(R.drawable.qiangnongwu);
//                break;
//            case "53":
//                //霾
//                img_weather.setImageResource(R.drawable.mai);
//                break;
//            case "54":
//                //中毒霾
//                img_weather.setImageResource(R.drawable.zhongdumai);
//                break;
//            case "55":
//                //重度霾
//                img_weather.setImageResource(R.drawable.zhondumai);
//                break;
//            case "56":
//                //严重霾
//                img_weather.setImageResource(R.drawable.yanzhongmai);
//                break;
//            case "57":
//                //大雾
//                img_weather.setImageResource(R.drawable.dawu);
//                break;
//            case "58":
//                //特强浓雾
//                img_weather.setImageResource(R.drawable.teqiangnongwu);
//                break;
//            case "99":
//                //无
//                img_weather.setImageResource(R.drawable.meiyou);
//                break;
//            case "301":
//                //雨
//                img_weather.setImageResource(R.drawable.yu);
//                break;
//            case "302":
//                //雪
//                img_weather.setImageResource(R.drawable.xue);
//                break;
//            default:
//
//                break;
//        }
//    }
}
