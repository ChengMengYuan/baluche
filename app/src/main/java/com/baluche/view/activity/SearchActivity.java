package com.baluche.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baluche.R;
import com.baluche.view.adapter.SearchSuggestionAdapter;

import java.util.ArrayList;

public class SearchActivity extends BaseActivity {

    private ImageView back_search_img;//返回按钮
    private EditText search_et;//搜索框
    private TextView search_tv;//搜索按钮
    private RecyclerView search_rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> data = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public void setActivityPre() {

    }

    @Override
    public void initView() {
        data.add("红谷凯旋地下停车场");
        data.add("红谷凯旋南区地下停车场");
        data.add("红谷凯旋东区地下停车场");
        data.add("红谷凯旋北区地下停车场");
        data.add("红谷凯旋西区地下停车场");
        back_search_img = findViewById(R.id.back_search_img);
        back_search_img.setOnClickListener(this);
        search_et = findViewById(R.id.search_et);
        search_et.setOnClickListener(this);
        search_tv = findViewById(R.id.search_tv);
        search_tv.setOnClickListener(this);
        search_rv = findViewById(R.id.search_rv);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new SearchSuggestionAdapter(data);
        search_rv.setLayoutManager(mLayoutManager);
        search_rv.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {
        search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //输入框有变化的时候
                $Log("onTextChanged");
                $Log("" + charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.back_search_img:
                finish();
                break;
            case R.id.search_et:
                break;
            case R.id.search_tv:
                break;
        }
    }

}
