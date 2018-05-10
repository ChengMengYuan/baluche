package com.baluche.view.api;

import com.baluche.base.IBaseView;

/**
 * 文 件 名: IAddVehicleACT<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/9 16:14<p>
 * 邮   箱: 2766755768@qq.com<p>
 * 文件说明:<p>
 */
public interface IAddVehicleACT extends IBaseView {

    /**
     * 选择为标准汽车
     */
    void vehicle();

    /**
     * 选择为新能源汽车
     */
    void new_vehicle();

    /**
     * 键盘关闭按钮
     */
    void keyboard_close_btn();

    /**
     * 单击输入框键盘弹出
     */
    void expand_keyboard();
}
