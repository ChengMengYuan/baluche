package com.baluche.view.api;

import com.baluche.base.IBaseView;

/**
 * 文 件 名: ISettingACT<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/10 0010 09:30<p>
 * 邮   箱: 2766755768@qq.com<p>
 * 文件说明:<p>
 */
public interface ISettingACT extends IBaseView{

    /**
     * 退出窗口
     */
    void showquitdialog();

    /**
     * 确认退出
     */
    void quit_true();

    /**
     * 取消退出
     */
    void quit_false();
}
