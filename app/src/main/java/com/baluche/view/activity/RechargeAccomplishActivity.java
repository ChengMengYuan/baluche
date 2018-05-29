package com.baluche.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.base.BaseActivity;

public class RechargeAccomplishActivity extends BaseActivity {

    private RelativeLayout recharge_accomplish_return_left;
    private TextView recharge_accomplish_money_number;
    private TextView recharge_accomplish_pay_ways;
    private ImageView recharge_accomplish_pay_ways_ic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_accomplish);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        recharge_accomplish_return_left = findViewById(R.id.recharge_accomplish_return_left);
        recharge_accomplish_return_left.setOnClickListener(this);
        recharge_accomplish_money_number = findViewById(R.id.recharge_accomplish_money_number);
        recharge_accomplish_money_number.setText(intent.getStringExtra("Paynumber"));
        recharge_accomplish_pay_ways = findViewById(R.id.recharge_accomplish_pay_ways);
        recharge_accomplish_pay_ways.setText(intent.getStringExtra("Paystyle"));
        recharge_accomplish_pay_ways_ic = findViewById(R.id.recharge_accomplish_pay_ways_ic);
        if(intent.getStringExtra("Paystyle").equals("支付宝")){
            recharge_accomplish_pay_ways_ic.setImageResource(R.drawable.alipay);
        }else{
            recharge_accomplish_pay_ways_ic.setImageResource(R.drawable.wechat);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.recharge_accomplish_return_left:
                finish();
                break;
        }
    }
}
