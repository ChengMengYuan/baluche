package com.baluche.http.http_methods;

import android.util.Log;

import com.baluche.http.service.ApiService;
import com.baluche.model.entity.Banner;
import com.baluche.model.entity.Park;
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

    //    public void getJoke(Observer<List<MyJoke>> observer) {
    //        apiService.getData()
    //                .subscribeOn(Schedulers.io())
    //                .unsubscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread())
    //                .subscribe(observer);
    //    }
    Gson gson = new Gson();
    String a = "a";

    public void getWeather(Observer<Weather> observer) {
        // FIXME: 2018/3/24 0024

        HashMap hashmap = new HashMap();//需要传送的数据

        HashMap map = new HashMap();
        //        map.put("aa", "aa");
        //        map.put("cc", "bb");
        //        map.put("bb", "cc");
        String time = Calendar.getInstance().getTimeInMillis() + "";
        map.put("time", time);

        hashmap.put("sign", Getsign(map));//加一个sign的md5验证

        //        hashmap.put("aa", "aa");
        //        hashmap.put("cc", "bb");
        //        hashmap.put("bb", "cc");
        hashmap.put("time", time);


        String s = gson.toJson(hashmap);
        Log.d("gson", s + "");

        apiService.getWeather(gson.toJson(hashmap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getBanner(Observer<Banner> observer) {

        HashMap hashmap = new HashMap();//需要传送的数据

        HashMap map = new HashMap();
        //        map.put("aa", "aa");
        //        map.put("cc", "bb");
        //        map.put("bb", "cc");
        String time = Calendar.getInstance().getTimeInMillis() + "";
        map.put("time", time);

        hashmap.put("sign", Getsign(map));//加一个sign的md5验证

        //        hashmap.put("aa", "aa");
        //        hashmap.put("cc", "bb");
        //        hashmap.put("bb", "cc");
        hashmap.put("time", time);


        String s = gson.toJson(hashmap);
        Log.d("gson", s + "");
        apiService.getBanner(gson.toJson(hashmap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    public void getPark(Observer<Park> observer) {
        apiService.getPark("")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

}
