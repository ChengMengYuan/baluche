package com.baluche.view.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baluche.R;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class RegisterActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener {
    private EditText register_phone; //手机号输入框
    private EditText register_password;//密码输入框
    private EditText register_register_et;//验证码输入框

    private Button register_register_bt; //注册button

    public static String phone = "";
    public static String password = "";

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_register_bt:

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
                return true;
            }
        }
    }


    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.register_phone:
                if (b) {// 此处为得到焦点时的处理内容

                } else {// 此处为失去焦点时的处理内容
                    phone = register_phone.getText().toString();
                }
                break;
            case R.id.register_password:
                if (b) {

                } else {
                    password = register_password.getText().toString();
                }
                break;
            case R.id.logon_verification_code_edit:

                break;
        }
        if (phone.isEmpty() || password.isEmpty()) {

        } else {
            flag = checkString(phone, password);
        }
        if (flag) {
            register_register_bt.setBackgroundColor(Color.parseColor("#2cb154"));
            register_register_bt.setEnabled(true);
        } else {
            register_register_bt.setEnabled(false);
        }
    }
}
