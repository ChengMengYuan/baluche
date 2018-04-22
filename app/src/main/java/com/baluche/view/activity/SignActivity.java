package com.baluche.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.baluche.R;

/**
 * 签到Activity
 */
public class SignActivity extends BaseActivity {
    private Button sign_bt;
    private ImageView round_1;
    private ImageView line_1;
    private ImageView round_2;
    private ImageView line_2;
    private ImageView round_3;
    private ImageView line_3;
    private ImageView round_4;
    private ImageView line_4;
    private ImageView round_5;
    private ImageView line_5;
    private ImageView round_6;
    private ImageView line_6;
    private ImageView round_7;
    private ImageView line_7;

    int num = 1;

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

        round_1 = findViewById(R.id.round_1);
        line_1 = findViewById(R.id.line_1);
        round_2 = findViewById(R.id.round_2);
        line_2 = findViewById(R.id.line_2);
        round_3 = findViewById(R.id.round_3);
        line_3 = findViewById(R.id.line_3);
        round_4 = findViewById(R.id.round_4);
        line_4 = findViewById(R.id.line_4);
        round_5 = findViewById(R.id.round_5);
        line_5 = findViewById(R.id.line_5);
        round_6 = findViewById(R.id.round_6);
        line_6 = findViewById(R.id.line_6);
        round_7 = findViewById(R.id.round_7);
        line_7 = findViewById(R.id.line_7);
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
                changeSignInfo(num);
                num++;
                break;
        }
    }

    private void changeSignInfo(int num) {
        switch (num) {
            case 1:
                changeOne();
                break;
            case 2:
                changeTwo();
                break;
            case 3:
                changeThree();
                break;
            case 4:
                changefour();
                break;
            case 5:
                changefive();
                break;
            case 6:
                changeSix();
                break;
            case 7:
                changeSeven();
                break;
        }
    }

    private void changeOne() {
        round_1.setImageResource(R.drawable.rounded_baise);
        line_1.setImageResource(R.drawable.line_baise);
    }

    private void changeTwo() {
        changeOne();
        round_2.setImageResource(R.drawable.rounded_baise);
        line_2.setImageResource(R.drawable.line_baise);
    }

    private void changeThree() {
        changeTwo();
        round_3.setImageResource(R.drawable.rounded_baise);
        line_3.setImageResource(R.drawable.line_baise);
    }

    private void changefour() {
        changeThree();
        round_4.setImageResource(R.drawable.rounded_baise);
        line_4.setImageResource(R.drawable.line_baise);
    }

    private void changefive() {
        changefour();
        round_5.setImageResource(R.drawable.rounded_baise);
        line_5.setImageResource(R.drawable.line_baise);
    }

    private void changeSix() {
        changefive();
        round_6.setImageResource(R.drawable.rounded_baise);
        line_6.setImageResource(R.drawable.line_baise);
    }

    private void changeSeven() {
        changeSix();
        round_7.setImageResource(R.drawable.rounded_baise);
        line_7.setImageResource(R.drawable.line_baise);
    }

}
