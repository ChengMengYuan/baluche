package com.baluche.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.http.http.HttpMethods;
import com.baluche.model.entity.Login;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/3/30 0030.
 */

public class LoginActivity extends BaseActivity {

    private EditText login_phone;
    private EditText login_password;
    private Button login_login;

    public static String login_name = "";
    public static String password = "";

    private TextView login_register_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void setActivityPre() {

    }

    @Override
    public void initView() {
        login_login = findViewById(R.id.login_login);
        login_login.setOnClickListener(this);
        login_phone = findViewById(R.id.login_phone);
        login_phone.setOnClickListener(this);
        login_password = findViewById(R.id.login_password);
        login_password.setOnClickListener(this);

        login_register_tv = findViewById(R.id.login_register_tv);
        login_register_tv.setOnClickListener(this);
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
            case R.id.login_login:
                login_name = login_phone.getText().toString();
                password = login_password.getText().toString();

                if (isPhoneNumber(login_name)) {

                    if (IsPassword(password)) {
                        Toast.makeText(getApplicationContext(), "正在登录,请稍后", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "密码格式有误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "手机号有误", Toast.LENGTH_SHORT).show();
                }

                HttpMethods.getInstance().getLogin(new Observer<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Login login) {
                        if (login.getCode() == 200) {
                            Log.d("http+login", "getCode" + login.getCode());
                            Log.d("http+login", "getMessage" + login.getMessage());
                        } else {//如果接口签名校验不通过
                            Log.d("http+login", "getMessage" + login.getMessage());
                            Log.d("http+login", "getCode" + login.getCode());
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
            case R.id.login_register_tv:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /**
     * 验证输入密码条件
     *
     * @param password 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     * 6-16位数
     */
    private boolean IsPassword(String password) {
        if (password.length() < 6 || password.length() > 16) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证输入手机号条件
     *
     * @param phoneNumber
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     * 11位
     */
    private boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11) {
            return false;
        } else {
            return true;
        }

    }
}
