package com.baluche.view.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.model.entity.Park;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27 0027.
 */


public class NearRecyclerViewAdapter extends RecyclerView.Adapter<NearRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Park> mData;
    private Context context;


    public NearRecyclerViewAdapter(ArrayList<Park> data, Context context) {
        Log.d("NearRecyclerViewAdapter", "======");
        for (int i = 0; i < data.size(); i++) {
            Log.d("NearRecyclerViewAdapter", "" + data.get(i));
        }
        this.mData = data;
        this.context = context;
    }

    public void updateData(ArrayList<Park> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public NearRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.near_rv_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NearRecyclerViewAdapter.ViewHolder holder, int position) {
        Park.DataBean park = mData.get(position).getData().get(position);
        /*设置停车场名称*/
        holder.item_Location_name_tv.setText(park.getTitle());
        /*设置到停车场的距离*/
        holder.item_surplus_tv.setText(park.getNull_number() + "");
        int distance = park.getDistance();
        Log.d("distance", ":" + distance);
        String dis = "";
        if (distance >= 1000) {
            dis = distance / 1000 + "公里";
        } else if (distance < 1000) {
            dis = distance + "米";
        }
        holder.item_distance_tv.setText(dis);
        Log.d("onBindViewHolder", "" + mData.get(position));
        /*设置导航按钮的点击事件*/
        holder.navigation_img.setOnClickListener(view -> {
            startNaviGao(park.getLat(), park.getLng());
        });
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

    //验证各种导航地图是否安装
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 高德地图,起点就是定位点
     * 终点是 LatLng ll = new LatLng("你的纬度latitude","你的经度longitude");
     */
    public void startNaviGao(String latitude, String longitude) {
        if (isAvilible(context, "com.autonavi.minimap")) {
            try {
                //sourceApplication
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=公司的名称（随意写）" +
                        "&poiname=我的目的地&lat=" + latitude + "&lon=" + longitude + "&dev=0");
                context.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "您尚未安装高德地图或地图版本过低", Toast.LENGTH_LONG).show();
        }
    }

}
