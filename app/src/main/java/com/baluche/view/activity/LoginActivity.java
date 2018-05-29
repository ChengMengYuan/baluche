package com.baluche.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baluche.R;
import com.baluche.presenter.LoginPre;
import com.baluche.base.BaseActivity;
import com.baluche.view.api.ILoginACT;


/**
 * Created by Administrator on 2018/3/30 0030.
 */

public class LoginActivity extends BaseActivity implements ILoginACT {

    private LoginPre loginPre;
    private EditText login_phone;
    private EditText login_password;

    public static String login_name = "";
    public static String password = "";
    private RelativeLayout logon_return_left;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPre = new LoginPre(this);
        login_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                login_name = login_phone.getText().toString();
                loginPre.checkIsOldUser(login_name);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void initView() {
        Button login_login = findViewById(R.id.login_login);
        login_login.setOnClickListener(this);
        login_phone = findViewById(R.id.login_phone);
        login_phone.setOnClickListener(this);
        login_password = findViewById(R.id.login_password);
        login_password.setOnClickListener(this);
        TextView login_register_tv = findViewById(R.id.login_register_tv);
        login_register_tv.setOnClickListener(this);
        TextView login_forget_tv = findViewById(R.id.login_forget_tv);
        login_forget_tv.setOnClickListener(this);
        logon_return_left = findViewById(R.id.logon_return_left);
        logon_return_left.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                login_name = login_phone.getText().toString();
                password = login_password.getText().toString();
                loginPre.toLogin();
                break;
            case R.id.login_register_tv:
                startActivity(RegisterActivity.class);
                break;
            case R.id.logon_return_left:
                startActivity(MainActivity.class);
                break;
            case R.id.login_forget_tv:
                startActivity(ResetPasswordActivity.class);
                break;
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    /**
     * 登录成功
     */
    @Override
    public void loginSucceed() {
        finish();
    }

    MaterialDialog errDialog;//提示的dialog

    /**
     * 登录失败
     */
    @SuppressLint("Assert")
    @Override
    public void loginErr(String msg) {
        assert msg != null;
        assert !msg.equals("");
        errDialog = new MaterialDialog.Builder(this)
                .title("登录失败")
                .content(msg)
                .negativeText("确定")
                .show();
    }

    MaterialDialog oldUserDialog;//提示的dialog

    /**
     * 提示是老用户的提示
     */
    @Override
    public void showIsOldUser() {
        oldUserDialog = new MaterialDialog.Builder(this)
                .title("注册提示")
                .content("欢迎您，您已经我们其他平台注册过！请直接设置密码进行APP登录操作!")
                .positiveText("去设置")
                .onPositive((dialog, which) -> {
                    Log.d("materialDialog", "去设置");
                    startActivity(ResetPasswordActivity.class);
                })
                .negativeText("取消登录")
                .onNegative((dialog, which) -> {
                    Log.d("materialDialog", "取消登录");
                    finish();
                })
                .show();
    }

    /**
     * 提示手机号非法
     */
    @Override
    public void showIsNotPhoneNumber() {

    }

    /**
     * 提示密码非法
     */
    @Override
    public void showIsNotPassWord() {

    }

}
