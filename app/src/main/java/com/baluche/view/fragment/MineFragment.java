package com.baluche.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;

/**
 * Created by cmy on 2018/3/20.
 */

public class MineFragment extends Fragment {

    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_title, container, false);
//        initView(v);
        return v;
    }

//    private void initView(View v) {
//        tv = v.findViewById(R.id.tv1);
//    }


}
