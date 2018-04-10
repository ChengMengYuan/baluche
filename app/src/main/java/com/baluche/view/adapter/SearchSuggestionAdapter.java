package com.baluche.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baluche.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class SearchSuggestionAdapter extends RecyclerView.Adapter<SearchSuggestionAdapter.ViewHolder> {
    private ArrayList<String> mData;

    public SearchSuggestionAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    @Override
    public SearchSuggestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_item, parent, false);
        // 实例化viewholder
        SearchSuggestionAdapter.ViewHolder viewHolder = new SearchSuggestionAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchSuggestionAdapter.ViewHolder holder, int position) {
        holder.park_name_sugg.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView park_name_sugg;

        public ViewHolder(View itemView) {
            super(itemView);
            park_name_sugg = itemView.findViewById(R.id.park_name_sugg);
        }
    }
}
