package com.baluche.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/21 0021.
 */

public class HotProblemViewAdapter extends RecyclerView.Adapter<HotProblemViewAdapter.ViewHolder> {
    private ArrayList<String> mData;

    public HotProblemViewAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    @Override
    public HotProblemViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_problem_item, parent, false);
        // 实例化viewholder
        HotProblemViewAdapter.ViewHolder viewHolder = new HotProblemViewAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HotProblemViewAdapter.ViewHolder holder, int position) {
        holder.inform_entrance_inform.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView inform_entrance_inform;

        public ViewHolder(View itemView) {
            super(itemView);
            inform_entrance_inform = itemView.findViewById(R.id.inform_entrance_inform);
        }
    }
}
