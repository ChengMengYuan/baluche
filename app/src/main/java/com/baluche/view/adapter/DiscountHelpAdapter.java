package com.baluche.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class DiscountHelpAdapter extends RecyclerView.Adapter<DiscountHelpAdapter.ViewHolder>{
    private ArrayList<String> mData;


    public DiscountHelpAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public DiscountHelpAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_help_item, parent, false);
        // 实例化viewholder
        DiscountHelpAdapter.ViewHolder viewHolder = new DiscountHelpAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DiscountHelpAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.discount_help_one.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView discount_help_one;

        public ViewHolder(View itemView) {
            super(itemView);
            discount_help_one = itemView.findViewById(R.id.discount_help_one);
        }
    }
}
