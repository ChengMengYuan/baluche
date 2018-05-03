package com.baluche.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.view.adapter.WalletAccountAdapter;

import java.util.ArrayList;

public class WalletActivity extends BaseActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView wallet_account_listView;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView wallet_recharge_btn;
    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
    }


    @Override
    public void initView() {

        for (int i = 0; i < 10; i++) {
            data.add("海南停車場" + i);
        }
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new WalletAccountAdapter(data);
        wallet_account_listView = findViewById(R.id.wallet_account_listView);
        // 设置布局管理器
        wallet_account_listView.setLayoutManager(mLayoutManager);
        // 设置adapter
        wallet_account_listView.setAdapter(mAdapter);

        wallet_recharge_btn = findViewById(R.id.wallet_recharge_btn);
        wallet_recharge_btn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.wallet_recharge_btn:
                Intent intent1 = new Intent(getApplicationContext(), RechargeActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
