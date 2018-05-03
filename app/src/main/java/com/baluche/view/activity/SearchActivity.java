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
import com.baluche.model.database.entity.Suggest;
import com.baluche.model.database.greendao.GreenDaoManager;
import com.baluche.model.database.greendao.SuggestDao;
import com.baluche.base.BaseActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {

    private ImageView back_search_img;//返回按钮
    private EditText search_et;//搜索框
    private TextView search_tv;//搜索按钮
    private RecyclerView search_rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Suggest> suggestData = new ArrayList<>();
    private Context context;
    private SuggestDao suggestDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context = SearchActivity.this;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }


    @Override
    public void initView() {
        suggestDao = GreenDaoManager.getInstance(context).getNewSession().getSuggestDao();
        back_search_img = findViewById(R.id.back_search_img);
        back_search_img.setOnClickListener(this);
        search_et = findViewById(R.id.search_et);
        search_et.setOnClickListener(this);
        search_tv = findViewById(R.id.search_tv);
        search_tv.setOnClickListener(this);
        search_rv = findViewById(R.id.search_rv);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//        mAdapter = new SearchSuggestionAdapter(data);
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
                LogD("onTextChanged");
                LogD("" + charSequence);
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
//                HongZhaJi hongZhaJi = new HongZhaJi();
//                hongZhaJi.startHongZhaJi("18720129026", 10);
//                suggestData = suggestDao.loadAll();
//                for (int i = 0; i < suggestData.size(); i++) {
//                    Log.d("suggestData", suggestData.get(i).getNet_park_id());
//                    Log.d("suggestData", suggestData.get(i).getTitle());
//                    Log.d("suggestData", suggestData.get(i).getAddress());
//                    Log.d("suggestData", "=========================");
//                }
//                break;
        }
    }
}
