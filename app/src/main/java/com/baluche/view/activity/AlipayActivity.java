package com.baluche.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.model.http.entity.PayTest;
import com.baluche.model.http.http.HttpMethods;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AlipayActivity extends BaseActivity {
    private String OrderStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
    }

    /**
     * [初始化控件]
     */
    @Override
    public void initView() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::widgetClick);
    }

    /**
     * [初始化数据]
     */
    @Override
    public void initData() {

    }

    /**
     * View点击事件(防止快速点击引起的Bug)
     *
     * @param view
     */
    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                new AlipayTask(this, 0).execute();
                break;
        }
    }

}
