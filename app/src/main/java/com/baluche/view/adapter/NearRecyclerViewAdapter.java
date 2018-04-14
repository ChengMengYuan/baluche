package com.baluche.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.model.entity.Park;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/27 0027.
 */


public class NearRecyclerViewAdapter extends RecyclerView.Adapter<NearRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Park> mData;


    public NearRecyclerViewAdapter(ArrayList<Park> data) {
        Log.d("NearRecyclerViewAdapter", "======");
        for (int i = 0; i < data.size(); i++) {
            Log.d("NearRecyclerViewAdapter", "" + data.get(i));
        }
        this.mData = data;
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
        // 绑定数据
        holder.item_Location_name_tv.setText(mData.get(position).getData().get(position).getTitle());
        holder.item_surplus_tv.setText(mData.get(position).getData().get(position).getNull_number() + "");
        int distance = mData.get(position).getData().get(position).getDistance();
        Log.d("distance", ":" + distance);
        String dis = "";
        if (distance >= 1000) {
            dis = distance / 1000 + "公里";
        } else if (distance < 1000) {
            dis = distance + "米";
        }
        holder.item_distance_tv.setText(dis);
        Log.d("onBindViewHolder", "" + mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_Location_name_tv;
        TextView item_surplus_tv;
        TextView item_distance_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            item_Location_name_tv = itemView.findViewById(R.id.item_Location_name_tv);
            item_surplus_tv = itemView.findViewById(R.id.item_surplus_tv);
            item_distance_tv = itemView.findViewById(R.id.item_distance_tv);
        }
    }
}
