package com.baluche.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.view.activity.AddVehicleActivity;
import com.baluche.view.activity.DiscountHelpActivity;
import com.baluche.view.activity.LoginActivity;
import com.baluche.view.activity.MessageActivity;
import com.baluche.view.activity.ParkpayingActivity;
import com.baluche.view.activity.PersonMsgActivity;
import com.baluche.view.activity.SettingActivity;
import com.baluche.view.activity.VehicleManageActivity;
import com.baluche.view.activity.WalletActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.jetbrains.annotations.Nullable;

/**
 * Created by cmy on 2018/3/20.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private Context context;/*Title控件 */

    //标题功能
    private SimpleDraweeView account_head;//头像框
    private TextView register_logon;//登录/注册
    private ImageView image_xiaoxi;//提示消息按钮
    private RelativeLayout sign;//每日签到

    //内容功能（整行）
    private RelativeLayout my_wallet;//我的钱包
    private RelativeLayout my_account;//账单
    private RelativeLayout my_vehicle;//车辆管理
    private RelativeLayout my_problem;//问题反馈
    private RelativeLayout my_usehelp;//使用帮助
    private RelativeLayout my_setting;//设置

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
        context = getContext();
        initView(v);
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView(View v) {
        account_head = v.findViewById(R.id.account_head);
        register_logon = v.findViewById(R.id.register_logon);
        image_xiaoxi = v.findViewById(R.id.image_xiaoxi);
        sign = v.findViewById(R.id.sign);

        my_wallet = v.findViewById(R.id.my_wallet);
        my_account = v.findViewById(R.id.my_account);
        my_vehicle = v.findViewById(R.id.my_vehicle);
        my_problem = v.findViewById(R.id.my_problem);
        my_usehelp = v.findViewById(R.id.my_use_help);
        my_setting = v.findViewById(R.id.my_setting);

        account_head.setOnClickListener(this);
        register_logon.setOnClickListener(this);
        image_xiaoxi.setOnClickListener(this);
        sign.setOnClickListener(this);

        my_wallet.setOnClickListener(this);
        my_account.setOnClickListener(this);
        my_vehicle.setOnClickListener(this);
        my_problem.setOnClickListener(this);
        my_usehelp.setOnClickListener(this);
        my_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_head:
                Intent intent = new Intent(getActivity(), PersonMsgActivity.class);
                startActivity(intent);
                break;
            case R.id.register_logon:
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.image_xiaoxi:
                Intent intent2 = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent2);
                break;
            case R.id.sign:
                Toast.makeText(context, "sign", Toast.LENGTH_LONG).show();
                break;
            case R.id.my_wallet:
                Intent intent7 = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent7);
                break;
            case R.id.my_account:
                Intent intent3 = new Intent(getActivity(), ParkpayingActivity.class);
                startActivity(intent3);
                break;
            case R.id.my_vehicle:
                Intent intent4 = new Intent(getActivity(), VehicleManageActivity.class);
                startActivity(intent4);
                break;
            case R.id.my_problem:
                Intent intent5 = new Intent(getActivity(), AddVehicleActivity.class);
                startActivity(intent5);
                break;
            case R.id.my_use_help:
                Intent intent6 = new Intent(getActivity(), DiscountHelpActivity.class);
                startActivity(intent6);
                break;
            case R.id.my_setting:
                Toast.makeText(context, "my_setting", Toast.LENGTH_LONG).show();
                Intent intent8 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent8);
                break;
            default:
                break;
        }
    }
}
