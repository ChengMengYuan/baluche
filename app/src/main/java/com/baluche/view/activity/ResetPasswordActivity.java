package com.baluche.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.http.http.HttpMethods;
import com.baluche.model.entity.MyJoke;
import com.baluche.model.entity.SMScode;
import com.baluche.other.CodeTimer;
import com.baluche.other.CodeTimerService;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ResetPasswordActivity extends BaseActivity {
    public static final String CODE = "code";
    private Intent mCodeTimerServiceIntent;

    private EditText reset_account_edit;             //手机号输入框
    private EditText reset_password_edit;            //密码输入框
    private EditText reset_verification_code_edit;   //验证码输入框

    private TextView send_code_bt_reset;             //发送验证码
    private Button reset_password_btn;             //设置密码按钮

    private BroadcastReceiver mCodeTimerReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (CODE.equals(action)) {
                //接收信息，改变button的点击状态和text
                boolean isEnable = intent.getBooleanExtra(CodeTimer.IS_ENABLE, false);
                String message = intent.getStringExtra(CodeTimer.MESSAGE);
                send_code_bt_reset.setEnabled(isEnable);
                send_code_bt_reset.setText(message);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_reset_password);
    }

    @Override
    public void setActivityPre() {

    }

    @Override
    public void initView() {
        reset_account_edit = findViewById(R.id.reset_account_edit);
        reset_password_edit = findViewById(R.id.reset_password_edit);
        reset_verification_code_edit = findViewById(R.id.reset_verification_code_edit);
        send_code_bt_reset = findViewById(R.id.send_code_bt_reset);
        reset_password_btn = findViewById(R.id.reset_password_btn);
        send_code_bt_reset.setOnClickListener(this);
        reset_password_btn.setOnClickListener(this);

        //验证码计时器服务
        mCodeTimerServiceIntent = new Intent(this, CodeTimerService.class);
        mCodeTimerServiceIntent.setAction(CODE);
        //注册接收验证码计时器信息的广播
        IntentFilter filter = new IntentFilter(CODE);
        registerReceiver(mCodeTimerReceiver, filter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.send_code_bt_reset://发送验证码
                HttpMethods.getInstance().getSMScode(new Observer<SMScode>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SMScode smScode) {
                        smScode.getCode();
                        send_code_bt_reset.setEnabled(false);
                        startService(mCodeTimerServiceIntent);//启动服务
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
            case R.id.reset_password_btn://重置密码
                HttpMethods.getInstance().getPasswordBack(new Observer<MyJoke>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyJoke myJoke) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
        }
    }


}
