package com.baluche.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/27 0027.
 */


public class NearRecyclerViewAdapter extends RecyclerView.Adapter<NearRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> mData;


    public NearRecyclerViewAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<String> data) {
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
        holder.item_Location_name_tv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_Location_name_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            item_Location_name_tv = itemView.findViewById(R.id.item_Location_name_tv);
        }
    }
}
