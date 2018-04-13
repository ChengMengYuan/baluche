package com.baluche.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baluche.R;
import com.baluche.view.adapter.InfromViewAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class InformFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView inform_listView;
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
        View v = inflater.inflate(R.layout.fragment_inform, container, false);
        initData();
        initView(savedInstanceState, v);
        return v;
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            data.add("海南停車場" + i);
        }
    }

    private void initView(Bundle savedInstanceState, View v) {
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new InfromViewAdapter(data);
        inform_listView = v.findViewById(R.id.inform_listView);
        // 设置布局管理器
        inform_listView.setLayoutManager(mLayoutManager);
        // 设置adapter
        inform_listView.setAdapter(mAdapter);
    }
}
