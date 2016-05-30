package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.activity.StrategyContentActivity;
import com.example.dllo.zhangxiwei_travelapp.adapter.StrategyListViewAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseFragment;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyBean;
import com.example.dllo.zhangxiwei_travelapp.entity.RecentStrategyEntity;
import com.example.dllo.zhangxiwei_travelapp.entity.RecentStrategyEntityDao;
import com.example.dllo.zhangxiwei_travelapp.myinterface.StrategyClickListener;
import com.example.dllo.zhangxiwei_travelapp.singleton.RecentStrategySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by dllo on 16/5/9.
 * 攻略页fragment
 *
 */
public class FragmentStrategy extends BaseFragment implements StrategyClickListener {

    ListView strategyListView;
    List<StrategyBean> strategyBeans;
    StrategyListViewAdapter strategyListViewAdapter;
    private View strategyListHead;
    private String place;
    private RecentStrategyEntityDao entityDao;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private List<RecentStrategyEntity> entities;

    @Override
    public int getLayout() {
        return R.layout.fragment_strategy;
    }

    @Override
    public void initView() {

        strategyListView = bindView(R.id.fragment_strategy_listview);
        //攻略页的头布局
        strategyListHead = LayoutInflater.from(getContext()).inflate(R.layout.fragment_strategy_listhead, null);

        textView1 = (TextView) strategyListHead.findViewById(R.id.strategy_recent_record_1);
        textView2 = (TextView) strategyListHead.findViewById(R.id.strategy_recent_record_2);
        textView3 = (TextView) strategyListHead.findViewById(R.id.strategy_recent_record_3);
        textView4 = (TextView) strategyListHead.findViewById(R.id.strategy_recent_record_4);
        textView5 = (TextView) strategyListHead.findViewById(R.id.strategy_recent_record_5);
        textView6 = (TextView) strategyListHead.findViewById(R.id.strategy_recent_record_6);

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

                readRecentStrategy();

                strategyListView.addHeaderView(strategyListHead);
                strategyListViewAdapter.onClick(FragmentStrategy.this);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }

    //自定义listview中的recyclerview点击事件的接口
    @Override
    public void MyStrategyOnClick(int placeId, String placeName) {

        Intent intent = new Intent(getContext(), StrategyContentActivity.class);
        intent.putExtra("placeId", placeId);
        startActivity(intent);
        place = placeName;
        Log.d("FragmentStrategy", placeName);

        saveRecentPlace();

    }

