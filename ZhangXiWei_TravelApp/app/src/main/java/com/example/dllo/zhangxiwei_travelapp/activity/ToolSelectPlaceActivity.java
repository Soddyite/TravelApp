package com.example.dllo.zhangxiwei_travelapp.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.ToolSelPlaceExListAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBean;
import com.example.dllo.zhangxiwei_travelapp.bean.ToolPlaceBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by dllo on 16/5/18.
 */
public class ToolSelectPlaceActivity extends BaseActivity {

    ExpandableListView exListView;
    TabLayout tabLayout;
    List<ToolPlaceBean> toolPlaceBeans;

    @Override
    protected int getLayout() {
        return R.layout.activity_tool_select_place;
    }

    @Override
    protected void initView() {
        exListView = bindView(R.id.tool_select_place_exlist);
        tabLayout = bindView(R.id.tool_select_place_tablayout);

    }


    @Override
    protected void initData() {

        RequestQueue requestQueuel = Volley.newRequestQueue(MyApplication.getContext());
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/wiki/destinations.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                toolPlaceBeans = gson.fromJson(response, new TypeToken<List<ToolPlaceBean>>() {
                }.getType());
                initExListView();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueuel.add(stringRequest);

    }

    private void initExListView() {
        Intent intent = getIntent();
        intent.putExtra("placeId", 123456);
        setResult(2, intent);

        ToolSelPlaceExListAdapter adapter = new ToolSelPlaceExListAdapter(MyApplication.getContext());
        adapter.setToolPlaceBeans(toolPlaceBeans);
        exListView.setAdapter(adapter);






    }

}
