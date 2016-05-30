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
import com.example.dllo.zhangxiwei_travelapp.adapter.StrategyContentDestinationAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentDestinationBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/21.
 * 攻略详细页里的旅行地页
 */
public class StrategyContentDestination extends BaseActivity implements AbsListView.OnScrollListener {

    TextView placeName;
    ListView listView;
    private int placeId;
    private String name;
    List<StrategyContentDestinationBean> destinationBeans;
    int id = 2;
    private RequestQueue requestQueue;
    private StrategyContentDestinationAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_strategy_content_destination;
    }

    @Override
    protected void initView() {
        placeName = bindView(R.id.strategy_content_title_tv);
        listView = bindView(R.id.strategy_content_destination_listview);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        placeId = intent.getIntExtra("placeId", 0);
        placeName.setText(name + "旅行地");
        placeName.setTextColor(Color.WHITE);

        destinationBeans = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/destinations/attractions/" + placeId + ".json?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                destinationBeans = gson.fromJson(response, new TypeToken<List<StrategyContentDestinationBean>>() {
                }.getType());
                adapter = new StrategyContentDestinationAdapter(MyApplication.getContext());
                adapter.setDestinationBeans(destinationBeans);
                listView.setAdapter(adapter);

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
        Log.d("StrategyContentDestinat", "view.getLastVisiblePosition():" + view.getLastVisiblePosition());
        Log.d("StrategyContentDestinat", "destinationBeans.size():" + destinationBeans.size());
        if (view.getLastVisiblePosition() == destinationBeans.size() - 1) {
            StringRequest stringRequest1 = new StringRequest("http://chanyouji.com/api/destinations/attractions/" + placeId + ".json?page=" + id++, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Gson gson = new Gson();

                    List<StrategyContentDestinationBean> destinationBeans1 = new ArrayList<>();
                    destinationBeans1 = gson.fromJson(response, new TypeToken<List<StrategyContentDestinationBean>>() {
                    }.getType());
                    if (destinationBeans1.size() > 0) {
//                        adapter.addDestinationBeans(destinationBeans1);

                        for (StrategyContentDestinationBean strategyContentDestinationBean : destinationBeans1) {
                            destinationBeans.add(strategyContentDestinationBean);

                        }
                        adapter.setDestinationBeans(destinationBeans);
                    }

                    Log.d("StrategyContentDestinat", "destinationBeans1.size():" + destinationBeans1.size());


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("StrategyContentDestinat", "error");

                }
            });

            requestQueue.add(stringRequest1);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
