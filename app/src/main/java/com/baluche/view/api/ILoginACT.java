package com.baluche.view.api;


/**
 * 文 件 名: ILoginACT<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/3 10:01<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:登录界面接口<p>
 */
public interface ILoginACT extends IUser {
    /**
     * 登录成功
     */
    void loginSucceed();

    /**
     * 登录失败
     *
     * @param msg 登录失败后的返回信息
     */
    void loginErr(String msg);

    /**
     * 提示是老用户
     */
    void showIsOldUser();
}