    //存储最近浏览的地点到数据库
    private void saveRecentPlace() {

        entityDao.deleteAll();
        if (place.equals(textView1.getText())) {

            entityDao.insert(new RecentStrategyEntity(0l, place, 1));
            entityDao.insert(new RecentStrategyEntity(1l, textView2.getText().toString(), 2));
            entityDao.insert(new RecentStrategyEntity(2l, textView3.getText().toString(), 3));
            entityDao.insert(new RecentStrategyEntity(3l, textView4.getText().toString(), 4));
            entityDao.insert(new RecentStrategyEntity(4l, textView5.getText().toString(), 5));
            entityDao.insert(new RecentStrategyEntity(5l, textView6.getText().toString(), 6));


        } else if (place.equals(textView2.getText())) {

            entityDao.insert(new RecentStrategyEntity(0l, place, 1));
            entityDao.insert(new RecentStrategyEntity(1l, textView1.getText().toString(), 2));
            entityDao.insert(new RecentStrategyEntity(2l, textView3.getText().toString(), 3));
            entityDao.insert(new RecentStrategyEntity(3l, textView4.getText().toString(), 4));
            entityDao.insert(new RecentStrategyEntity(4l, textView5.getText().toString(), 5));
            entityDao.insert(new RecentStrategyEntity(5l, textView6.getText().toString(), 6));



        } else if (place.equals(textView3.getText())) {

            entityDao.insert(new RecentStrategyEntity(0l, place, 1));
            entityDao.insert(new RecentStrategyEntity(1l, textView1.getText().toString(), 2));
            entityDao.insert(new RecentStrategyEntity(2l, textView2.getText().toString(), 3));
            entityDao.insert(new RecentStrategyEntity(3l, textView4.getText().toString(), 4));
            entityDao.insert(new RecentStrategyEntity(4l, textView5.getText().toString(), 5));
            entityDao.insert(new RecentStrategyEntity(5l, textView6.getText().toString(), 6));

        } else if (place.equals(textView4.getText())) {
            entityDao.insert(new RecentStrategyEntity(0l, place, 1));
            entityDao.insert(new RecentStrategyEntity(1l, textView1.getText().toString(), 2));
            entityDao.insert(new RecentStrategyEntity(2l, textView2.getText().toString(), 3));
            entityDao.insert(new RecentStrategyEntity(3l, textView3.getText().toString(), 4));
            entityDao.insert(new RecentStrategyEntity(4l, textView5.getText().toString(), 5));
            entityDao.insert(new RecentStrategyEntity(5l, textView6.getText().toString(), 6));

        } else if (place.equals(textView5.getText())) {
            entityDao.insert(new RecentStrategyEntity(0l, place, 1));
            entityDao.insert(new RecentStrategyEntity(1l, textView1.getText().toString(), 2));
            entityDao.insert(new RecentStrategyEntity(2l, textView2.getText().toString(), 3));
            entityDao.insert(new RecentStrategyEntity(3l, textView3.getText().toString(), 4));
            entityDao.insert(new RecentStrategyEntity(4l, textView4.getText().toString(), 5));
            entityDao.insert(new RecentStrategyEntity(5l, textView6.getText().toString(), 6));

        } else if (place.equals(textView6.getText())) {

            entityDao.insert(new RecentStrategyEntity(0l, place, 1));
            entityDao.insert(new RecentStrategyEntity(1l, textView1.getText().toString(), 2));
            entityDao.insert(new RecentStrategyEntity(2l, textView2.getText().toString(), 3));
            entityDao.insert(new RecentStrategyEntity(3l, textView3.getText().toString(), 4));
            entityDao.insert(new RecentStrategyEntity(4l, textView4.getText().toString(), 5));
            entityDao.insert(new RecentStrategyEntity(5l, textView5.getText().toString(), 6));

        } else {

            entityDao.insert(new RecentStrategyEntity(0l, place, 1));
            entityDao.insert(new RecentStrategyEntity(1l, textView1.getText().toString(), 2));
            entityDao.insert(new RecentStrategyEntity(2l, textView2.getText().toString(), 3));
            entityDao.insert(new RecentStrategyEntity(3l, textView3.getText().toString(), 4));
            entityDao.insert(new RecentStrategyEntity(4l, textView4.getText().toString(), 5));
            entityDao.insert(new RecentStrategyEntity(5l, textView5.getText().toString(), 6));

        }


    }


    //从数据库取出最近查看的地点
    private void readRecentStrategy() {
        entityDao = RecentStrategySingleton.getInstance().getEntityDao();
        entities = entityDao.queryBuilder().list();

        if (entities.size() > 0) {

            if (entities.get(0).getPlaceName() != null) {
                textView1.setText(entities.get(0).getPlaceName());
            }
            if (entities.get(1).getPlaceName() != null) {
                textView2.setText(entities.get(1).getPlaceName());
            }
            if (entities.get(2).getPlaceName() != null) {
                textView3.setText(entities.get(2).getPlaceName());
            }
            if (entities.get(3).getPlaceName() != null) {
                textView4.setText(entities.get(3).getPlaceName());
            }
            if (entities.get(4).getPlaceName() != null) {
                textView5.setText(entities.get(4).getPlaceName());
            }
            if (entities.get(5).getPlaceName() != null) {
                textView6.setText(entities.get(5).getPlaceName());
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        readRecentStrategy();
    }

}
