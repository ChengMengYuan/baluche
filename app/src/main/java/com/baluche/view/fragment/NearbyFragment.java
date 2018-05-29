package com.baluche.view.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.baluche.R;
import com.baluche.model.http.entity.Park;
import com.baluche.model.http.http.HttpMethods;
import com.baluche.view.adapter.InfromViewAdapter;
import com.baluche.view.adapter.NearRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.amap.api.maps.AMap.MAP_TYPE_NORMAL;


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
     * 初始化地图控制器对象
     */
    private AMap aMap;
    /**
     * 高德地图定位蓝点
     */
    private MyLocationStyle myLocationStyle;
    /**
     * 高德地图定位控制器
     */
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    /**
     * 高德地图点标记
     */
    private Marker locationMarker;

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
    private NearRecyclerViewAdapter mAdapter;


    private double Latitude = 0d;     //获取纬度
    private double Longitude = 0d;   //获取经度


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
        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        near_recyclerView = v.findViewById(R.id.near_recyclerView);
        /*设置布局管理器*/
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        near_recyclerView.setLayoutManager(mLayoutManager);
        /*设置分割线*/
        near_recyclerView.addItemDecoration(new MyItemDecoration());

//        nearby_bottom_sheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
        nearby_bottom_sheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                // Check Logs to see how bottom sheets behaves
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
//                        CameraPosition cameraPosition = new CameraPosition();
//                        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));//将定位图标移动到当前屏幕中心位置
                        Log.e("Bottom Sheet Behaviour", "折叠的");
                        fab.setVisibility(View.INVISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "正在被拖拽");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "最大化");
                        fab.setVisibility(View.INVISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "隐藏的");
                        fab.setVisibility(View.VISIBLE);
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

        fab.setOnClickListener(v1 -> {
            nearby_bottom_sheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
        //初始化定位
        mLocationClient = new AMapLocationClient(getContext());
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果
        mLocationOption.setOnceLocation(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //设置定位回调监听
        mLocationClient.setLocationListener(aMapLocation -> {
            if (aMapLocation != null) {//当定位成功时
                if (aMapLocation.getErrorCode() == 0) {  //可在其中解析amapLocation获取相应内容。
                    //取出经纬度
                    LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    Latitude = aMapLocation.getLatitude();       //获取纬度
                    Longitude = aMapLocation.getLongitude();     //获取经度
                    MyLocationStyle myLocationStyle = new MyLocationStyle();
                    aMap.setMyLocationStyle(myLocationStyle);
                    aMap.getUiSettings().setMyLocationButtonEnabled(true);
                    aMap.setMapType(MAP_TYPE_NORMAL);
                    //去掉高德地图的log
                    aMap.getUiSettings().setLogoBottomMargin(-50);
                    //添加Marker显示定位位置
                    if (locationMarker == null) {
                        //如果是空的添加新的,icon方法就是设置定位图标，可以自定义
                        locationMarker = aMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                //                                    .snippet("")
                                .draggable(true)
                                .setFlat(true));
                        locationMarker.showInfoWindow();//主动显示indowindow
//                        aMap.addText(new TextOptions().position(latLng).text(aMapLocation.getAddress()));
                        //固定标签在屏幕中央
                        locationMarker.setPositionByPixels(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                    } else {
                        //已经添加过了，修改位置即可
                        locationMarker.setPosition(latLng);
                    }
                    //然后可以移动到定位点,使用animateCamera就有动画效果
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));//参数提示:1.经纬度 2.缩放级别
                    getParkData(Latitude, Longitude);
                }
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError",
                        "location Error, ErrCode:"
                                + aMapLocation.getErrorCode()
                                + ", errInfo:"
                                + aMapLocation.getErrorInfo());
            }
        });
        //启动定位
        mLocationClient.startLocation();
        //初始化定位蓝点样式
        myLocationStyle = new MyLocationStyle();
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点
    }

    /**
     * 获取当前位置周边的停车场
     *
     * @param latitude  纬度
     * @param longitude 经度
     */
    private void getParkData(double latitude, double longitude) {
        ArrayList<Park.DataBean> list = new ArrayList();
        HttpMethods.getInstance().getPark(latitude, longitude, new Observer<Park>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Park park) {
                switch (park.getCode()) {
                    case 200:
                        for (int i = 0; i < park.getData().size(); i++) {
                            list.add(park.getData().get(i));
                        }
                        break;
                    default:
                        Toast.makeText(getContext(), park.getMessage(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                /*设置adapter*/
                mAdapter = new NearRecyclerViewAdapter(list, getContext(),Latitude,longitude);
                near_recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
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