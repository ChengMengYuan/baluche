package com.baluche.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class VehicleManageViewAdapter extends RecyclerView.Adapter<VehicleManageViewAdapter.ViewHolder>{
    private ArrayList<String> mData;

    public VehicleManageViewAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    @Override
    public VehicleManageViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_item, parent, false);
        // 实例化viewholder
        VehicleManageViewAdapter.ViewHolder viewHolder = new VehicleManageViewAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VehicleManageViewAdapter.ViewHolder holder, int position) {
        holder.vehicle_unbound.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vehicle_unbound;

        public ViewHolder(View itemView) {
            super(itemView);
            vehicle_unbound = itemView.findViewById(R.id.vehicle_unbound);
        }
    }
}
