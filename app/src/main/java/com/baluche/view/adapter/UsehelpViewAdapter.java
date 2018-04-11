package com.baluche.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/11 0011.
 */

public class UsehelpViewAdapter extends RecyclerView.Adapter<UsehelpViewAdapter.ViewHolder>{
    private ArrayList<String> mData;


    public UsehelpViewAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public UsehelpViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.help_item, parent, false);
        // 实例化viewholder
        UsehelpViewAdapter.ViewHolder viewHolder = new UsehelpViewAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsehelpViewAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.usehelp_head.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView usehelp_head;

        public ViewHolder(View itemView) {
            super(itemView);
            usehelp_head = itemView.findViewById(R.id.usehelp_head);
        }
    }
}
