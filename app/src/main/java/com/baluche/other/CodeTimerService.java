package com.baluche.other;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Administrator on 2018/4/4 0004.
 */

public class CodeTimerService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        //启动计时器
        String action = intent.getAction();
        CodeTimer mCodeTimer = new CodeTimer(this, 60000, 100, action);
        mCodeTimer.start();
    }
}