package com.baluche.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baluche.R;
import com.baluche.view.adapter.PayingbillViewAdapter;

import java.util.ArrayList;

public class AvailableDiscountFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView available_discount_listView;
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
        View v = inflater.inflate(R.layout.fragment_available_discount, container, false);
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
        available_discount_listView = v.findViewById(R.id.available_discount_listView);
        // 设置布局管理器
        available_discount_listView.setLayoutManager(mLayoutManager);
        // 设置adapter
        available_discount_listView.setAdapter(mAdapter);


    }
}
