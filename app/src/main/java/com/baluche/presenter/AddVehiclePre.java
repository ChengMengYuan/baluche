package com.baluche.presenter;

import com.baluche.base.BasePresenter;
import com.baluche.view.api.IAddVehicleACT;
import com.baluche.view.api.IPersonMsgACT;

/**
 * 文 件 名: AddVehiclePre<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/9 0009 16:17<p>
 * 邮   箱: 2766755768@qq.com<p>
 * 文件说明:<p>
 */
public class AddVehiclePre extends BasePresenter{

    private IAddVehicleACT iAddVehicleACT;

    public AddVehiclePre(IAddVehicleACT iAddVehicleACT) {
        this.iAddVehicleACT = iAddVehicleACT;
    }

    public void vehicle(){
        iAddVehicleACT.vehicle();
    }

    public void new_vehicle(){
        iAddVehicleACT.new_vehicle();
    }

    public void keyboard_close_btn(){
        iAddVehicleACT.keyboard_close_btn();
    }

    public void expand_keyboard(){
        iAddVehicleACT.expand_keyboard();
    }
}
