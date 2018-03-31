package com.baluche.http.service;

import com.baluche.model.entity.Banner;
import com.baluche.model.entity.Login;
import com.baluche.model.entity.Park;
import com.baluche.model.entity.Register;
import com.baluche.model.entity.Weather;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public interface ApiService {
    // FIXME: 2018/3/23 0023 统一添加所有的http头部信息( @Headers({"Client-Type:" + "Android", "App-Version:" + "test"}))
    //
    //    /**
    //     * 来福笑话接口
    //     *
    //     * @return List<MyJoke>
    //     */
    //    @GET("xiaohua.json")
    //    Observable<List<MyJoke>> getData();

    /**
     * 天气接口
     *
     * @return Weather
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + "test"})
    @POST("api/weather/ip")
    Observable<Weather> getWeather(@Body String json);

    /**
     * 轮播图接口
     *
     * @param json
     * @return Banner
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + "test"})
    @POST("ad/banner")
    Observable<Banner> getBanner(@Body String json);

    /**
     * 位置信息接口
     *
     * @param json
     * @return Park
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + "test"})
    @POST("park/list")
    Observable<Park> getPark(@Body String json);

    /**
     * 注册接口
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + "test"})
    @POST("user/signin/register")
    Observable<Register> getRegister(@Body String json);

    /**
     * 登录接口
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + "test"})
    @POST("user/signin/login")
    Observable<Login> getLogin(@Body String json);
}