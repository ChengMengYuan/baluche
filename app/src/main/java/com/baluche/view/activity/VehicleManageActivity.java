package com.baluche.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.view.adapter.SearchSuggestionAdapter;
import com.baluche.view.adapter.VehicleManageViewAdapter;

import java.util.ArrayList;

public class VehicleManageActivity extends BaseActivity {

    private RecyclerView vehicle_manage_listView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView vehicle_manage_add;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_manage);
    }

    @Override
    public void setActivityPre() {

    }

    @Override
    public void initView() {
        data.add("红谷");
        data.add("红谷");
        data.add("红谷");
        data.add("红谷");
        data.add("红谷");
        vehicle_manage_listView = findViewById(R.id.vehicle_manage_listView);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new VehicleManageViewAdapter(data);
        vehicle_manage_listView.setLayoutManager(mLayoutManager);
        vehicle_manage_listView.setAdapter(mAdapter);
        vehicle_manage_add = findViewById(R.id.vehicle_manage_add);
        vehicle_manage_add.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.vehicle_manage_add:
                Intent intent5 = new Intent(getApplicationContext(), AddVehicleActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
