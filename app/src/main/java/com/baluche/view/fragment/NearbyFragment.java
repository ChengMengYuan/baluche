package com.baluche.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.TextOptions;
import com.baluche.R;
import com.baluche.model.http.entity.Park;
import com.baluche.model.http.http.HttpMethods;
import com.baluche.model.database.entity.Suggest;
import com.baluche.model.database.greendao.GreenDaoManager;
import com.baluche.model.database.greendao.SuggestDao;
import com.baluche.view.adapter.NearRecyclerViewAdapter;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.amap.api.maps.AMap.MAP_TYPE_NORMAL;

/**
 * Created by cmy on 2018/3/20.
 */

public class NearbyFragment extends Fragment {
    private RecyclerView near_recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MapView mMapView = null;//高德地图控件
    private AMap aMap;//初始化地图控制器对象
    private MyLocationStyle myLocationStyle;//高德地图定位蓝点
    private AMapLocationClient mLocationClient;//高德地图定位
    private AMapLocationClientOption mLocationOption;
    private Marker locationMarker;

    private SuggestDao suggestDao;

    private ArrayList<Park> data = new ArrayList<>();//停车场信息集合

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        suggestDao = GreenDaoManager.getInstance(getContext()).getNewSession().getSuggestDao();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_nearby, container, false);
        initData();
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


    private void initData() {
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new NearRecyclerViewAdapter(data, getContext());
    }


    /**
     * 获取数据
     *
     * @return
     */
    private ArrayList<Park> getData() {

        HttpMethods.getInstance().getPark(new Observer<Park>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Park park) {
                Logger.t("park").d(park.getMessage());
                Logger.t("park").d(park.getCode());
                if (park.getCode() == 200) {
                    if (park.getData().size() > 0) {
                        for (int i = 0; i < park.getData().size(); i++) {
                            data.add(park);
                            /*将获取到的停车场信息缓存到数据库*/
                            Suggest suggest = new Suggest();
                            suggest.setNet_park_id(park.getData().get(i).getNet_park_id());
                            suggest.setAddress(park.getData().get(i).getAddress());
                            suggest.setTitle(park.getData().get(i).getTitle());
                            suggestDao.insertOrReplace(suggest);
                        }
                    } else {

                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
        return data;
    }


    public static double Latitude = 0d;     //获取纬度
    public static double Longitude = 0d;   //获取经度

    /**
     * 初始化控件
     *
     * @param v
     */
    @SuppressLint("CheckResult")
    private void initView(Bundle savedInstanceState, View v) {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions
                .request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {//同意权限
                        Log.d("rxPermissions", "tongyi");
                        initMap(savedInstanceState, v);
                    } else {//拒绝权限
                        Log.d("rxPermissions", "jujue");
                        MaterialDialog materialDialog = new MaterialDialog.Builder(getContext())
                                .title("权限提示")
                                .content("该权限是软件必须获取的权限，如果拒绝可能导致核心功能不可使用，请在设置中赋予该权限。")
                                .positiveText("去设置")
                                .onPositive((dialog, which) -> {
                                    Log.d("materialDialog", "去设置");
                                    Intent localIntent = new Intent();
                                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    if (Build.VERSION.SDK_INT >= 9) {
                                        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                        localIntent.setData(Uri.fromParts("package", getContext().getPackageName(), null));
                                    } else if (Build.VERSION.SDK_INT <= 8) {
                                        localIntent.setAction(Intent.ACTION_VIEW);
                                        localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                                        localIntent.putExtra("com.android.settings.ApplicationPkgName", getContext().getPackageName());
                                    }
                                    getContext().startActivity(localIntent);
                                })
                                .negativeText("取消")
                                .onNegative((dialog, which) -> Log.d("materialDialog", "取消"))
                                .show();
                    }
                });

    }

    private void initMap(Bundle savedInstanceState, View v) {

        near_recyclerView = v.findViewById(R.id.near_recyclerView);
        // 设置布局管理器
        near_recyclerView.setLayoutManager(mLayoutManager);
        near_recyclerView.addItemDecoration(new MyItemDecoration());
        // 设置adapter
        near_recyclerView.setAdapter(mAdapter);

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
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());//取出经纬度
                        Latitude = aMapLocation.getLatitude();       //获取纬度
                        Longitude = aMapLocation.getLongitude();     //获取经度
                        MyLocationStyle myLocationStyle = new MyLocationStyle();
                        aMap.setMyLocationStyle(myLocationStyle);
                        aMap.getUiSettings().setMyLocationButtonEnabled(true);
                        aMap.setMapType(MAP_TYPE_NORMAL);
                        //去掉高德地图的log
                        aMap.getUiSettings().setLogoBottomMargin(-50);
                        for (int i = 0; i < data.size(); i++) {
                            MarkerOptions markerOption = new MarkerOptions();
                            markerOption.position(new LatLng(Double.parseDouble(data.get(i).getData().get(i).getLat()),
                                    Double.parseDouble(data.get(i).getData().get(i).getLng())));
                            markerOption.draggable(false);//设置Marker可拖动
                            markerOption.title(String.valueOf(i));
                            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(getResources(), R.drawable.dadaobaoxue)));
                            aMap.addMarker(markerOption);
                        }
                        //添加Marker显示定位位置
                        if (locationMarker == null) {
                            //如果是空的添加新的,icon方法就是设置定位图标，可以自定义
                            locationMarker = aMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    //                                    .snippet("")
                                    .draggable(true)
                                    .setFlat(true));
                            locationMarker.showInfoWindow();//主动显示indowindow
                            aMap.addText(new TextOptions().position(latLng).text(aMapLocation.getAddress()));
                            //固定标签在屏幕中央
                            locationMarker.setPositionByPixels(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                        } else {
                            //已经添加过了，修改位置即可
                            locationMarker.setPosition(latLng);
                        }
                        //然后可以移动到定位点,使用animateCamera就有动画效果
                        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));//参数提示:1.经纬度 2.缩放级别
                        while (Latitude == 0d) {
                            Log.d("while...", "onLocationChanged: ");
                        }
                        getData();
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
                    }
                }
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            super.onSaveInstanceState(outState);
            //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
            mMapView.onSaveInstanceState(outState);
        }
    }

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