package com.baluche.presenter;

import com.baluche.base.BasePresenter;

/**
 * 文 件 名: UserPre<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/7 10:18<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:用户模块公关业务方法<p>
 */
public class UserPre extends BasePresenter {

    /**
     * 验证输入手机号条件
     *
     * @param phoneNumber 手机号码
     * @return 如果是符合格式的字符串, 返回 <b>true</b>,否则为 <b>false </b>
     * 11位
     */
    boolean isPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 11;
    }

    protected boolean isPassWord(String password) {
        return password.length() >= 6 && password.length() <= 16;
    }
}
