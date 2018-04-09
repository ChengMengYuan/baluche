package com.baluche.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baluche.R;
import com.baluche.view.adapter.InfromViewAdapter;
import com.baluche.view.adapter.PayingbillViewAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class PayingbillFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView parkpaying_listView;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> data = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_payingbill, container, false);
        initData();
        initView(savedInstanceState, v);
        return v;
    }

    private void initData() {
        for (int i = 0; i <10 ; i++) {
            data.add("海南停車場"+i);
        }
    }

    private void initView(Bundle savedInstanceState, View v) {
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new PayingbillViewAdapter(data);
        parkpaying_listView = v.findViewById(R.id.parkpaying_listView);
        // 设置布局管理器
        parkpaying_listView.setLayoutManager(mLayoutManager);
        // 设置adapter
        parkpaying_listView.setAdapter(mAdapter);


    }

}
