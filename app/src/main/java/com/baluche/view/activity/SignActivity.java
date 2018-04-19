package com.baluche.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.baluche.R;

/**
 * 签到Activity
 * 拥有一个自定义的签到控件,点击签到按钮后重新绘制View
 */
public class SignActivity extends BaseActivity {

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
