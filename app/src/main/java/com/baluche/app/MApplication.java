package com.baluche.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.baluche.model.database.greendao.GreenDaoManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：     cmy
 * @version :     2018/3/19.
 * @e-mil ：      mengyuan.cheng.mier@gmail.com
 * @Description : 将所有的activity添加到List中,方便退出时统一管理
 */
public class MApplication extends Application {
    //    public static final String APP_NAME = "畅行八路车";
    public static List<Object> activities = new ArrayList<>();
    private static MApplication instance;
    private static Context mContext;

    // 小米注册的APP_ID
    private static final String APP_ID = "2882303761517775981";
    //小米注册的APP_KEY
    private static final String APP_KEY = "5351777530981";
    // 此TAG在adb logcat中检索自己所需要的信息， 只需在命令行终端输入 adb logcat | grep
    // com.xiaomi.mipushdemo
    public static final String TAG = "com.xiaomi.mipushdemo";


    public static String Token = "";
    public static boolean isDebug = true;//Debug开关

    @Override
    public void onCreate() {
        super.onCreate();
        //图片加载框架的初始化
        Fresco.initialize(this);
        mContext = getApplicationContext();
        //数据库框架初始化
        GreenDaoManager.getInstance(mContext);
        Log.d("Token", "Token" + MApplication.Token);

        //判断用户是否已经打开App，详细见下面方法定义
        if (shouldInit()) {
            //注册推送服务
            //注册成功后会向DemoMessageReceiver发送广播
            // 可以从DemoMessageReceiver的onCommandResult方法中MiPushCommandMessage对象参数中获取注册信息
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
            //参数说明
            //context：Android平台上app的上下文，建议传入当前app的application context
            //appID：在开发者网站上注册时生成的，MiPush推送服务颁发给app的唯一认证标识
            //appKey:在开发者网站上注册时生成的，与appID相对应，用于验证appID是否合法
        }
    }


    //通过判断手机里的所有进程是否有这个App的进程
    //从而判断该App是否有打开
    private boolean shouldInit() {
        //通过ActivityManager我们可以获得系统里正在运行的activities
        //包括进程(Process)等、应用程序/包、服务(Service)、任务(Task)信息。
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        assert am != null;
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();

        //获取本App的唯一标识
        int myPid = android.os.Process.myPid();
        //利用一个增强for循环取出手机里的所有进程
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            //通过比较进程的唯一标识和包名判断进程里是否存在该App
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
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
     * @param activity activity
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

    public static Context getContext() {
        return mContext;
    }
}