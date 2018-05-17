package com.baluche.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.baluche.model.http.entity.PayTest;
import com.baluche.model.http.http.HttpMethods;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: AlipayTask<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/17 16:52<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:<p>
 */
public class AlipayTask extends AsyncTask {
    private String OrderStr = "";
    private static final String TAG = "AlipayTask";
    private Context mContext;
    private ProgressDialog dialog;
    private int mType;

    public AlipayTask(Context context, int type) {
        mContext = context;
        mType = type;
    }

    @Override
    protected void onPreExecute() {

    }


    @Override
    protected Object doInBackground(Object[] objects) {
        getOrderInfo();
        // 构造PayTask 对象
        PayTask alipay = new PayTask((Activity) mContext);
        Log.d(TAG, OrderStr);
        // 调用支付接口，获取支付结果
        String result = alipay.pay(OrderStr, true);
        return result;
    }

    @Override
    protected void onPostExecute(Object o) {

    }

    private void getOrderInfo() {
        Log.d("http-main", "pay_test");
        HttpMethods.getInstance().payTest(new Observer<PayTest>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PayTest payTest) {
                Log.d("MainActivity", "payTest.getCode():" + payTest.getCode());
                Log.d("MainActivity", payTest.getMessage());
                Log.d("MainActivity", payTest.getData().getOrderStr());
                OrderStr = payTest.getData().getOrderStr();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

}
