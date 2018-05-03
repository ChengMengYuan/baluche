package com.baluche.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.app.MApplication;
import com.baluche.model.http.http.HttpMethods;
import com.baluche.model.http.entity.Register;
import com.baluche.model.http.entity.SMScode;
import com.baluche.other.timeservice.CodeTimer;
import com.baluche.other.timeservice.CodeTimerService;
import com.baluche.base.BaseActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class RegisterActivity extends BaseActivity {
    public static final String CODE = "code";
    private Intent mCodeTimerServiceIntent;

    private EditText register_phone; //手机号输入框
    private EditText register_password;//密码输入框
    private EditText register_register_et;//验证码输入框
    private CheckBox register_cb;//协议单选框

    private Button register_register_bt; //注册button
    private TextView register_sendCode; // 发送验证码

    private ImageView register_return_left;//返回按钮

    public static String re_phone = "";
    public static String re_password = "";
    public static String TSMScode = "";

    private boolean flag = false;//判断帐号密码是否合法

    private BroadcastReceiver mCodeTimerReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (CODE.equals(action)) {
                //接收信息，改变button的点击状态和text
                boolean isEnable = intent.getBooleanExtra(CodeTimer.IS_ENABLE, false);
                String message = intent.getStringExtra(CodeTimer.MESSAGE);
                register_sendCode.setEnabled(isEnable);
                register_sendCode.setText(message);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    @Override
    public void initView() {
        register_phone = findViewById(R.id.register_phone);
        register_phone.addTextChangedListener(new TextWatcher() {//监听输入框的变化
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                re_phone = register_phone.getText().toString();
                checkString(re_phone, re_password);
            }
        });
        register_password = findViewById(R.id.register_password);
        register_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                re_password = register_password.getText().toString();
                checkString(re_phone, re_password);
            }
        });
        register_register_et = findViewById(R.id.logon_verification_code_edit);
        register_register_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                TSMScode = register_register_et.getText().toString();
                checkString(re_phone, re_password);
            }
        });

        register_register_bt = findViewById(R.id.register_register_bt);
        register_register_bt.setOnClickListener(this);

        register_cb = findViewById(R.id.register_cb);
        register_cb.setOnClickListener(this);

        register_sendCode = findViewById(R.id.register_sendCode);
        register_sendCode.setOnClickListener(this);

        register_return_left = findViewById(R.id.register_return_left);
        register_return_left.setOnClickListener(this);

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
            case R.id.register_return_left://返回按钮点击事件
                finish();
                break;
            case R.id.register_register_bt://发送验证码按钮点击事件
                HttpMethods.getInstance().getRegister(new Observer<Register>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Register register) {//判断返回code,处理不同的结果
                        int code = register.getCode();
                        switch (code) {
                            case 10000://参数错误
                                toast(register.getMessage());
                                break;
                            case 200://注册成功
                                Log.d("http+register", "" + register.getCode());
                                Log.d("http+register", "" + register.getMessage());
                                MApplication.Token = register.getData().getToken();
                                Log.d("http+register", "" + register.getData().getToken());
                                break;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;

            case R.id.register_cb://八路车协议单选框
                checkString(re_phone, re_password);
                break;

            case R.id.register_sendCode:
                if (re_phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    HttpMethods.getInstance().getSMScode(new Observer<SMScode>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(SMScode smScode) {
                            smScode.getCode();
                            register_sendCode.setEnabled(false);
                            startService(mCodeTimerServiceIntent);//启动服务
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }
                break;
        }

    }

    /**
     * 初步检测注册的手机号以及密码是否合法
     *
     * @return
     */
    private void checkString(String phone, String password) {
        if (phone.length() != 11) {
            flag = false;
        } else {
            if (password.length() < 6 || password.length() > 16) {
                flag = false;
            } else {
                if (register_cb.isChecked()) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }

        changeLoginBtn(flag);
    }

    /**
     * 修改注册按钮的属性
     *
     * @param flag
     */
    private void changeLoginBtn(boolean flag) {
        if (flag) {//可以点击注册
            register_register_bt.setBackgroundColor(Color.parseColor("#2cb154"));
            register_register_bt.setEnabled(true);
        } else {//不允许点击注册
            register_register_bt.setBackgroundColor(Color.parseColor("#bcbcbc"));
            register_register_bt.setEnabled(false);
        }
    }


    //    @Override
    //    public void onFocusChange(View view, boolean b) {
    //        switch (view.getId()) {
    //            case R.id.register_phone:
    //                if (b) {// 此处为得到焦点时的处理内容
    //
    //                } else {// 此处为失去焦点时的处理内容
    //                    re_phone = register_phone.getText().toString();
    //                }
    //                break;
    //            case R.id.register_password:
    //                if (b) {
    //
    //                } else {
    //                    re_password = register_password.getText().toString();
    //                }
    //                break;
    //            case R.id.logon_verification_code_edit:
    //                if (b) {
    //
    //                } else {
    //                    TSMScode = register_register_et.getText().toString();
    //                }
    //                break;
    //        }
    //        if (re_phone.isEmpty() || re_password.isEmpty()) {
    //
    //        } else {
    //            flag = checkString(re_phone, re_password);
    //        }
    //        if (flag) {
    //            register_register_bt.setBackgroundColor(Color.parseColor("#2cb154"));
    //            register_register_bt.setEnabled(true);
    //        } else {
    //            register_register_bt.setEnabled(false);
    //        }
    //    }
}
