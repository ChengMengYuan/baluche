package com.baluche.http.service;

import com.baluche.app.Constant;
import com.baluche.model.entity.Banner;
import com.baluche.model.entity.Login;
import com.baluche.model.entity.MyJoke;
import com.baluche.model.entity.Park;
import com.baluche.model.entity.PersonMsg;
import com.baluche.model.entity.Portrait;
import com.baluche.model.entity.Register;
import com.baluche.model.entity.SMScode;
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

    // FIXME: 2018/4/2 0002 天气接口限流暂停使用

    /**
     * 天气接口
     *
     * @return Weather
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})

    @POST("api/weather/ip/fixme")
    Observable<Weather> getWeather(@Body String json);

    /**
     * 轮播图接口
     *
     * @param json
     * @return Banner
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("ad/banner")
    Observable<Banner> getBanner(@Body String json);

    /**
     * 位置信息接口
     *
     * @param json
     * @return Park
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("park/list")
    Observable<Park> getPark(@Body String json);

    /**
     * 注册接口
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("user/signin/register")
    Observable<Register> getRegister(@Body String json);

    /**
     * 登录接口
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("user/signin/login")
    Observable<Login> getLogin(@Body String json);

    /**
     * 验证码接口
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("user/signin/getverifycode")
    Observable<SMScode> getSMScode(@Body String json);


    /**
     * 个人信息查询接口
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("user/signin/queryuserinfo")
    Observable<PersonMsg> queryPersonMsg(@Body String json);

    /**
     * 个人信息修改接口
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("user/signin/upuserinfo")
    Observable<PersonMsg> updatePersonMsg(@Body String json);

    /**
     * 头像上传接口
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("user/signin/upuserinfo")
    Observable<Portrait> updatePortrait(@Body String json);


    // FIXME: 2018/4/8 0008  myjok

    /**
     * 修改密码接口
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("user/signin/uppass")
    Observable<MyJoke> updatePassword(@Body String json);

    /**
     * 找回密码接口
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("user/signin/uppass")
    Observable<MyJoke> getPasswordBack(@Body String json);


    /**
     * 检测用户是否是微信用户，支付宝用户，并且支持设置密码
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("user/signin/thirdparty")
    Observable<MyJoke> cheakUsertype(@Body String json);

    /**
     * 用户绑定车辆
     *
     * @param json
     * @return
     */
    @Headers({"Client-Type:" + "Android", "App-Version:" + Constant.APP_VERSION})
    @POST("user/usercar/bindcar")
    Observable<MyJoke> bindCar(@Body String json);
}