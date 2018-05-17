package com.baluche.model.http.service;

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

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    @POST("api/weather/ip/fixme")
    Observable<Weather> getWeather(@Body String json);

    /**
     * 轮播图接口
     *
     * @param json
     * @return Banner
     */
    @POST("ad/banner")
    Observable<Banner> getBanner(@Body String json);

    /**
     * 位置信息接口
     *
     * @param json
     * @return Park
     */
    @POST("park/list")
    Observable<Park> getPark(@Body String json);

    /**
     * 注册接口
     *
     * @param json
     * @return
     */
    @POST("user/signin/register")
    Observable<Register> getRegister(@Body String json);

    /**
     * 登录接口
     *
     * @param json
     * @return
     */
    @POST("user/signin/login")
    Observable<Login> getLogin(@Body String json);

    /**
     * 验证码接口
     *
     * @param json
     * @return
     */
    @POST("user/signin/getverifycode")
    Observable<SMScode> getSMScode(@Body String json);


    /**
     * 个人信息查询接口
     *
     * @param json
     * @return
     */
    @POST("user/signin/queryuserinfo")
    Observable<PersonMsg> queryPersonMsg(@Body String json);

    /**
     * 个人信息修改接口
     */
    @POST("user/signin/upuserinfo")
    Observable<PersonMsg> updatePersonMsg(@Body String json);

    /**
     * 头像上传接口
     */
    @POST("user/signin/upuserinfo")
    Observable<Portrait> updatePortrait(@Body String json);

    /**
     * 重置密码接口
     *
     * @param json
     * @return
     */
    @POST("user/signin/getbackpass")
    Observable<BaseResultEntity> getPasswordBack(@Body String json);

    /**
     * 找回密码接口
     *
     * @param json
     * @return
     */
    @POST("user/signin/uppass")
    Observable<BaseResultEntity> updatePassword(@Body String json);


    /**
     * 检测用户是否是微信用户，支付宝用户，并且支持设置密码
     *
     * @param json
     * @return
     */
    @POST("user/signin/thirdparty")
    Observable<BaseResultEntity> cheakUsertype(@Body String json);

    /**
     * 用户绑定车辆
     *
     * @param json
     * @return
     */
    @POST("user/usercar/bindcar")
    Observable<BaseResultEntity> bindCar(@Body String json);

    /**
     * 支付测试
     *
     * @return payTest
     */
    @POST("user/usercar/paytest")
    Observable<PayTest> payTest(@Body String json);

}