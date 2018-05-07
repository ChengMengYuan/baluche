package com.baluche.view.api;

/**
 * 文 件 名: ISignACT<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/7 15:01<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:签到界面接口<p>
 */
public interface ISignACT {
    /**
     * 根据连续签到的次数更改UI
     *
     * @param num 连续签到的天数
     */
    void changeSignInfo(int num);

    /**
     * 达到签到条件之后弹出礼物提示
     */
    void showGiftPic();
}
