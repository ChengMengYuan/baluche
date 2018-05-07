package com.baluche.presenter;

import android.util.Log;

import com.baluche.model.http.entity.Register;
import com.baluche.model.http.http.HttpMethods;
import com.baluche.view.api.IRegisterACT;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: RegisterPre<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/7 10:10<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:注册界面业务逻辑控制器<p>
 */
public class RegisterPre extends UserPre {
    private IRegisterACT iRegisterACT;

    public RegisterPre(IRegisterACT iRegisterACT) {
        this.iRegisterACT = iRegisterACT;
    }

    /**
     * @param phoneNumber 注册的手机号
     * @param password    注册的密码
     */
    public void toResister(String phoneNumber, String password) {
        if (isPhoneNumber(phoneNumber)) {
            if (isPassWord(password)) {
                startRegister();
            } else {
                iRegisterACT.showIsNotPassWord();
            }
        } else {
            iRegisterACT.showIsNotPhoneNumber();
        }
    }

    /**
     * 开始调用注册接口
     */
    private void startRegister() {
        iRegisterACT.showWaitDioLog();
        HttpMethods.getInstance().getRegister(new Observer<Register>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Register register) {
                iRegisterACT.dismissWaitDioLog();
                Log.d("http+resister", "getMessage" + register.getMessage());
                Log.d("http+resister", "getCode" + register.getCode());
                switch (register.getCode()) {
                    case 200:
                        iRegisterACT.registerSucceed();
                        break;
                    default:
                        iRegisterACT.registerErr(register.getMessage());
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                iRegisterACT.registerErr("网络请求失败,请检查手机网络配置");
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
