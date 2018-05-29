package com.baluche.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.view.adapter.WalletAccountAdapter;

import java.util.ArrayList;

public class WalletActivity extends BaseActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView wallet_account_listView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RelativeLayout wallet_return_left;

    private RelativeLayout wallet_recharge_btn;
    private RelativeLayout wallet_discount;
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
        wallet_return_left = findViewById(R.id.wallet_return_left);
        wallet_return_left.setOnClickListener(this);
        wallet_discount = findViewById(R.id.wallet_discount);
        wallet_discount.setOnClickListener(this);
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
//                startActivity(AlipayActivity.class);
                startActivity(RechargeActivity.class);
                break;
            case R.id.wallet_return_left:
                finish();
                break;
            case R.id.wallet_discount:
                startActivity(DiscountCouponActivity.class);
                break;
        }
    }

}
