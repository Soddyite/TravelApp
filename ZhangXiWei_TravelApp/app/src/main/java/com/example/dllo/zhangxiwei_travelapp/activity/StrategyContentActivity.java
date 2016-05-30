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
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.StrategyContentAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentBean;
import com.example.dllo.zhangxiwei_travelapp.myinterface.StrategyContentClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by dllo on 16/5/16.
 * 攻略详细内容页
 */
public class StrategyContentActivity extends BaseActivity implements StrategyContentClickListener {

    ListView listView;
    TextView placeName;
    List<StrategyContentBean> contentBeans;
    StrategyContentAdapter contentAdapter;
    private int placeId;

    @Override
    protected int getLayout() {
        return R.layout.activity_strategy_content;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.strategy_content_listview);
        placeName = bindView(R.id.strategy_content_title_tv);


    }

    @Override
    protected void initData() {
        contentAdapter = new StrategyContentAdapter(MyApplication.getContext());
        placeName.setTextColor(Color.WHITE);

        Intent intent = getIntent();
        placeId = intent.getIntExtra("placeId", 0);
        Log.d("StrategyContentActivity", "placeId:" + placeId);

        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getContext());
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/destinations/" + placeId + ".json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                contentBeans = gson.fromJson(response, new TypeToken<List<StrategyContentBean>>() {
                }.getType());
                contentAdapter.setContentBeans(contentBeans);
                contentAdapter.setStrategyClickListener(StrategyContentActivity.this);
                Log.d("StrategyContentActivity", "contentBeans.size():" + contentBeans.get(0).getName_zh_cn());
                listView.setAdapter(contentAdapter);
                placeName.setText(contentBeans.get(0).getName_zh_cn() + "攻略");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


    }


    //设置攻略详情页的点击事件,并判断点击的是什么内容
    @Override
    public void MyStrategyContentOnClick(int pos, String name, int vId) {
        Intent intent = new Intent();
        switch (vId) {
            case R.id.strategy_content_radiobutton_strategy:
                intent.setClass(this, StrategyContentStrategy.class);
                intent.putExtra("placeId", pos);
                intent.putExtra("name", name);
                startActivity(intent);
                break;
            case R.id.strategy_content_radiobutton_travel:
                intent.setClass(this, StrategyContentTravel.class);
                intent.putExtra("placeId", pos);
                intent.putExtra("name", name);
                startActivity(intent);
                break;

            case R.id.strategy_content_radiobutton_destination:
                intent.setClass(this, StrategyContentDestination.class);
                intent.putExtra("placeId", pos);
                intent.putExtra("name", name);
                startActivity(intent);
                break;
            case R.id.strategy_content_radiobutton_topic:
                intent.setClass(this, StrategyContentTopic.class);
                intent.putExtra("placeId", pos);
                intent.putExtra("name", name);
                startActivity(intent);
                break;
        }
    }
}
