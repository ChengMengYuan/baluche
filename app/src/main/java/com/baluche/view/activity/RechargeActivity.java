package com.baluche.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.presenter.PersonMsgPre;
import com.baluche.presenter.RechargePre;
import com.baluche.view.api.IRechargeACT;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RechargeActivity extends BaseActivity implements IRechargeACT{

    private Button recharge_pay_btn;
    private Button pay_dialog_sure_btn;
    private EditText recharge_money_edit;
    private TextView pay_dialog_money_number;
    private TextView getpay_dialog_money_number_text;
    private View inflate;
    private Dialog dialog;
    private RechargePre rechargePre;
    private RelativeLayout pay_dialog_style;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView pay_rv;
    private RecyclerView.LayoutManager mLayoutManager;
    private String editmoney;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        rechargePre = new RechargePre(this);
    }

    @Override
    public void initView() {

        recharge_pay_btn = findViewById(R.id.recharge_pay_btn);
        recharge_pay_btn.setOnClickListener(this);
        recharge_money_edit = findViewById(R.id.recharge_money_edit);
        recharge_money_edit.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable edt)
            {
                String temp = edt.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2)
                {
                    edt.delete(posDot + 3, posDot + 4);
                }
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
        });

    }

    @Override
    public void initData() {

    }


    @Override
    public void widgetClick(View view) {

        switch (view.getId()){
            case R.id.recharge_pay_btn:
                editmoney =recharge_money_edit.getText().toString();
                rechargePre.show_paydialog();
                break;

            case R.id.pay_dialog_style:
                rechargePre.paydialogstyle();
                break;

            case R.id.pay_dialog_sure_btn:
                startActivity(RechargeAccomplishActivity.class);
                break;
        }

    }

    public void show_paydialog(){
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate
                (R.layout.pay_dialog, null);

        pay_dialog_style = inflate.findViewById(R.id.pay_dialog_style);
        pay_dialog_style.setOnClickListener(this);

        pay_dialog_sure_btn = inflate.findViewById(R.id.pay_dialog_sure_btn);
        pay_dialog_sure_btn.setOnClickListener(this);

        pay_dialog_money_number = inflate.findViewById(R.id.pay_dialog_money_number);
        pay_dialog_money_number.setText(editmoney.toString());

        getpay_dialog_money_number_text = inflate.findViewById(R.id.pay_dialog_money_number_text);
        getpay_dialog_money_number_text.setText(editmoney.toString());

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


    public void paydialogstyle(){
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