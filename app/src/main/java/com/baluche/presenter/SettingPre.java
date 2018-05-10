package com.baluche.presenter;

import com.baluche.base.BasePresenter;
import com.baluche.view.api.IRechargeACT;
import com.baluche.view.api.ISettingACT;

/**
 * 文 件 名: SettingPre<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/10 0010 09:37<p>
 * 邮   箱: 2766755768@qq.com<p>
 * 文件说明:<p>
 */
public class SettingPre extends BasePresenter{

    private ISettingACT iSettingACT;

    public SettingPre (ISettingACT iSettingACT) {
        this.iSettingACT = iSettingACT;
    }

    public void showquitdialog(){
        iSettingACT.showquitdialog();
    }

    public void quit_true(){
        iSettingACT.quit_true();
    }

    public void quit_false(){
        iSettingACT.quit_false();
    }
}
