package com.baluche.presenter;

import android.util.Log;

import com.baluche.base.BasePresenter;
import com.baluche.model.http.entity.Login;
import com.baluche.model.http.http.HttpMethods;
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
                        // FIXME: 2018/5/3
                        iLoginACT.loginSucceed();
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
