package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.StrategyListViewAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseFragment;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyBean;
import com.example.dllo.zhangxiwei_travelapp.myinterface.StrategyClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class FragmentStrategy extends BaseFragment implements StrategyClickListener, AdapterView.OnItemClickListener {

    ListView strategyListView;
    List<StrategyBean> strategyBeans;
    StrategyListViewAdapter strategyListViewAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_strategy;
    }

    @Override
    public void initView() {

        strategyListView = bindView(R.id.fragment_strategy_listview);

    }

    @Override
    public void initData() {
        strategyListViewAdapter = new StrategyListViewAdapter(MyApplication.getContext());
        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getContext());

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/destinations.json?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                strategyBeans = gson.fromJson(response, new TypeToken<List<StrategyBean>>() {
                }.getType());
                strategyListViewAdapter.setStrategyBeans(strategyBeans);
                strategyListView.setAdapter(strategyListViewAdapter);

                //攻略页的头布局
                View strategyListHead = LayoutInflater.from(getContext()).inflate(R.layout.fragment_strategy_listhead, null);
                strategyListView.addHeaderView(strategyListHead);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
        strategyListView.setOnItemClickListener(this);


    }

    @Override
    public void MyStrategyOnClick(int pos) {
        Log.d("FragmentStrategy", "pos:" + pos);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        strategyListViewAdapter.onClick(this);
    }
}
