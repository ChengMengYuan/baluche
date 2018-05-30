package com.baluche.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baluche.R;
import com.baluche.model.http.entity.Park;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/27 0027.
 */


public class NearRecyclerViewAdapter extends RecyclerView.Adapter<NearRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Park.DataBean> mData;
    private Context context;
    private double Latitude;
    private double Longitude;
    private MaterialDialog materialDialog;//等待的dialog


    public NearRecyclerViewAdapter(ArrayList<Park.DataBean> data, Context context, double Latitude, double Longitude) {
        Log.d("NearRecyclerViewAdapter", "======");
        for (int i = 0; i < data.size(); i++) {
            Log.d("NearRecyclerViewAdapter", "" + data.get(i));
        }
        this.mData = data;
        this.context = context;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

//    public void updateData(ArrayList<Park.DataBean> data) {
//        this.mData = data;
//        notifyDataSetChanged();
//    }

    @Override
    public NearRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.near_rv_item, parent, false);
        // 实例化viewHolder
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NearRecyclerViewAdapter.ViewHolder holder, int position) {
        Park.DataBean park = mData.get(position);
        /*设置停车场名称*/
        holder.item_Location_name_tv.setText(park.getTitle());
        /*设置到停车场的距离*/
        holder.item_surplus_tv.setText(park.getNull_number());
        int distance = park.getDistance();
        Log.d("distance", ":" + distance);
        String dis;
        if (1000 <= distance) {
            dis = distance / 1000 + "公里";
        } else {
            dis = distance + "米";
        }
        holder.item_distance_tv.setText(dis);
        Log.d("onBindViewHolder", "" + mData.get(position));
        /*设置导航按钮的点击事件*/
        holder.navigation_img.setOnClickListener(view -> {
            duringSearchMap();
            startToNavigation(park.getLat(), park.getLng());
        });
    }

    /**
     * 启动导航
     *
     * @param lat lat
     * @param lng lng
     */
    private void startToNavigation(String lat, String lng) {
        String gaodeMap = "com.autonavi.minimap";   //高德地图包名：com.autonavi.minimap
        String tencentMap = "com.tencent.map";      //腾讯地图包名：com.tencent.map
        String BaiduMap = "com.baidu.BaiduMap";     //百度地图包名：com.baidu.BaiduMap
        if (isPackageInstalled(gaodeMap)) {
            materialDialog.dismiss();
            startGaodeMap(lat, lng);
            Log.d("isPackageInstalled", "安装了高德地图APP: ");
        } else if (isPackageInstalled(tencentMap)) {
            materialDialog.dismiss();
            startTencentMap(lat, lng);
            //如果安装了腾讯地图APP
            Log.d("isPackageInstalled", "安装了腾讯地图APP: ");
        } else if (isPackageInstalled(BaiduMap)) {
            materialDialog.dismiss();
            startBaiduMap(lat, lng);
            //如果安装了百度地图APP
            Log.d("isPackageInstalled", "安装了百度地图APP:");
        } else {
            materialDialog.dismiss();
            startHTMLMap(lat, lng);
            //如果什么地图都没安装,则打开网页版地图进行导航
            Log.d("isPackageInstalled", "什么地图都没安装: ");
        }
    }

    /**
     * 打开网页版腾讯地图
     *
     * @param lat lat
     * @param lng lng
     */
    private void startHTMLMap(String lat, String lng) {
        Log.d("startHTMLMap", "mlat---" + Latitude);
        Log.d("startHTMLMap", "mlng---" + Longitude);
        Log.d("startHTMLMap", "lat---" + lat);
        Log.d("startHTMLMap", "lng---" + lng);
        Uri mapUri = Uri.parse("http://apis.map.qq.com/uri/v1/routeplan?type=drive" +
                "&from=起点" +
                "&fromcoord=" +
                Latitude +
                "," +
                Longitude +
                "&to=终点" +
                "&tocoord=" +
                lat +
                "," +
                lng +
                "&policy=1" +
                "&referer=myapp");
        Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
        context.startActivity(loction);
    }

    /**
     * 打开百度地图进行导航
     *
     * @param lat 目的地经纬度
     * @param lng 目的地经纬度
     */
    private void startBaiduMap(String lat, String lng) {
        //如果安装了百度地图APP
        Log.d("isPackageInstalled", "安装了百度地图APP: ");
        // 百度地图
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            //将功能Scheme以URI的方式传入data
            Uri uri = Uri.parse("baidumap://map/direction?destination=" + lat + "," + lng + "&mode=driving");
            intent.setData(uri);
            //启动该页面即可
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            startHTMLMap(lat, lng);
        }
    }

    /**
     * 打开腾讯地图进行导航
     *
     * @param lat 目的地经纬度
     * @param lng 目的地经纬度
     */
    private void startTencentMap(String lat, String lng) {
        //如果安装了腾讯地图APP
        Log.d("isPackageInstalled", "安装了腾讯地图APP: ");
        // 腾讯地图
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            //将功能Scheme以URI的方式传入data
            Uri uri = Uri.parse("qqmap://map/routeplan?type=drive&tocoord=" + lat + "," + lng + "&policy=0&referer=baluche");
            intent.setData(uri);
            //启动该页面即可
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            startHTMLMap(lat, lng);
        }
    }

    /**
     * 打开高德地图进行导航
     *
     * @param lat 目的地经纬度
     * @param lng 目的地经纬度
     */
    private void startGaodeMap(String lat, String lng) {
        //如果安装了高德地图APP
        Log.d("isPackageInstalled", "安装了高德地图APP: ");
        // 高德地图
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            //将功能Scheme以URI的方式传入data
            Uri uri = Uri.parse("androidamap://navi?sourceApplication=appname" +
                    "&poiname=fangheng" +
                    "&lat=" +
                    lat +
                    "&lon=" +
                    lng +
                    "&dev=1&style=2");
            intent.setData(uri);
            //启动该页面即可
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            startHTMLMap(lat, lng);
        }
    }

    /**
     * 校验是否装了该应用
     *
     * @param packageName 应用包名
     * @return boolean
     */
    private boolean isPackageInstalled(String packageName) {
        return new File(Environment.getExternalStorageDirectory().getPath() + packageName)
                .exists();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_Location_name_tv;
        TextView item_surplus_tv;
        TextView item_distance_tv;
        ImageView navigation_img;

        public ViewHolder(View itemView) {
            super(itemView);
            item_Location_name_tv = itemView.findViewById(R.id.item_Location_name_tv);
            item_surplus_tv = itemView.findViewById(R.id.item_surplus_tv);
            item_distance_tv = itemView.findViewById(R.id.item_distance_tv);
            navigation_img = itemView.findViewById(R.id.navigation_img);
        }
    }

    /**
     * 展示一个dialog提示用户等待
     */
    private void duringSearchMap() {
        materialDialog = new MaterialDialog.Builder(context)
                .title("请稍候")
                .content("正在打开地图")
                .progress(true, 0)
                .show();
    }
}
