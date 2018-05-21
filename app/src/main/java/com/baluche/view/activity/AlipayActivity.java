package com.baluche.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.model.http.entity.PayTest;
import com.baluche.model.http.http.HttpMethods;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AlipayActivity extends BaseActivity {
    private String OrderStr = "";
    private String Paynumber;
    private int Paystyle;
    private TextView alipay_test_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);

//        /*获取Intent中的Bundle对象*/
//        Bundle bundle = this.getIntent().getExtras();
//
//        /*获取Bundle中的数据，注意类型和key*/
//        Paynumber = bundle.getString("Paynumber");
//        Paystyle = bundle.getInt("Paystyle");
    }

    /**
     * [初始化控件]
     */
    @Override
    public void initView() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::widgetClick);

        Intent intent = getIntent();
        alipay_test_text = findViewById(R.id.alipay_test_text);
        alipay_test_text.setText("金额："+intent.getStringExtra("Paynumber")+"支付方式："+intent.getStringExtra("Paystyle"));
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
