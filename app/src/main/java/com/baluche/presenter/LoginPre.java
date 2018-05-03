package com.baluche.presenter;

import android.graphics.Color;
import android.util.Log;

import com.baluche.R;
import com.baluche.base.BasePresenter;
import com.baluche.model.http.entity.Login;
import com.baluche.model.http.entity.MyJoke;
import com.baluche.model.http.http.HttpMethods;
import com.baluche.util.SnackbarUtil;
import com.baluche.view.api.ILoginACT;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: LoginPre<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/3 10:10<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:登录界面业务逻辑代码<p>
 */
public class LoginPre extends BasePresenter {

    private ILoginACT iLoginACT;

    public LoginPre(ILoginACT iLoginACT) {
        this.iLoginACT = iLoginACT;
    }

    public void toLogin() {
        iLoginACT.showWaitDioLog();
        HttpMethods.getInstance().getLogin(new Observer<Login>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Login login) {
                iLoginACT.dismissWaitDioLog();
                Log.d("http+login", "getMessage" + login.getMessage());
                Log.d("http+login", "getCode" + login.getCode());
                switch (login.getCode()) {
                    case 200:
                        iLoginACT.loginSucceed();
                        break;
                    default:
                        iLoginACT.loginErr(login.getMessage());
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void checkIsOldUser(String phoneNumber) {
        if (isPhoneNumber(phoneNumber)) {
            HttpMethods.getInstance().cheakUsertype(new Observer<MyJoke>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(MyJoke myJoke) {
                    Log.d(iLoginACT.TAG, myJoke.getMessage() + "");
                    switch (myJoke.getCode()) {
                        case 200:
                            iLoginACT.showIsOldUser();
                            break;
                        case 10021://手机号输入有误
                            break;
                        case 10019://不是老用户
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onComplete() {

                }
            });
        }

    }

    /**
     * 验证输入手机号条件
     *
     * @param phoneNumber 手机号码
     * @return 如果是符合格式的字符串, 返回 <b>true</b>,否则为 <b>false </b>
     * 11位
     */
    private boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11) {
            return false;
        } else {
            return true;
        }

    }

}
