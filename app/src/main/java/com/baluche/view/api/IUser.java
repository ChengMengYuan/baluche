package com.baluche.view.api;

import com.baluche.base.IBaseView;

/**
 * 文 件 名: Iuser<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/7 10:07<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:用户模块公用接口<p>
 */
public interface IUser extends IBaseView {
    /**
     * 等待的Dialog
     */
    void showWaitDioLog();

    /**
     * 取消等待的Dialog
     */
    void dismissWaitDioLog();

    /**
     * 提示手机号非法
     */
    void showIsNotPhoneNumber();

    /**
     * 提示密码非法
     */
    void showIsNotPassWord();

}
