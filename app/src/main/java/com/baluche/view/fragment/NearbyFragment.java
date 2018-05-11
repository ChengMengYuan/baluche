package com.baluche.view.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.MapView;
import com.baluche.R;
import com.baluche.view.adapter.InfromViewAdapter;

import java.util.ArrayList;


/**
 * Created by cmy on 2018/3/20.
 */

public class NearbyFragment extends Fragment {
    //    private BottomSheetBehavior nearby_bottom_sheet;
    private BottomSheetBehavior nearby_bottom_sheet;
    /**
     * 高德地图控件
     */
    private MapView mMapView = null;
    /**
     * 列表控件
     */
    private RecyclerView near_recyclerView;
    /**
     * 列表布局管理器
     */
    private RecyclerView.LayoutManager mLayoutManager;
    /**
     * 列表适配器
     */
    private RecyclerView.Adapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nearby, container, false);
        initView(savedInstanceState, v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }

    private void initView(Bundle savedInstanceState, View v) {
        initMap(savedInstanceState, v);
        nearby_bottom_sheet = BottomSheetBehavior.from(v.findViewById(R.id.nearby_bottom_sheet));

        near_recyclerView = v.findViewById(R.id.near_recyclerView);
        /*设置布局管理器*/
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        near_recyclerView.setLayoutManager(mLayoutManager);
        /*设置分割线*/
        near_recyclerView.addItemDecoration(new MyItemDecoration());
        /*设置adapter*/
        ArrayList<String> data = new ArrayList<>();//停车场信息集合
        for (int i = 0; i < 50; i++) {
            data.add("这是测试数据" + i);
        }
        mAdapter = new InfromViewAdapter(data);
        near_recyclerView.setAdapter(mAdapter);

//        nearby_bottom_sheet.setState(BottomSheetBehavior.STATE_EXPANDED);
        nearby_bottom_sheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                } else {
                }

                // Check Logs to see how bottom sheets behaves
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Bottom Sheet Behaviour", "折叠的");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "正在被拖拽");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "最大化");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "隐藏的");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Bottom Sheet Behaviour", "拖完了");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    /**
     * @param savedInstanceState Bundle
     * @param v                  View
     */
    private void initMap(Bundle savedInstanceState, View v) {
        //初始化高德地图控件
        mMapView = v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            super.onSaveInstanceState(outState);
            //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
            mMapView.onSaveInstanceState(outState);
        }
    }


    /**
     * 分割线
     */
    class MyItemDecoration extends RecyclerView.ItemDecoration {
        /**
         * @param outRect 边界
         * @param view    recyclerView ItemView
         * @param parent  recyclerView
         * @param state   recycler 内部数据管理
         */
        @Override
        public void getItemOffsets(Rect outRect,
                                   View view,
                                   RecyclerView parent,
                                   RecyclerView.State state) {
            //设定底部边距为2px
            outRect.set(0, 0, 0, 1);
        }
    }
}