package com.baluche.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baluche.R;
import com.baluche.model.http.http.HttpMethods;
import com.baluche.model.http.entity.SMScode;
import com.baluche.other.timeservice.CodeTimer;
import com.baluche.other.timeservice.CodeTimerService;
import com.baluche.base.BaseActivity;
import com.baluche.presenter.ResetPasswordPre;
import com.baluche.view.api.IResetPasswordACT;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ResetPasswordActivity extends BaseActivity implements IResetPasswordACT {

    private ResetPasswordPre resetPasswordPre;

    public static final String CODE = "code";
    private Intent mCodeTimerServiceIntent;

    private EditText reset_account_edit;             //手机号输入框
    private String phoneNum;//手机号
    private EditText reset_password_edit;            //密码输入框
    private String newPassword;//密码
    private EditText reset_verification_code_edit;   //验证码输入框
    private String code;//验证码
    private RelativeLayout reset_return_left;

    private TextView send_code_bt_reset;             //发送验证码
    private Button reset_password_btn;             //设置密码按钮

    private BroadcastReceiver mCodeTimerReceiver = new BroadcastReceiver() {
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
        resetPasswordPre = new ResetPasswordPre(this);
    }

    @Override
    public void initView() {
        reset_account_edit = findViewById(R.id.reset_account_edit);
        reset_password_edit = findViewById(R.id.reset_password_edit);
        reset_verification_code_edit = findViewById(R.id.reset_verification_code_edit);
        send_code_bt_reset = findViewById(R.id.send_code_bt_reset);
        reset_password_btn = findViewById(R.id.reset_password_btn);
        reset_return_left = findViewById(R.id.reset_return_left);
        reset_return_left.setOnClickListener(this);
        send_code_bt_reset.setOnClickListener(this);
        reset_password_btn.setOnClickListener(this);

        //验证码计时器服务
        mCodeTimerServiceIntent = new Intent(this, CodeTimerService.class);
        mCodeTimerServiceIntent.setAction(CODE);
        //注册接收验证码计时器信息的广播
        IntentFilter filter = new IntentFilter(CODE);
        registerReceiver(mCodeTimerReceiver, filter);
        /*
         * 监听密码输入框的变化
         */
        reset_password_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                newPassword = reset_password_edit.getText().toString();
            }
        });

        /**
         * 监听手机号输入变化
         */
        reset_account_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phoneNum = reset_account_edit.getText().toString();
            }
        });

        reset_verification_code_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                code = reset_verification_code_edit.getText().toString();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.send_code_bt_reset://发送验证码
                HttpMethods.getInstance().getSMScode(phoneNum, new Observer<SMScode>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SMScode smScode) {
                        Log.d("ResetPasswordActivity", "smScode.getCode():" + smScode.getCode());
                        Log.d("ResetPasswordActivity", "smScode.getMessage():" + smScode.getMessage());
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
                resetPasswordPre.toResetPassWord(phoneNum, newPassword, code);
                break;
            case R.id.reset_return_left:
                finish();
                break;
        }
    }


    /**
     * 提示更改密码成功
     */
    @Override
    public void showResetSucceed() {
        new MaterialDialog.Builder(this)
                .title("提示")
                .content("密码修改成功！")
                .negativeText("确定")
                .show();
    }

    /**
     * 修改密码错误提示
     *
     * @param msg 错误信息
     */
    @Override
    public void showResetErr(String msg) {
        new MaterialDialog.Builder(this)
                .title("提示")
                .content(msg)
                .negativeText("确定")
                .show();
    }

    /**
     * 提示手机号非法
     */
    @Override
    public void showIsNotPhoneNumber() {
        new MaterialDialog.Builder(this)
                .title("提示")
                .content("请输入正确的手机号")
                .negativeText("确定")
                .show();
    }

    /**
     * 提示密码非法
     */
    @Override
    public void showIsNotPassWord() {
        new MaterialDialog.Builder(this)
                .title("提示")
                .content("密码不合法,请重新输入")
                .negativeText("确定")
                .show();
    }


}
