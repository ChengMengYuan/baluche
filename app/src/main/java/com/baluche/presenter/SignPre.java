package com.baluche.presenter;

import com.baluche.base.BasePresenter;
import com.baluche.view.api.ISignACT;

/**
 * 文 件 名: SignPre<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/7 15:05<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:签到界面业务逻辑控制器<p>
 */
public class SignPre extends BasePresenter {
    private ISignACT iSignACT;
    private int num = 1;

    public SignPre(ISignACT iSignACT) {
        this.iSignACT = iSignACT;
    }

    /**
     * 开始访问签到接口
     */
    public void toSign() {
        switch (num) {
            case 7://连续签到7天后的奖励提示
                iSignACT.changeSignInfo(num);
                iSignACT.showGiftPic();
                break;
            default:
                iSignACT.changeSignInfo(num);
                num++;
                break;
        }
    }
}
