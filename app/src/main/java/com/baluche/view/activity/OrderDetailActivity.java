package com.baluche.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.baluche.R;
import com.baluche.base.BaseActivity;

public class OrderDetailActivity extends BaseActivity {

    private RelativeLayout order_detail_return_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
    }

    @Override
    public void initView() {
        order_detail_return_left = findViewById(R.id.order_detail_return_left);
        order_detail_return_left.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.order_detail_return_left:
                finish();
                break;
        }
    }
}
