package com.baluche.view.api;


/**
 * 文 件 名: IRegisterACT<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/7 10:02<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:注册界面接口<p>
 */
public interface IRegisterACT extends IUser{

    /**
     * 注册成功
     */
    void registerSucceed();

    /**
     * 登录失败
     *
     * @param msg 登录失败后的返回信息
     */
    void registerErr(String msg);

    /**
     * 提示密码非法
     */
    void showIsNotPassWord();

}
