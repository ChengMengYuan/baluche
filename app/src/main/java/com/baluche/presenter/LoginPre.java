package com.baluche.presenter;

import android.util.Log;

import com.baluche.model.http.entity.BaseResultEntity;
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
 * 文件说明:登录界面业务逻辑控制器<p>
 */
public class LoginPre extends UserPre {

    private ILoginACT iLoginACT;

    public LoginPre(ILoginACT iLoginACT) {
        this.iLoginACT = iLoginACT;
    }

    /**
     * 用户点击登录后的逻辑
     */
    public void toLogin() {
        iLoginACT.showWaitDioLog();
        /*访问后台来获取登录数据*/
        HttpMethods.getInstance().getLogin(new Observer<Login>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Login login) {
                iLoginACT.dismissWaitDioLog();
                Log.d("http+login", "getMessage" + login.getMessage());
                Log.d("http+login", "getCode" + login.getCode());
                if (login.getCode() == 200) {
                    Log.d("http+login", "getToken" + login.getData().getToken());
                }
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

    /**
     * 检查是否是平台老用户
     *
     * @param phoneNumber 登录的电话号码
     */
    public void checkIsOldUser(String phoneNumber) {
        if (isPhoneNumber(phoneNumber)) {//手机号格式正确
            //开始调用登录后台
            HttpMethods.getInstance().cheakUsertype(new Observer<BaseResultEntity>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(BaseResultEntity entity) {
                    Log.d(iLoginACT.TAG, entity.getMessage() + "");
                    switch (entity.getCode()) {
                        case 200://是老用户
                            iLoginACT.showIsOldUser();
                            break;
                        case 10019://不是老用户
                            break;
                        case 10021://手机号输入有误
                            iLoginACT.showIsNotPhoneNumber();
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
        } else {//手机号格式不正确
            //给出提示
            iLoginACT.showIsNotPhoneNumber();
        }

    }

}
