package com.example.dllo.zhangxiwei_travelapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.ToolSelPlaceViewPagerAdapter;
import com.example.dllo.zhangxiwei_travelapp.bean.ToolPlaceBean;
import com.example.dllo.zhangxiwei_travelapp.fragment.ToolSelPlaceViewFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/18.
 * 工具页里的位置选择页
 */
public class ToolSelectPlaceActivity extends FragmentActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    List<ToolPlaceBean> toolPlaceBeans;
    ToolSelPlaceViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tool_select_place);
        viewPager = (ViewPager) findViewById(R.id.tool_select_place_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tool_select_place_tablayout);
        tabLayout.setTabTextColors(Color.rgb(0, 0, 0), Color.rgb(00, 153, 255));

        initData();


    }


    private void initData() {

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

    //ExpandableListView加载数据的方法
    private void initExListView() {


        viewPagerAdapter = new ToolSelPlaceViewPagerAdapter(getSupportFragmentManager());

        List<ToolPlaceBean.DestinationsBean> destinationsBeans = new ArrayList<>();
        List<ToolPlaceBean.DestinationsBean> destinationsBeans2 = new ArrayList<>();

        for (ToolPlaceBean toolPlaceBean : toolPlaceBeans) {
            if (toolPlaceBean.getCategory().equals("1") || toolPlaceBean.getCategory().equals("2") || toolPlaceBean.getCategory().equals("3")) {
                for (ToolPlaceBean.DestinationsBean destinationsBean : toolPlaceBean.getDestinations()) {

                    destinationsBeans.add(destinationsBean);
                }
            }

            if (toolPlaceBean.getCategory().equals("99") || toolPlaceBean.getCategory().equals("999")) {
                for (ToolPlaceBean.DestinationsBean destinationsBean : toolPlaceBean.getDestinations()) {

                    destinationsBeans2.add(destinationsBean);
                }
            }


        }

        List<Fragment> fragments = new ArrayList<>();

        ToolSelPlaceViewFragment toolSelPlaceViewFragment = new ToolSelPlaceViewFragment();
        toolSelPlaceViewFragment.setDestinationsBeans(destinationsBeans);
        ToolSelPlaceViewFragment toolSelPlaceViewFragment1 = new ToolSelPlaceViewFragment();
        toolSelPlaceViewFragment1.setDestinationsBeans(destinationsBeans2);

        fragments.add(toolSelPlaceViewFragment);
        fragments.add(toolSelPlaceViewFragment1);


        viewPagerAdapter.setFragments(fragments);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
