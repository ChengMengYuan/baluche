package com.baluche.view.api;

import com.baluche.base.IBaseView;

/**
 * 文 件 名: IPersonMsgACT<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/3 0003 17:25<p>
 * 邮   箱: 2766755768@qq.com<p>
 * 文件说明:<p>
 */
public interface IPersonMsgACT extends IBaseView {

    /**
     * 信息更改成功
     */
    void messageChangeSuccess();

    /**
     * 信息更改失败
     */
    void messageChangeFail();

}
