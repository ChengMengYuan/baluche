package com.baluche.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.baluche.model.http.entity.PayTest;
import com.baluche.model.http.http.HttpMethods;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: AlipayTask<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/17 16:52<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:支付宝支付异步处理类<p>
 */

public class AlipayTask extends AsyncTask {
    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private static final String TAG = "AlipayTask";
    /*后台返回的需要支付的订单*/
    private String OrderStr = "";
    /*待支付的金额,单位是元*/
    private int mMoney;
    //支付宝回调返回的支付结果
    private Map<String, String> result;

    AlipayTask(Context context, int money) {
        mContext = context;
        mMoney = money;
    }

    @Override
    protected void onPreExecute() {
        //支付任务开始前的准备
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        //异步处理支付任务
        Log.d(TAG, "开始支付流程");
        //  构造PayTask 对象
        PayTask alipay = new PayTask((Activity) mContext);
        //  调用支付宝APP，获取支付结果
        result = alipay.payV2(getOrderInfo(), true);
        /*打印支付结果*/
        Log.d(TAG, "doInBackground-->" + OrderStr);
        Log.d(TAG, "调用支付接口，获取支付结果-->" + result);
        Log.d(TAG, "doInBackground: 结束了");

        return result;
    }

    @Override
    protected void onPostExecute(Object o) {
        //根据支付宝返回的支付结果进行处理
        String code = result.get("resultStatus");
        switch (code) {
            case "9000"://订单支付成功
                Log.d(TAG, "onPostExecute: " + "订单支付成功");
                break;
            case "8000"://正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                Log.d(TAG, "onPostExecute: " + "正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态");
                break;
            case "4000"://订单支付失败
                Log.d(TAG, "onPostExecute: " + "订单支付失败");
                break;
            case "5000"://重复请求
                Log.d(TAG, "onPostExecute: " + "重复请求");
                break;
            case "6001"://用户中途取消
                Log.d(TAG, "onPostExecute: " + "用户中途取消");
                break;
            case "6002"://网络连接出错
                Log.d(TAG, "onPostExecute: " + "网络连接出错");
                break;
            case "6004"://支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                Log.d(TAG, "onPostExecute: " + "支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态");
                break;
            default://其它支付错误
                Log.d(TAG, "onPostExecute: " + "其它支付错误");
                break;
        }
    }

    /**
     * 从后台获取支付订单
     *
     * @return OrderStr-->加密后的支付订单字符串
     */
    private String getOrderInfo() {

        Log.d("http-main", "pay_test");
        HttpMethods.getInstance().payTest(mMoney, new Observer<PayTest>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PayTest payTest) {
                Log.d(TAG, "payTest.getCode():" + payTest.getCode());
                Log.d(TAG, payTest.getMessage());
                Log.d(TAG, "onNext-->" + payTest.getData().getOrderStr());
                OrderStr = payTest.getData().getOrderStr();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });

        return OrderStr;
    }

}
