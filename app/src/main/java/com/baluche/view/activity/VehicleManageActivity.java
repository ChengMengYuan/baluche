package com.baluche.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.presenter.VehicleManagePre;
import com.baluche.view.adapter.VehicleManageViewAdapter;
import com.baluche.view.api.IVehicleManageACT;

import java.util.ArrayList;

public class VehicleManageActivity extends BaseActivity implements IVehicleManageACT{

    private RecyclerView vehicle_manage_listView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView vehicle_manage_add;
    private ArrayList<String> data = new ArrayList<>();
    private VehicleManagePre vehicleManagePre;
    private RelativeLayout vehicle_manage_return_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_manage);
        vehicleManagePre = new VehicleManagePre(this);

    }


    @Override
    public void initView() {
        vehicle_manage_listView = findViewById(R.id.vehicle_manage_listView);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new VehicleManageViewAdapter(data);
        vehicle_manage_listView.setLayoutManager(mLayoutManager);
        vehicle_manage_listView.setAdapter(mAdapter);
        vehicle_manage_add = findViewById(R.id.vehicle_manage_add);
        vehicle_manage_add.setOnClickListener(this);
        vehicle_manage_return_left = findViewById(R.id.vehicle_manage_return_left);
        vehicle_manage_return_left.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.vehicle_manage_add:
                vehicleManagePre.vehicle_manage_add();
                break;
            case R.id.vehicle_manage_return_left:
                finish();
                break;
        }
    }

    public void vehicle_manage_add(){
        if(data.size()>=3){
            Toast.makeText(this, "已达到最大绑定数量", Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent5 = new Intent(getApplicationContext(), AddVehicleActivity.class);
            startActivity(intent5);
        }
    }
}
