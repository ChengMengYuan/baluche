package com.baluche.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baluche.R;
import com.baluche.view.adapter.HotProblemViewAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import me.next.tagview.TagCloudView;

public class CommonProblemFragment extends Fragment {

    private Context context;/*Title控件 */
    private RecyclerView.Adapter mAdapter;
    private RecyclerView common_problem_listView;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> tags = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_common_problem, container, false);
        initData();
        initView(savedInstanceState, v);
        return v;
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            data.add("海南停車場" + i);
        }

        tags.add("红谷凯旋地下停车场");
        tags.add("红谷");
        tags.add("红谷凯旋");
        tags.add("红谷凯旋北区");
        tags.add("红谷凯旋西区地下停车场");
    }

    private void initView(Bundle savedInstanceState, View v) {
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new HotProblemViewAdapter(data);
        common_problem_listView = v.findViewById(R.id.common_problem_listView);
        // 设置布局管理器
        common_problem_listView.setLayoutManager(mLayoutManager);
        // 设置adapter
        common_problem_listView.setAdapter(mAdapter);


        TagCloudView tagCloudView = v.findViewById(R.id.common_problem_tag_cloud);
        tagCloudView.setTags(tags);
        tagCloudView.setOnTagClickListener(new TagCloudView.OnTagClickListener() {
            @Override
            public void onTagClick(int position) {
                Toast.makeText(context, "TagView onClick",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
