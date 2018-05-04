package com.baluche.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.baluche.R;
import com.baluche.base.BaseActivity;

import java.util.ArrayList;

public class RechargeActivity extends BaseActivity {

    private Button recharge_pay_btn;
    private Button pay_dialog_sure_btn;
    private View inflate;
    private Dialog dialog;
    private RelativeLayout pay_dialog_style;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView pay_rv;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
    }


    @Override
    public void initView() {

        recharge_pay_btn = findViewById(R.id.recharge_pay_btn);
        recharge_pay_btn.setOnClickListener(this);


    }

    @Override
    public void initData() {

    }


    @Override
    public void widgetClick(View view) {

        switch (view.getId()){
            case R.id.recharge_pay_btn:
                show_headchange();
                break;

            case R.id.pay_dialog_style:
                paydialogstyle();
                break;

            case R.id.pay_dialog_sure_btn:
                startActivity(RechargeAccomplishActivity.class);
                break;
        }

    }

    public void show_headchange() {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate
                (R.layout.pay_dialog, null);

        pay_dialog_style = inflate.findViewById(R.id.pay_dialog_style);
        pay_dialog_style.setOnClickListener(this);

        pay_dialog_sure_btn = inflate.findViewById(R.id.pay_dialog_sure_btn);
        pay_dialog_sure_btn.setOnClickListener(this);

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


    public void paydialogstyle() {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate
                (R.layout.pay_ways_dialog, null);

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
}
