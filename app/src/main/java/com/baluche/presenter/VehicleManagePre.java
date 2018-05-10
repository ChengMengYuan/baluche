package com.baluche.presenter;

import com.baluche.base.BasePresenter;
import com.baluche.view.api.IVehicleManageACT;

/**
 * 文 件 名: VehicleManagePre<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/10 0010 10:18<p>
 * 邮   箱: 2766755768@qq.com<p>
 * 文件说明:<p>
 */
public class VehicleManagePre extends BasePresenter {

    private IVehicleManageACT iVehicleManageACT;

    public VehicleManagePre (IVehicleManageACT iVehicleManageACT){
        this.iVehicleManageACT = iVehicleManageACT;
    }

    public void vehicle_manage_add(){
        iVehicleManageACT.vehicle_manage_add();
    }
}
