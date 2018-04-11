package com.baluche.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baluche.R;
import com.baluche.http.http.HttpMethods;
import com.baluche.model.entity.Login;
import com.baluche.model.entity.MyJoke;
import com.baluche.util.SnackbarUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/3/30 0030.
 */

public class LoginActivity extends BaseActivity {

    private EditText login_phone;
    private EditText login_password;
    private Button login_login;

    private ImageView logon_return_left;

    public static String login_name = "";
    public static String password = "";

    private TextView login_register_tv;
    private TextView login_forget_tv;

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
        login_forget_tv = findViewById(R.id.login_forget_tv);
        login_forget_tv.setOnClickListener(this);
        logon_return_left = findViewById(R.id.logon_return_left);
        logon_return_left.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {
        login_phone.addTextChangedListener(new TextWatcher() {//监听输入框的变化
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, charSequence + "");
                if (isPhoneNumber(charSequence + "")) {//检测该手机号是否为平台老用户
                    login_name = login_phone.getText().toString();
                    HttpMethods.getInstance().cheakUsertype(new Observer<MyJoke>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(MyJoke myJoke) {
                            Log.d(TAG, myJoke.getMessage() + "");
                            final MaterialDialog materialDialog;//提示的dialog
                            switch (myJoke.getCode()) {
                                case 10021://手机号输入有误
                                    SnackbarUtil.showLongSnackbar(login_phone,
                                            "手机号输入有误",
                                            getResources().getColor(R.color.colorGreen),
                                            Color.WHITE);
                                    break;
                                case 10019://不是老用户
                                    break;
                                case 200://是老用户
                                    materialDialog = new MaterialDialog.Builder(getApplicationContext())
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
                                    break;
                                default:

                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                login_name = login_phone.getText().toString();
                password = login_password.getText().toString();
                final MaterialDialog materialDialog;//等待的dialog
                materialDialog = new MaterialDialog.Builder(this)
                        .title("请稍候")
                        .content("正在登录,请稍后")
                        .progress(true, 0)
                        .show();
                if (isPhoneNumber(login_name)) {
                    if (IsPassword(password)) {

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
                        Log.d("http+login", "getMessage" + login.getMessage());
                        Log.d("http+login", "getCode" + login.getCode());
                        switch (login.getCode()) {
                            // TODO: 2018/4/11 0011 开一个通知，告知登录状态 
                            case 200:
                                login.getData().getToken();
                                finish();
                                break;
                            case 10005:
                                materialDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "手机号未注册，请前往注册帐号", Toast.LENGTH_SHORT).show();
                                break;
                            case 10004:
                                materialDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "帐号密码错误", Toast.LENGTH_SHORT).show();
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
     * @param phoneNumber 手机号码
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
