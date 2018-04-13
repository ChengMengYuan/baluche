package com.baluche.http.http;

import android.util.Log;

import com.baluche.app.MApplication;
import com.baluche.http.service.ApiService;
import com.baluche.model.entity.Banner;
import com.baluche.model.entity.Login;
import com.baluche.model.entity.MyJoke;
import com.baluche.model.entity.Park;
import com.baluche.model.entity.PersonMsg;
import com.baluche.model.entity.Portrait;
import com.baluche.model.entity.Register;
import com.baluche.model.entity.SMScode;
import com.baluche.model.entity.Weather;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.baluche.util.EncryptUtil.Getsign;
import static com.baluche.view.activity.LoginActivity.login_name;
import static com.baluche.view.activity.LoginActivity.password;
import static com.baluche.view.activity.RegisterActivity.TSMScode;
import static com.baluche.view.activity.RegisterActivity.re_password;
import static com.baluche.view.activity.RegisterActivity.re_phone;
import static com.baluche.view.fragment.NearbyFragment.Latitude;
import static com.baluche.view.fragment.NearbyFragment.Longitude;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class HttpMethods {
    private static final String BASE_URL = "http://appclient.baluche.cn/";
    private static final int TIME_OUT = 4;
    private Retrofit retrofit;
    private ApiService apiService;

    private HttpMethods() {
        /**
         * 构造函数私有化
         * 并在构造函数中进行retrofit的初始化
         */
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    private static class sinalInstance {
        public static final HttpMethods instance = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return sinalInstance.instance;
    }

    private HashMap kmap = new HashMap();//需要签名校验的sign
    private HashMap pMap = new HashMap();//最后传的参数
    private String time = Calendar.getInstance().getTimeInMillis() + "";
    private Gson gson = new Gson();

    /**
     * 获取天气信息方法
     *
     * @param observer
     */
    public void getWeather(Observer<Weather> observer) {
        kmap.put("time", time);

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        apiService.getWeather(gson.toJson(pMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }

    /**
     * 获取广告轮播图方法
     *
     * @param observer
     */
    public void getBanner(Observer<Banner> observer) {
        kmap.put("time", time);

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);

        apiService.getBanner(gson.toJson(pMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }

    /**
     * 获取停车场列表信息方法
     *
     * @param observer
     */
    public void getPark(Observer<Park> observer) {
        kmap.put("time", time);
        kmap.put("lat", Latitude);
        kmap.put("lng", Longitude);

        pMap.put("sign", Getsign(kmap));//加一个sign的md5验证
        pMap.put("time", time);
        pMap.put("lat", Latitude);
        pMap.put("lng", Longitude);

        String s = gson.toJson(pMap);
        Log.d("http+park", s + "");

        apiService.getPark(gson.toJson(pMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }


    /**
     * 注册方法
     *
     * @param observer
     */
    public void getRegister(Observer<Register> observer) {
        kmap.put("time", time);
        kmap.put("mobile", re_phone);// 手机号
        kmap.put("password", re_password);//密码
        kmap.put("code", TSMScode);//验证码

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        pMap.put("mobile", re_phone);// 手机号
        pMap.put("password", re_password);//密码
        pMap.put("code", TSMScode);//验证码
        Log.d("http+register", "" + gson.toJson(pMap));
        apiService.getRegister(gson.toJson(pMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        kmap.clear();
        pMap.clear();
    }

    /**
     * 登录方法
     *
     * @param observer
     */
    public void getLogin(Observer<Login> observer) {
        kmap.put("time", time);
        kmap.put("mobile", login_name);// 手机号
        kmap.put("password", password);//密码

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        pMap.put("mobile", login_name);// 手机号
        pMap.put("password", password);//密码

        apiService.getLogin(gson.toJson(pMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        kmap.clear();
        pMap.clear();
    }


    /**
     * 发送验证码方法
     *
     * @param observer
     */
    public void getSMScode(Observer<SMScode> observer) {
        kmap.put("time", time);
        kmap.put("mobile", re_phone);// 手机号

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        pMap.put("mobile", re_phone);// 手机号

        Log.d("http+SMScode", "" + gson.toJson(pMap));
        apiService.getSMScode(gson.toJson(pMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }

    /**
     * 查询个人信息方法
     *
     * @param observer
     */
    public void queryPersonMsg(Observer<PersonMsg> observer) {
        kmap.put("time", time);
        kmap.put("token", MApplication.Token);// token

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        pMap.put("token", MApplication.Token);// token

        Log.d("http+SMScode", "" + gson.toJson(pMap));
        apiService.queryPersonMsg(gson.toJson(pMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }

    /**
     * 修改个人信息方法
     *
     * @param observer
     */
    public void updatePersonMsg(Observer<PersonMsg> observer) {
        kmap.put("time", time);
        kmap.put("token", MApplication.Token);// token

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        pMap.put("token", MApplication.Token);// token
        apiService.updatePersonMsg("")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }

    /**
     * 上传头像方法
     *
     * @param observer
     */
    public void updatePortrait(Observer<Portrait> observer) {
        kmap.put("time", time);
        kmap.put("token", MApplication.Token);// token

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        pMap.put("token", MApplication.Token);// token
        apiService.updatePortrait("")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }

    /**
     * 修改密码方法
     *
     * @param observer
     */
    public void updatePassword(Observer<MyJoke> observer) {
        kmap.put("time", time);
        kmap.put("token", MApplication.Token);// token

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        pMap.put("token", MApplication.Token);// token
        apiService.updatePassword("")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }


    /**
     * 找回密码方法
     *
     * @param observer
     */
    public void getPasswordBack(Observer<MyJoke> observer) {
        kmap.put("time", time);
        kmap.put("token", MApplication.Token);// token

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        pMap.put("token", MApplication.Token);// token
        apiService.getPasswordBack("")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }

    /**
     * 检测用户是否是微信用户，支付宝用户，并且支持设置密码
     *
     * @param observer
     */
    public void cheakUsertype(Observer<MyJoke> observer) {
        kmap.put("time", time);
        kmap.put("mobile", login_name);// 手机号

        pMap.put("sign", Getsign(kmap));
        pMap.put("mobile", login_name);// 手机号
        pMap.put("time", time);
        Log.d("cheakUsertype", "" + gson.toJson(pMap));
        apiService.cheakUsertype("" + gson.toJson(pMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }


    /**
     * 用户绑定车辆方法
     *
     * @param observer
     */
    public void bindCar(Observer<MyJoke> observer) {
        kmap.put("time", time);
        kmap.put("token", MApplication.Token);// token

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        pMap.put("token", MApplication.Token);// token
        apiService.bindCar("")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }

}
