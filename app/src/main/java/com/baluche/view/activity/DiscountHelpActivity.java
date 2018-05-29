package com.baluche.view.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.view.adapter.DiscountHelpAdapter;

import java.util.ArrayList;

public class DiscountHelpActivity extends BaseActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView discount_help_listView;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> data = new ArrayList<>();
    private RelativeLayout discount_help_return_left;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_help);
    }



    @Override
    public void initView() {
        for (int i = 0; i < 10; i++) {
            data.add("海南停車場" + i);
        }
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new DiscountHelpAdapter(data);
        discount_help_listView = findViewById(R.id.discount_help_listView);
        // 设置布局管理器
        discount_help_listView.setLayoutManager(mLayoutManager);
        // 设置adapter
        discount_help_listView.setAdapter(mAdapter);
        discount_help_return_left = findViewById(R.id.discount_return_left);
        discount_help_return_left.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.discount_help_return_left:
                finish();
                break;
        }
    }
}
