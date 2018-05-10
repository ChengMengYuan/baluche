package com.baluche.view.api;

import com.baluche.base.IBaseView;

/**
 * 文 件 名: IPersonMsgACT<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/3 03 17:25<p>
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

    /**
     * 头像弹窗
     */
    void show_headchange();

    /**
     * 个人信息修改
     */
    void write_personal_name_msg();

    /**
     * 个人生日修改
     */
    void write_timepicker_msg();

    /**
     * 上传信息过程弹窗
     */
    void during_send_personal_name_msg();

    /**
     * 上传信息消失
     */
    void clear_send_personal_name_msg();

    /**
     * 从相册选择照片
     */
    void choosePhoto();

    /**
     * 拍照
     */
    void takePhoto();
}
