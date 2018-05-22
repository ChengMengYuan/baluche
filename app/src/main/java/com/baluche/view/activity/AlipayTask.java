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
        Log.d(TAG, "onPreExecute: ");
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        Log.d(TAG, "doInBackground: 开始了");
        getOrderInfo();
        // 构造PayTask 对象
        PayTask alipay = new PayTask((Activity) mContext);
        Log.d(TAG, "" + OrderStr);
        // 调用支付接口，获取支付结果
        String result = alipay.pay(OrderStr, true);
        Log.d(TAG, "调用支付接口，获取支付结果" + result);
        Log.d(TAG, "doInBackground: 结束了");
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
                Log.d("onNext", "payTest.getCode():" + payTest.getCode());
                Log.d("onNext", payTest.getMessage());
                Log.d("onNext", payTest.getData().getOrderStr());

                OrderStr = payTest.getData().getOrderStr();
                Log.d(TAG, OrderStr);
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
