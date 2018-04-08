package com.baluche.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.view.activity.LoginActivity;
import com.baluche.view.activity.MessageActivity;
import com.baluche.view.activity.PersonMsgActivity;
import com.facebook.drawee.view.SimpleDraweeView;

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
        my_setting = v.findViewById(R.id.my_setting);

        account_head.setOnClickListener(this);
        register_logon.setOnClickListener(this);
        image_xiaoxi.setOnClickListener(this);
        sign.setOnClickListener(this);

        my_wallet.setOnClickListener(this);
        my_account.setOnClickListener(this);
        my_vehicle.setOnClickListener(this);
        my_problem.setOnClickListener(this);
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
                Toast.makeText(context, "my_wallet", Toast.LENGTH_LONG).show();
                break;
            case R.id.my_account:
                Toast.makeText(context, "my_account", Toast.LENGTH_LONG).show();
                break;
            case R.id.my_vehicle:
                Toast.makeText(context, "my_vehicle", Toast.LENGTH_LONG).show();
                break;
            case R.id.my_problem:
                Toast.makeText(context, "my_problem", Toast.LENGTH_LONG).show();
                break;
            case R.id.my_setting:
                Toast.makeText(context, "my_setting", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
