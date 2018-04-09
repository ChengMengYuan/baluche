package com.baluche.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class PayingbillViewAdapter extends RecyclerView.Adapter<PayingbillViewAdapter.ViewHolder>{
    private ArrayList<String> mData;


    public PayingbillViewAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public PayingbillViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.costbill_item, parent, false);
        // 实例化viewholder
        PayingbillViewAdapter.ViewHolder viewHolder = new PayingbillViewAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PayingbillViewAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.payingbill_park_space.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView payingbill_park_space;

        public ViewHolder(View itemView) {
            super(itemView);
            payingbill_park_space = itemView.findViewById(R.id.payingbill_park_space);
        }
    }
}
