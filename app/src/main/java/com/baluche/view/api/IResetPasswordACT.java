package com.baluche.view.api;

/**
 * 文 件 名: IResetPasswordACT<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/7 15:22<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:忘记密码界面接口<p>
 */
public interface IResetPasswordACT extends IUser {
    /**
     * 提示更改密码成功
     */
    void showResetSucceed();

    /**
     * 修改密码错误提示
     *
     * @param msg 错误信息
     */
    void showResetErr(String msg);
}
