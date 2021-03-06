package com.baluche.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.presenter.SettingPre;
import com.baluche.view.api.ISettingACT;

import static com.baluche.app.Constant.APP_VERSION;

public class SettingActivity extends BaseActivity implements ISettingACT{

    private View inflate;
    private RelativeLayout setting_quit;
    private Dialog dialog;
    private TextView quit_true;
    private TextView quit_false;
    private TextView APP_VERSION_tv;
    private SettingPre settingPre;
    private RelativeLayout setting_return_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        settingPre = new SettingPre(this);
    }


    @Override
    public void initView() {
        setting_quit = findViewById(R.id.setting_quit);
        setting_quit.setOnClickListener(this);
        APP_VERSION_tv = findViewById(R.id.APP_VERSION);
        APP_VERSION_tv.setText(APP_VERSION);
        setting_return_left = findViewById(R.id.setting_return_left);
        setting_return_left.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.setting_quit:
                settingPre.showquitdialog();
                break;
            case R.id.quit_true:
                settingPre.quit_true();
                break;
            case R.id.quit_false:
                settingPre.quit_false();
                break;
            case R.id.setting_return_left:
                finish();
                break;
        }
    }

    public void showquitdialog(){
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate
                (R.layout.quit_layout, null);
        //初始化控件
        quit_true = inflate.findViewById(R.id.quit_true);
        quit_false = inflate.findViewById(R.id.quit_false);
        quit_true.setOnClickListener(this);
        quit_false.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 0;//设置Dialog距离底部的距离
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    public void quit_true(){
        Toast.makeText(this, "quit_true", Toast.LENGTH_LONG).show();
    }

    public void quit_false(){
        Toast.makeText(this, "quit_false", Toast.LENGTH_LONG).show();
    }
}
