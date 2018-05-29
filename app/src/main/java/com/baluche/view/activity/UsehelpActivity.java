package com.baluche.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.view.adapter.UsehelpViewAdapter;

import java.util.ArrayList;

public class UsehelpActivity extends BaseActivity {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView usehelp_listView;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> data = new ArrayList<>();
    private RelativeLayout usehelp_return_left;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_help);
    }

    @Override
    public void initView() {
        for (int i = 0; i < 10; i++) {
            data.add("海南停車場" + i);
        }
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new UsehelpViewAdapter(data);
        usehelp_listView = findViewById(R.id.usehelp_listView);
        // 设置布局管理器
        usehelp_listView.setLayoutManager(mLayoutManager);
        // 设置adapter
        usehelp_listView.setAdapter(mAdapter);

        usehelp_return_left = findViewById(R.id.usehelp_return_left);
        usehelp_return_left.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.usehelp_return_left:
                finish();
                break;
        }
    }
}
