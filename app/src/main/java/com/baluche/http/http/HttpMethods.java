package com.baluche.http.http;

import com.baluche.http.service.ApiService;
import com.baluche.model.entity.Banner;
import com.baluche.model.entity.Park;
import com.baluche.model.entity.Weather;
import com.baluche.view.fragment.NearbyFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    Parameter parameter = new Parameter();

    public void getWeather(Observer<Weather> observer) {

        apiService.getWeather(parameter.returndefaultParame())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getBanner(Observer<Banner> observer) {
        apiService.getBanner(parameter.returndefaultParame())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    public void getPark(Observer<Park> observer) {
        apiService.getPark(NearbyFragment.newInstance().returnParkParame())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

}
