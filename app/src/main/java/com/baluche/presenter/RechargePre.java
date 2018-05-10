package com.baluche.presenter;

import com.baluche.base.BasePresenter;
import com.baluche.view.api.IPersonMsgACT;
import com.baluche.view.api.IRechargeACT;

/**
 * 文 件 名: RechargePre<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/9 0009 18:00<p>
 * 邮   箱: 2766755768@qq.com<p>
 * 文件说明:<p>
 */
public class RechargePre extends BasePresenter {

    private IRechargeACT iRechargeACT;

    public RechargePre(IRechargeACT iRechargeACT) {
        this.iRechargeACT = iRechargeACT;
    }

    public void show_paydialog(){
        iRechargeACT.show_paydialog();
    }

    public void paydialogstyle(){
        iRechargeACT.paydialogstyle();
    }
}
