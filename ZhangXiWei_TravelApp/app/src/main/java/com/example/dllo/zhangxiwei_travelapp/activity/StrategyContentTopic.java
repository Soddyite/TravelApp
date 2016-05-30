package com.example.dllo.zhangxiwei_travelapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.StrategyContentTopicAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentTopicBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/21.
 * 攻略详情页里的专题页
 */
public class StrategyContentTopic extends BaseActivity implements AbsListView.OnScrollListener {

    ListView listView;
    TextView textView;
    List<StrategyContentTopicBean> topicBeans;
    private int placeId;
    int id = 2;
    private RequestQueue requestQueue;
    private StrategyContentTopicAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_strategy_content_topic;
    }

    @Override
    protected void initView() {
        textView = bindView(R.id.strategy_content_title_tv);
        listView = bindView(R.id.strategy_content_topic_listview);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        placeId = intent.getIntExtra("placeId", 0);
        textView.setText(name + "专题");
        textView.setTextColor(Color.WHITE);


        topicBeans = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(MyApplication.getContext());
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/articles.json?destination_id=" + placeId + "&page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                topicBeans = gson.fromJson(response, new TypeToken<List<StrategyContentTopicBean>>() {
                }.getType());
                adapter = new StrategyContentTopicAdapter(MyApplication.getContext());
                adapter.setTopicBeans(topicBeans);
                listView.setAdapter(adapter);
                Log.d("StrategyContentTopic", "topicBeans.size():" + topicBeans.size());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        requestQueue.add(stringRequest);

        listView.setOnScrollListener(this);


    }

    //下拉加载
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (view.getLastVisiblePosition() == topicBeans.size() - 1) {
            StringRequest stringRequest1 = new StringRequest("http://chanyouji.com/api/articles.json?destination_id=" + placeId + "&page=" + id++, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Gson gson = new Gson();
                    List<StrategyContentTopicBean> strategyContentTopicBeans = new ArrayList<>();
                    strategyContentTopicBeans = gson.fromJson(response, new TypeToken<List<StrategyContentTopicBean>>() {
                    }.getType());
                    if (strategyContentTopicBeans.size() > 0) {
//                        adapter.addTopicBeans(strategyContentTopicBeans);

                        for (StrategyContentTopicBean strategyContentTopicBean : strategyContentTopicBeans) {
                            topicBeans.add(strategyContentTopicBean);
                        }
                        adapter.setTopicBeans(topicBeans);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            requestQueue.add(stringRequest1);


        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
