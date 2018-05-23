package com.baluche.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.presenter.RechargePre;
import com.baluche.view.api.IRechargeACT;

import java.util.ArrayList;

public class RechargeActivity extends BaseActivity implements IRechargeACT {

    private Button recharge_pay_btn;
    private Button pay_dialog_sure_btn;
    private EditText recharge_money_edit;
    private TextView pay_dialog_money_number;
    private TextView getpay_dialog_money_number_text;
    private View inflate;
    private Dialog dialog;
    private int posDot;
    private RechargePre rechargePre;
    private ImageView pay_dialog_wechat_choose;
    private ImageView pay_dialog_alipay_choose;
    private RecyclerView.LayoutManager mLayoutManager;
    private RelativeLayout pay_ways_choose_weixin;
    private RelativeLayout pay_ways_choose_alipay;
    private String editmoney;
    private int paystyle = 2;
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
        recharge_money_edit.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable edt) {
                String temp = edt.toString();
                posDot = temp.indexOf(".");
//                Log.d("RechargeActivity", "temp123456:*****************************" +posDot);
//                Log.d("RechargeActivity", "temp1234567890:*****************************" +posDot);
//                if(temp.charAt(0)== '0'){
//                    edt.delete(posDot, posDot+1);
//                }
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    edt.delete(posDot + 3, posDot + 4);
                }

            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });
    }

    @Override
    public void initData() {

    }


    @Override
    public void widgetClick(View view) {

        switch (view.getId()) {
            case R.id.recharge_pay_btn:
                if (posDot <= 0) {
                    editmoney = recharge_money_edit.getText().toString() + ".00";//获得充值金额
                } else {
                    editmoney = recharge_money_edit.getText().toString();//获得充值金额
                }

                rechargePre.show_paydialog();
                break;

            case R.id.pay_dialog_sure_btn:
                rechargePre.pay_dialog_sure_btn();
                break;

            case R.id.pay_ways_choose_weixin:
                pay_dialog_wechat_choose.setVisibility(View.VISIBLE);
                pay_dialog_alipay_choose.setVisibility(View.GONE);
                paystyle = 2;
                break;

            case R.id.pay_ways_choose_alipay:
                pay_dialog_wechat_choose.setVisibility(View.GONE);
                pay_dialog_alipay_choose.setVisibility(View.VISIBLE);
                paystyle = 1;
                break;
        }

    }

    public void show_paydialog() {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate
                (R.layout.pay_dialog, null);

        pay_dialog_sure_btn = inflate.findViewById(R.id.pay_dialog_sure_btn);
        pay_dialog_sure_btn.setOnClickListener(this);

        pay_dialog_money_number = inflate.findViewById(R.id.pay_dialog_money_number);
        pay_dialog_money_number.setText(editmoney.toString());

        getpay_dialog_money_number_text = inflate.findViewById(R.id.pay_dialog_money_number_text);
        getpay_dialog_money_number_text.setText(editmoney.toString());

        pay_dialog_wechat_choose = inflate.findViewById(R.id.pay_dialog_wechat_choose);
        pay_dialog_alipay_choose = inflate.findViewById(R.id.pay_dialog_alipay_choose);

        pay_ways_choose_weixin = inflate.findViewById(R.id.pay_ways_choose_weixin);
        pay_ways_choose_weixin.setOnClickListener(this);

        pay_ways_choose_alipay = inflate.findViewById(R.id.pay_ways_choose_alipay);
        pay_ways_choose_alipay.setOnClickListener(this);

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

    @Override
    public void pay_dialog_sure_btn() {

        switch (paystyle) {
            case 1:
                new AlipayTask(this, 5).execute();
                break;

            case 2:
                Toast.makeText(this, "微信支付还在开发中", Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
//        Intent intent = new Intent(RechargeActivity.this, AlipayActivity.class);

//        /* 通过Bundle对象存储需要传递的数据 */
//        Bundle bundle = new Bundle();
//        /*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/
//        bundle.putString("Paynumber", editmoney);
//        bundle.putInt("Paystyle", 1);
//        /*把bundle对象assign给Intent*/
//        intent.putExtras(bundle);
//        intent.putExtra("Paynumber", editmoney);
//        intent.putExtra("Paystyle", "支付宝");
//        startActivity(intent);
    }

}