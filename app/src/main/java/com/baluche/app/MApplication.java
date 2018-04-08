package com.baluche.app;

import android.app.Activity;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：     cmy
 * @version :     2018/3/19.
 * @e-mil ：      mengyuan.cheng.mier@gmail.com
 * @Description : 将所有的activity添加到List中,方便退出时统一管理
 */
public class MApplication extends Application {
    public static final String APP_NAME = "畅行八路车";
    public static List<Object> activities = new ArrayList<>();
    private static MApplication instance;

    public static String Token = "";
    public static boolean isDebug = true;//Debug开关

    @Override
    public void onCreate() {
        super.onCreate();
        //图片加载框架的初始化
        Fresco.initialize(this);
    }

    public MApplication() {

    }

    /**
     * 获取单例模式中唯一的MyApplication实例
     */
    public static MApplication getInstance() {
        if (instance == null) {
            instance = new MApplication();
        }
        return instance;
    }


    /**
     * 添加activity到容器中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.contains(activity);
        }
    }

    /**
     * 退出App时调用该方法
     * 遍历所有activity并且finish。
     */
    public void destory() {
        for (Object activity : activities) {
            ((Activity) activity).finish();
        }
        System.exit(0);
    }

}