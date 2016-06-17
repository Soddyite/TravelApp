package com.example.dllo.zhangxiwei_travelapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.StrategyContentStrategyAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentStrategyBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by dllo on 16/5/21.
 * 攻略详细内容里的攻略页
 */
public class StrategyContentStrategy extends BaseActivity {

    TextView titleTv;
    StrategyContentStrategyAdapter adapter;
    ListView listView;


    @Override
    protected int getLayout() {
        return R.layout.activity_strategy_content_strategy;
    }

    @Override
    protected void initView() {
        titleTv = bindView(R.id.strategy_content_title_tv);
        listView = bindView(R.id.strategy_content_listview);

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        int placeId = intent.getIntExtra("placeId", 0);
        String name = intent.getStringExtra("name");
        titleTv.setText(name + "攻略");
        titleTv.setTextColor(Color.WHITE);
        Log.d("StrategyContentStrategy", "placeId:" + placeId);


        /**
         * 接口请求的数据过大,请求时会OOM
         */
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/wiki/destinations/" + placeId + ".json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                List<StrategyContentStrategyBean> contentStrategyBeans = gson.fromJson(response, new TypeToken<List<StrategyContentStrategy>>() {
                }.getType());
                adapter = new StrategyContentStrategyAdapter(MyApplication.getContext());
                adapter.setContentStrategies(contentStrategyBeans);
                listView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);


    }
}
