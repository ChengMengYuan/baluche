package com.baluche.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.presenter.RechargePre;
import com.baluche.view.api.IRechargeACT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RechargeActivity extends BaseActivity implements IRechargeACT {

    private Button recharge_pay_btn;
    private Button pay_dialog_sure_btn;
    private TextView pay_dialog_money_number;
    private TextView getpay_dialog_money_number_text;
    private GridView recharge_grid;
    private TextView recharge_cell_number;
    private View inflate;
    private Dialog dialog;
    private int posDot;
    private RechargePre rechargePre;
    private ImageView pay_dialog_wechat_choose;
    private ImageView pay_dialog_alipay_choose;
    private ImageView pay_dialog_close;
    private RelativeLayout recharge_return_left;
    private RecyclerView.LayoutManager mLayoutManager;
    private RelativeLayout pay_ways_choose_weixin;
    private RelativeLayout pay_ways_choose_alipay;
    private int moneynumber;
    private String editmoney = "0.00";
    private int paystyle = 2;

    int[] titles = new int[] { 5, 10, 15, 20, 25, 30};
    private String[] from = {"title"};
    private int[] to = {R.id.recharge_cell_number};

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
        recharge_grid = findViewById(R.id.recharge_grid);
        recharge_return_left = findViewById(R.id.recharge_return_left);
        recharge_return_left.setOnClickListener(this);
        SimpleAdapter pictureAdapter = new SimpleAdapter(this, getList(),
                R.layout.recharge_cell, from, to);
        recharge_grid.setAdapter(pictureAdapter);
        recharge_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i=0;i<parent.getCount();i++) {
                    View item = parent.getChildAt(i);
                    recharge_cell_number = item.findViewById(R.id.recharge_cell_number);
                    recharge_cell_number.setTextColor(Color.parseColor("#2cb154"));
                    item.setBackgroundResource(R.drawable.edit_bg);
                }
                recharge_cell_number = view.findViewById(R.id.recharge_cell_number);
                recharge_cell_number.setTextColor(Color.parseColor("#ffffff"));
                view.setBackgroundResource(R.drawable.edit_bg2);
                editmoney = titles[position]+".00";
            }
        });
    }

    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;



        for (int i = 0; i < titles.length; i++) {
            map = new HashMap<String, Object>();
            moneynumber = titles[i];
            map.put("title", titles[i]+"元");
            list.add(map);
        }
        return list;
    }

    @Override
    public void initData() {

    }


    @Override
    public void widgetClick(View view) {

        switch (view.getId()){
            case R.id.recharge_pay_btn:
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
            case R.id.pay_dialog_close:
                dialog.dismiss();
                break;
            case R.id.recharge_return_left:
                finish();
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

        pay_dialog_close = inflate.findViewById(R.id.pay_dialog_close);
        pay_dialog_close.setOnClickListener(this);

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
                new AlipayTask(this, moneynumber).execute();
                Intent intent = new Intent(RechargeActivity.this, RechargeAccomplishActivity.class);
                intent.putExtra("Paynumber", editmoney);
                intent.putExtra("Paystyle", "支付宝");
                startActivity(intent);
                break;

            case 2:
                Intent intent2 = new Intent(RechargeActivity.this, RechargeAccomplishActivity.class);
                intent2.putExtra("Paynumber", editmoney);
                intent2.putExtra("Paystyle", "微信");
                startActivity(intent2);
                break;

            default:
                break;
        }

    }

}