package com.baluche.view.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.baluche.http.http.HttpMethods;
import com.baluche.model.entity.Register;
import com.baluche.model.entity.SMScode;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class RegisterActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener {
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

    boolean flag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initview();
    }

    private void initview() {
        register_phone = findViewById(R.id.register_phone);
        register_phone.setOnFocusChangeListener(this);
        register_password = findViewById(R.id.register_password);
        register_password.setOnFocusChangeListener(this);
        register_register_et = findViewById(R.id.logon_verification_code_edit);
        register_register_et.setOnFocusChangeListener(this);

        register_register_bt = findViewById(R.id.register_register_bt);
        register_register_bt.setOnClickListener(this);
        register_register_bt.setEnabled(false);

        register_cb = findViewById(R.id.register_cb);
        register_cb.setOnClickListener(this);

        register_sendCode = findViewById(R.id.register_sendCode);
        register_sendCode.setOnClickListener(this);

        register_return_left = findViewById(R.id.register_return_left);
        register_return_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // FIXME: 2018/3/31 0031 重新获取焦点
            case R.id.register_return_left:
                RegisterActivity.this.finish();
                break;
            case R.id.register_phone:

                break;
            case R.id.register_password:

                break;
            case R.id.logon_verification_code_edit:

                break;

            case R.id.register_register_bt://验证码接口,调用成功返回登录token
                HttpMethods.getInstance().getRegister(new Observer<Register>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Register register) {
                        Log.d("http+register", "" + register.getCode());
                        Log.d("http+register", "" + register.getMessage());
                        MApplication.Token = register.getData().getToken();
                        Log.d("http+register", "" + register.getData().getToken());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;

            case R.id.register_cb:
                register_register_et.clearFocus();
                register_register_et.setFocusable(false);
                register_phone.clearFocus();
                register_phone.setFocusable(false);
                register_password.clearFocus();
                register_password.setFocusable(false);

                if (register_cb.isChecked() && checkString(re_phone, re_password)) {
                    register_register_bt.setBackgroundColor(Color.parseColor("#2cb154"));
                    register_register_bt.setEnabled(true);
                } else {
                    register_register_bt.setBackgroundColor(Color.parseColor("#bcbcbc"));
                    register_register_bt.setEnabled(false);
                }

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
                            Log.d("http+SMScode", "" + smScode.getMessage());
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
    private boolean checkString(String phone, String password) {
        if (phone.length() != 11) {
            return false;
        } else {
            if (password.length() < 6 || password.length() > 16) {
                return false;
            } else {
                if (register_cb.isChecked()) {
                    return true;
                }
                return false;
            }
        }
    }


    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.register_phone:
                if (b) {// 此处为得到焦点时的处理内容

                } else {// 此处为失去焦点时的处理内容
                    re_phone = register_phone.getText().toString();
                }
                break;
            case R.id.register_password:
                if (b) {

                } else {
                    re_password = register_password.getText().toString();
                }
                break;
            case R.id.logon_verification_code_edit:
                if (b) {

                } else {
                    TSMScode = register_register_et.getText().toString();
                }
                break;
        }
        if (re_phone.isEmpty() || re_password.isEmpty()) {

        } else {
            flag = checkString(re_phone, re_password);
        }
        if (flag) {
            register_register_bt.setBackgroundColor(Color.parseColor("#2cb154"));
            register_register_bt.setEnabled(true);
        } else {
            register_register_bt.setEnabled(false);
        }
    }
}
