package com.baluche.app;

import com.baluche.http.entity.SMScode;
import com.baluche.http.http.HttpMethods;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HongZhaJi {
    public static String HongZhaJi_phone = "";

    public void startHongZhaJi(String phone, int num) {
        HongZhaJi_phone = phone;
        for (int i = 0; i < num; i++) {
            getSMScode();
        }
    }

    private void getSMScode() {
        HttpMethods.getInstance().getSMScode(new Observer<SMScode>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SMScode smScode) {

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