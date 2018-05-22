package com.baluche.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
        OrderStr = "alipay_sdk=alipay-sdk-php-20161101&app_id=2018052160225010&biz_content=%7B%22body%22%3A%22%E4%BD%99%E9%A2%9D%E5%85%85%E5%80%BC%22%2C%22subject%22%3A%22%E4%BD%99%E9%A2%9D%E5%85%85%E5%80%BC%22%2C%22out_trade_no%22%3A%22xxxxx4854856%22%2C%22total_amount%22%3A%220.01%22%2C%22timeout_express%22%3A%221m%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.baidu.com&sign_type=RSA2&timestamp=2018-05-22+14%3A20%3A26&version=1.0&sign=rHhT8HibLBw%2FH5OG6u%2BoBuuN%2Fuj7oT2JOEF5LiapTAjtiO89pUp2aL%2FeiVD%2BrWuzti2zkdVadoHZ0YNQN18p72%2Fa%2FJBuwn6EIuf4MxtYjV0D0W8%2BSEHihWKaVfvdT9RPb%2Fc3QnYJbgUrYIZf299zv%2FezJwnQ05p00L%2BGiu%2Bj9eX77EfE0Yadj38l61%2FYWOsxMRGbKe3HdhmHOX39dfSjO0XXH72c72GOB%2B4auOQItbbd%2Fr27u5%2BF3u0sfLMdFnBWvjcxCFXAJID96hrHa1fRf7zdcKZX5vciDnTX%2FUNWzzmI2%2BlyTfikPegWaBIRZJTcFIjt33Uj4q3x6UP7OiV6Sg%3D%3D";
        Log.d(TAG, "" + OrderStr);
        // 调用支付接口，获取支付结果
        String result = alipay.pay(OrderStr, true);
        Log.d(TAG, "调用支付接口，获取支付结果" + result);
        Log.d(TAG, "doInBackground: 结束了");
        Toast.makeText(mContext, result, 1000 * 10).show();
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
