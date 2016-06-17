package com.example.dllo.zhangxiwei_travelapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.SearchResultListAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.bean.SearchResultBean;
import com.example.dllo.zhangxiwei_travelapp.myinterface.SearchClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by dllo on 16/6/1.
 */
public class SearchResultActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    private List<SearchResultBean> resultBeans;
    private SearchResultListAdapter searchResultListAdapter;
    TextView textView;


    @Override
    protected int getLayout() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.activity_search_result_listview);
        textView = bindView(R.id.activity_search_result_title_tv);

    }

    @Override
    protected void initData() {

        textView.setTextColor(Color.WHITE);


        Intent intent = getIntent();
        int id = intent.getIntExtra("placeId", 0);
        int page = intent.getIntExtra("page", 0);

        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getContext());
        StringRequest stringRequest = null;
        if (page == 1) {
            stringRequest = new StringRequest("http://chanyouji.com/api/destinations/trips/" + id + ".json?page=1", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Gson gson = new Gson();
                    resultBeans = gson.fromJson(response, new TypeToken<List<SearchResultBean>>() {
                    }.getType());
                    searchResultListAdapter = new SearchResultListAdapter(MyApplication.getContext());
                    searchResultListAdapter.setSearchResultBeans(resultBeans);
                    listView.setAdapter(searchResultListAdapter);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

        } else if (page == 3) {
            stringRequest = new StringRequest("http://chanyouji.com/api/trips/month/" + id + ".json?page=1", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    resultBeans = gson.fromJson(response, new TypeToken<List<SearchResultBean>>() {
                    }.getType());
                    searchResultListAdapter = new SearchResultListAdapter(MyApplication.getContext());
                    searchResultListAdapter.setSearchResultBeans(resultBeans);
                    listView.setAdapter(searchResultListAdapter);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

        } else if (page == 4) {
            String name = intent.getStringExtra("name");
            stringRequest = new StringRequest("http://chanyouji.com/api/search/trips.json?q=" + name + "&page=1", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    resultBeans = gson.fromJson(response, new TypeToken<List<SearchResultBean>>() {
                    }.getType());
                    searchResultListAdapter = new SearchResultListAdapter(MyApplication.getContext());
                    searchResultListAdapter.setSearchResultBeans(resultBeans);
                    listView.setAdapter(searchResultListAdapter);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });


        }

        requestQueue.add(stringRequest);


        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.d("SearchResultActivity", "position:" + position);
        Intent intent = new Intent(this, NoteContentActivity.class);
        intent.putExtra("noteId", resultBeans.get(position).getId());
        startActivity(intent);


    }
}
