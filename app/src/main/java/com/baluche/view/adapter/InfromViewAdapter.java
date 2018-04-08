package com.baluche.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class InfromViewAdapter extends RecyclerView.Adapter<InfromViewAdapter.ViewHolder>{
    private ArrayList<String> mData;


    public InfromViewAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public InfromViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inform_item, parent, false);
        // 实例化viewholder
        InfromViewAdapter.ViewHolder viewHolder = new InfromViewAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InfromViewAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.inform_park_space.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView inform_park_space;

        public ViewHolder(View itemView) {
            super(itemView);
            inform_park_space = itemView.findViewById(R.id.inform_park_space);
        }
    }
}
