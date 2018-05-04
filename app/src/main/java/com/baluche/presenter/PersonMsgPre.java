package com.baluche.presenter;

import com.baluche.base.BasePresenter;
import com.baluche.view.api.ILoginACT;
import com.baluche.view.api.IPersonMsgACT;

/**
 * 文 件 名: PersonMsgPre<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/3 0003 18:05<p>
 * 邮   箱: 2766755768@qq.com<p>
 * 文件说明:<p>
 */
public class PersonMsgPre extends BasePresenter{

    private IPersonMsgACT iPersonMsgACT;

    public PersonMsgPre(IPersonMsgACT iPersonMsgACT) {
        this.iPersonMsgACT = iPersonMsgACT;
    }

    public void sendmessage(){
        iPersonMsgACT.messageChangeSuccess();
    }
}
