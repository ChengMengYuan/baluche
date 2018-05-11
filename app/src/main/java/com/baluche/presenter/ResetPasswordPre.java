package com.baluche.presenter;

import com.baluche.model.http.entity.BaseResultEntity;
import com.baluche.model.http.http.HttpMethods;
import com.baluche.view.api.IResetPasswordACT;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: ResetPasswordPre<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/7 15:19<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:忘记密码界面业务逻辑控制器<p>
 */
public class ResetPasswordPre extends UserPre {
    private IResetPasswordACT iResetPasswordACT;

    public ResetPasswordPre(IResetPasswordACT iResetPasswordACT) {
        this.iResetPasswordACT = iResetPasswordACT;
    }

    /**
     * 开始调用后台接口用来重置密码
     */
    public void toResetPassWord(String phoneNum, String newPassword, String code) {
        if (isPassWord(newPassword)) {
            iResetPasswordACT.showWaitDioLog();
            HttpMethods.getInstance().getPasswordBack(phoneNum, newPassword, code,
                    new Observer<BaseResultEntity>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseResultEntity baseResultEntity) {
                            iResetPasswordACT.dismissWaitDioLog();
                            switch (baseResultEntity.getCode()) {
                                case 200:
                                    iResetPasswordACT.showResetSucceed();
                                    break;
                                default:
                                    iResetPasswordACT.showResetErr(baseResultEntity.getMessage());
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
        } else {
            iResetPasswordACT.showIsNotPassWord();
        }

    }

}
