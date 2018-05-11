package com.baluche.view.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.base.BaseActivity;
import com.baluche.presenter.AddVehiclePre;
import com.baluche.view.api.IAddVehicleACT;

public class AddVehicleActivity extends BaseActivity implements IAddVehicleACT {
    // FIXME: 2018/4/22 自定义键盘长按删除键报错
    public static final String INPUT_LICENSE_COMPLETE = "me.kevingo.licensekeyboard.input.comp";
    public static final String INPUT_LICENSE_KEY = "LICENSE";

    private EditText inputbox1, inputbox2, inputbox3, inputbox4, inputbox5, inputbox6, inputbox7, inputbox8;
    private LicenseKeyboardUtil keyboardUtil;

    private AddVehiclePre addVehiclePre;
    private CheckBox add_vehicle_cb;//协议单选框
    private EditText et_car_license_inputbox8;
    private Button add_next_step;
    private LinearLayout keyboard_close;
    private TextView keyboard_close_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        addVehiclePre = new AddVehiclePre(this);
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        inputbox1 = findViewById(R.id.et_car_license_inputbox1);
        inputbox1.setOnClickListener(this);
        inputbox2 = findViewById(R.id.et_car_license_inputbox2);
        inputbox2.setOnClickListener(this);
        inputbox3 = findViewById(R.id.et_car_license_inputbox3);
        inputbox3.setOnClickListener(this);
        inputbox4 = findViewById(R.id.et_car_license_inputbox4);
        inputbox4.setOnClickListener(this);
        inputbox5 = findViewById(R.id.et_car_license_inputbox5);
        inputbox5.setOnClickListener(this);
        inputbox6 = findViewById(R.id.et_car_license_inputbox6);
        inputbox6.setOnClickListener(this);
        inputbox7 = findViewById(R.id.et_car_license_inputbox7);
        inputbox7.setOnClickListener(this);
        inputbox8 = findViewById(R.id.et_car_license_inputbox8);
        inputbox8.setOnClickListener(this);
        keyboard_close = findViewById(R.id.keyboard);
        add_next_step = findViewById(R.id.add_next_step);
        add_next_step.setOnClickListener(this);
        keyboard_close_btn = findViewById(R.id.keyboard_close_btn);
        keyboard_close_btn.setOnClickListener(this);

        //输入车牌完成后的intent过滤器
        IntentFilter finishFilter = new IntentFilter(INPUT_LICENSE_COMPLETE);

        final BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String license = intent.getStringExtra(INPUT_LICENSE_KEY);
                if (license != null && license.length() > 0) {
                    if (keyboardUtil != null) {
                        keyboardUtil.hideKeyboard();
                    }

                    AlertDialog alertDialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddVehicleActivity.this);
                    builder.setMessage("车牌号为:" + license);
                    alertDialog = builder.create();
                    alertDialog.setCancelable(true);
                    alertDialog.show();
                }
            }
        };
        this.registerReceiver(receiver, finishFilter);


        et_car_license_inputbox8 = findViewById(R.id.et_car_license_inputbox8);

        add_vehicle_cb = findViewById(R.id.add_vehicle_cb);
        add_vehicle_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                clear();
                if (add_vehicle_cb.isChecked()) {
                    addVehiclePre.new_vehicle();
                } else {
                    addVehiclePre.vehicle();
                }
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.add_next_step:
                break;
            case R.id.keyboard_close_btn:
                addVehiclePre.keyboard_close_btn();
                break;
            case R.id.et_car_license_inputbox1:
                addVehiclePre.expand_keyboard();
                break;
            case R.id.et_car_license_inputbox2:
                addVehiclePre.expand_keyboard();
                break;
            case R.id.et_car_license_inputbox3:
                addVehiclePre.expand_keyboard();
                break;
            case R.id.et_car_license_inputbox4:
                addVehiclePre.expand_keyboard();
                break;
            case R.id.et_car_license_inputbox5:
                addVehiclePre.expand_keyboard();
                break;
            case R.id.et_car_license_inputbox6:
                addVehiclePre.expand_keyboard();
                break;
            case R.id.et_car_license_inputbox7:
                addVehiclePre.expand_keyboard();
                break;
            case R.id.et_car_license_inputbox8:
                addVehiclePre.expand_keyboard();
                break;
        }
    }

    private void clear() {
        inputbox1.setText("");
        inputbox2.setText("");
        inputbox3.setText("");
        inputbox4.setText("");
        inputbox5.setText("");
        inputbox6.setText("");
        inputbox7.setText("");
        inputbox8.setText("");
    }

    @Override
    public void vehicle() {
        et_car_license_inputbox8.setVisibility(View.GONE);
        keyboardUtil = new LicenseKeyboardUtil(this, new EditText[]{inputbox1, inputbox2, inputbox3,
                inputbox4, inputbox5, inputbox6, inputbox7, inputbox8}, false);
        keyboard_close.setVisibility(View.VISIBLE);
        keyboardUtil.showKeyboard();
    }

    @Override
    public void new_vehicle() {
        et_car_license_inputbox8.setVisibility(View.VISIBLE);
        keyboardUtil = new LicenseKeyboardUtil(this, new EditText[]{inputbox1, inputbox2, inputbox3,
                inputbox4, inputbox5, inputbox6, inputbox7, inputbox8}, true);
        keyboard_close.setVisibility(View.VISIBLE);
        keyboardUtil.showKeyboard();
    }

    @Override
    public void keyboard_close_btn() {
        keyboard_close.setVisibility(View.GONE);
    }

    @Override
    public void expand_keyboard() {
        clear();
        keyboardUtil = new LicenseKeyboardUtil(this, new EditText[]{inputbox1, inputbox2, inputbox3,
                inputbox4, inputbox5, inputbox6, inputbox7, inputbox8}, false);
        keyboard_close.setVisibility(View.VISIBLE);
        keyboardUtil.showKeyboard();
    }
}
