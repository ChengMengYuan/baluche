package com.baluche.model.http.http;

import android.util.Log;

import com.baluche.app.Constant;
import com.baluche.app.MApplication;
import com.baluche.model.http.entity.Banner;
import com.baluche.model.http.entity.BaseResultEntity;
import com.baluche.model.http.entity.Login;
import com.baluche.model.http.entity.Park;
import com.baluche.model.http.entity.PayTest;
import com.baluche.model.http.entity.PersonMsg;
import com.baluche.model.http.entity.Portrait;
import com.baluche.model.http.entity.Register;
import com.baluche.model.http.entity.SMScode;
import com.baluche.model.http.entity.Weather;
import com.baluche.model.http.service.ApiService;
import com.baluche.util.SystemUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.baluche.util.EncryptUtil.getSign;
import static com.baluche.view.activity.LoginActivity.login_name;
import static com.baluche.view.activity.LoginActivity.password;
import static com.baluche.view.activity.RegisterActivity.TSMScode;
import static com.baluche.view.activity.RegisterActivity.re_password;
import static com.baluche.view.activity.RegisterActivity.re_phone;
//import static com.baluche.view.fragment.NearbyFragment.Latitude;
//import static com.baluche.view.fragment.NearbyFragment.Longitude;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class HttpMethods {
    private static final String BASE_URL = "http://appclient.baluche.cn/";
    private static final int TIME_OUT = 4;
    private Retrofit retrofit;
    private ApiService apiService;

    private HttpMethods() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        /**
         * 构造函数私有化
         * 并在构造函数中进行retrofit的初始化
         */
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {//添加统一的http请求头
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Client-Type", "Android")//操作系统
                            .addHeader("App-Version", Constant.APP_VERSION)//App版本
                            /*用户设备的基本信息*/
                            .removeHeader("User-Agent")
                            .addHeader("User-Agent", getUserAgent())
                            .build();
                    return chain.proceed(request);
                })
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

    }


    /**
     * 用于获取用户设备的基本信息
     *
     * @return String
     */
    private static String getUserAgent() {
        String userAgent = "";
        //获取设备型号
        String systemModel = SystemUtil.getSystemModel();
        //系统版本号
        String systemVersion = SystemUtil.getSystemVersion();
        //设备厂商
        String deviceBrand = SystemUtil.getDeviceBrand();
        //系统语言
        String Language = SystemUtil.getSystemLanguage();
        userAgent = ""
                + "/deviceBrand:/" + deviceBrand
                + "/systemModel:/" + systemModel
                + "/systemVersion/" + systemVersion
                + "/Language/" + Language
        ;
//        Logger.d("userAgent", ":" + userAgent);
//        Logger.d("手机型号", ":" + systemModel);
//        Logger.d("系统版本", ":" + systemVersion);
//        Logger.d("手机厂商", ":" + deviceBrand);
        return userAgent;
    }


    private static class singlInstance {//单例模式
        public static final HttpMethods instance = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return singlInstance.instance;
    }

    /*需要签名校验的参数*/
    private HashMap kmap = new HashMap();
    /*最后传的参数*/
    private HashMap pMap = new HashMap();
    /*当前设备的时间*/
    private String time = Calendar.getInstance().getTimeInMillis() + "";
    private Gson gson = new Gson();


    /**
     * 获取天气信息方法
     *
     * @param observer
     */
    public void getWeather(Observer<Weather> observer) {
        kmap.put("time", time);

        pMap.put("sign", getSign(kmap));
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

        pMap.put("sign", getSign(kmap));
        pMap.put("time", time);

        Logger.d("" + gson.toJson(pMap));
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
    public void getPark(double Latitude, double Longitude, Observer<Park> observer) {
        kmap.put("time", time);
        kmap.put("lat", Latitude);
        kmap.put("lng", Longitude);

        pMap.put("sign", getSign(kmap));//加一个sign的md5验证
        pMap.put("time", time);
        pMap.put("lat", Latitude);
        pMap.put("lng", Longitude);

        apiService.getPark(gson.toJson(pMap))
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
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

        pMap.put("sign", getSign(kmap));
        pMap.put("time", time);
        pMap.put("mobile", re_phone);// 手机号
        pMap.put("password", re_password);//密码
        pMap.put("code", TSMScode);//验证码
        Logger.d("http+register", "" + gson.toJson(pMap));
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

        pMap.put("sign", getSign(kmap));
        pMap.put("time", time);
        pMap.put("mobile", login_name);// 手机号
        pMap.put("password", password);//密码
        Log.d("http", "getLogin: " + pMap);
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
     * @param re_phone 手机号
     * @param observer observer
     */
    public void getSMScode(String re_phone, Observer<SMScode> observer) {
        kmap.put("time", time);
        kmap.put("mobile", re_phone);


        pMap.put("sign", getSign(kmap));
        pMap.put("time", time);
        pMap.put("mobile", re_phone);


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

        pMap.put("sign", getSign(kmap));
        pMap.put("time", time);
        pMap.put("token", MApplication.Token);// token

        Logger.d("http+SMScode", "" + gson.toJson(pMap));
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
    public void updatePersonMsg(Object name, int age, String bir, Observer<PersonMsg> observer) {
        apiService.updatePersonMsg("")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 上传头像方法
     *
     * @param observer
     */
    public void updatePortrait(Observer<Portrait> observer) {

        kmap.put("time", time);

        pMap.put("sign", getSign(kmap));
        pMap.put("time", time);

        apiService.updatePortrait(gson.toJson(pMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        kmap.clear();
        pMap.clear();
    }


    /**
     * 重置密码方法
     *
     * @param phoneNum    手机号
     * @param newPassword 新密码
     * @param code        验证码
     * @param observer    observer
     */
    public void getPasswordBack(String phoneNum, String newPassword, String code, Observer<BaseResultEntity> observer) {
        kmap.put("time", time);
        kmap.put("mobile", phoneNum);
        kmap.put("password", newPassword);
        kmap.put("comfirmpassword", newPassword);
        kmap.put("code", code);

        pMap.put("sign", getSign(kmap));
        pMap.put("time", time);
        pMap.put("mobile", phoneNum);
        pMap.put("password", newPassword);
        pMap.put("comfirmpassword", newPassword);
        pMap.put("code", code);
        Log.d("HttpMethods", gson.toJson(pMap));
        apiService.getPasswordBack(gson.toJson(pMap))
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
    public void cheakUsertype(Observer<BaseResultEntity> observer) {
        kmap.put("time", time);
        kmap.put("mobile", login_name);// 手机号

        pMap.put("sign", getSign(kmap));
        pMap.put("mobile", login_name);// 手机号
        pMap.put("time", time);
        Logger.d("cheakUsertype", "" + gson.toJson(pMap));
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
    public void bindCar(Observer<BaseResultEntity> observer) {
        kmap.put("time", time);
        kmap.put("token", MApplication.Token);// token

        pMap.put("sign", getSign(kmap));
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

    /**
     * 支付宝测试接口
     *
     * @param observer observer
     */
    public void payTest(int money, Observer<PayTest> observer) {
        Log.d("HttpMethods", "我他妈真的发了请求啊！");
        kmap.put("time", time);
        kmap.put("money", money);

        pMap.put("sign", getSign(kmap));
        pMap.put("time", time);
        pMap.put("money", money);
        Log.d("HttpMethods", gson.toJson(pMap));
        apiService.payTest(gson.toJson(pMap))
                .subscribe(observer);
        Log.d("HttpMethods", "我他妈请求发完了啊！");
    }

}
