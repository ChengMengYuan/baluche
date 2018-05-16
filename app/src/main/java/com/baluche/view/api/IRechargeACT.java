package com.baluche.view.api;

import com.baluche.base.IBaseView;

/**
 * 文 件 名: IRechargeACT<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/9 0009 17:54<p>
 * 邮   箱: 2766755768@qq.com<p>
 * 文件说明:<p>
 */
public interface IRechargeACT extends IBaseView{

    /**
     * 充值弹窗
     */
    void show_paydialog();

    /**
     *确认支付
     */
    void pay_dialog_sure_btn();
}
