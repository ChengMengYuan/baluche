package com.baluche.view.api;

import com.baluche.base.IBaseView;

/**
 * 文 件 名: ILoginACT<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/3 10:01<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:<p>
 */
public interface ILoginACT extends IBaseView {
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
     * 获取登录的用户名
     */
    void getLoginName();

    /**
     * 获取用户密码
     */
    void getcheckPassWord();

    /**
     * 提示是老用户
     */
    void showIsOldUser();

    /**
     * 提示还没注册
     */
    void showIsNewUser();

    /**
     * 提示等待登录中
     */
    void showWaitDioLog();

    /**
     * 取消等待登录的提示
     */
    void dismissWaitDioLog();

    /**
     * 提示登录失败
     */
    void showErrDiaLog();

}
