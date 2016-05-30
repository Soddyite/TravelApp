package com.example.dllo.zhangxiwei_travelapp.activity;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.dllo.zhangxiwei_travelapp.adapter.StrategyContentTravelAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentTravelBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/21.
 * 攻略详情页里的行程页
 */
public class StrategyContentTravel extends BaseActivity implements AbsListView.OnScrollListener {

    TextView placeName;
    ListView listView;
    List<StrategyContentTravelBean> contentTravelBeansbeans;
    StrategyContentTravelAdapter adapter;
    private int placeId;
    private String name;
    private RequestQueue requestQueue;
    int id = 2;

    @Override
    protected int getLayout() {
        return R.layout.activity_strategy_content_travel;
    }

    @Override
    protected void initView() {
        placeName = bindView(R.id.strategy_content_title_tv);
        listView = bindView(R.id.strategy_content_travel_listview);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        placeId = intent.getIntExtra("placeId", 0);
        name = intent.getStringExtra("name");
        placeName.setText(name + "行程");
        placeName.setTextColor(Color.WHITE);
        requestQueue = Volley.newRequestQueue(MyApplication.getContext());
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/destinations/plans/" + placeId + ".json?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                contentTravelBeansbeans = gson.fromJson(response, new TypeToken<List<StrategyContentTravelBean>>() {
                }.getType());

                adapter = new StrategyContentTravelAdapter(StrategyContentTravel.this);
                adapter.setContentTravelBeansbeans(contentTravelBeansbeans);
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
        if (view.getLastVisiblePosition() == contentTravelBeansbeans.size() - 1) {

            StringRequest stringRequest1 = new StringRequest("http://chanyouji.com/api/destinations/plans/" + placeId + ".json?page=" + id++, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Gson gson = new Gson();
                    List<StrategyContentTravelBean> contentTravelBeansbeans1 = new ArrayList<>();
                    contentTravelBeansbeans1 = gson.fromJson(response, new TypeToken<List<StrategyContentTravelBean>>() {
                    }.getType());
                    if (contentTravelBeansbeans1.size() > 0) {
//                        adapter.addContentTravelBeansbeans(contentTravelBeansbeans1);

                        for (StrategyContentTravelBean contentTravelBeansbean : contentTravelBeansbeans1) {
                            contentTravelBeansbeans.add(contentTravelBeansbean);
                        }
                        adapter.setContentTravelBeansbeans(contentTravelBeansbeans);
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
