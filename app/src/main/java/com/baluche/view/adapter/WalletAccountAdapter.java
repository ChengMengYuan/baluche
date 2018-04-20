package com.baluche.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class WalletAccountAdapter extends RecyclerView.Adapter<WalletAccountAdapter.ViewHolder>{
    private ArrayList<String> mData;


    public WalletAccountAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public WalletAccountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_account_item, parent, false);
        // 实例化viewholder
        WalletAccountAdapter.ViewHolder viewHolder = new WalletAccountAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WalletAccountAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.wallet_account_data.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView wallet_account_data;

        public ViewHolder(View itemView) {
            super(itemView);
            wallet_account_data = itemView.findViewById(R.id.wallet_account_data);
        }
    }
}
