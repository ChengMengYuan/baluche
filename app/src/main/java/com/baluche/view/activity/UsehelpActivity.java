package com.baluche.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baluche.R;
import com.baluche.view.activity.BaseActivity;
import com.baluche.view.adapter.InfromViewAdapter;
import com.baluche.view.adapter.UsehelpViewAdapter;

import java.util.ArrayList;

public class UsehelpActivity extends BaseActivity {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView usehelp_listView;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usehelp);
    }

    @Override
    public void setActivityPre() {

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
    }

    @Override
    public void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View view) {

    }
}
