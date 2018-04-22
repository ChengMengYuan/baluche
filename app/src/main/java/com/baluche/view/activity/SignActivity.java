package com.baluche.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baluche.R;

/**
 * 签到Activity
 */
public class SignActivity extends BaseActivity {
    private Button sign_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
    }

    @Override
    public void setActivityPre() {

    }

    @Override
    public void initView() {
        sign_bt = findViewById(R.id.sign_bt);
        sign_bt.setOnClickListener(this::widgetClick);
    }

    @Override
    public void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.sign_bt://点击签到后
                // TODO: 2018/4/22 访问后台接口传送签到信息

                break;
        }
    }

    private void changeSignInfo(int num) {
        switch (num) {
            case 1:
                changeOne();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
    }

    private void changeOne() {

    }

    private void changeTwo() {
        changeOne();
    }

    private void changeThree() {
        changeTwo();
    }

    private void changefour() {
        changeThree();
    }

    private void changefive() {
        changefour();
    }

    private void changeSix() {
        changefive();
    }

    private void changeSeven() {
        changeSix();
    }

}
