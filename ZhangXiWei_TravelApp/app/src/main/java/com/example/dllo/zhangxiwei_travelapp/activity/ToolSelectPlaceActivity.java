package com.example.dllo.zhangxiwei_travelapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.ToolSelPlaceExListAdapter;
import com.example.dllo.zhangxiwei_travelapp.adapter.ToolSelPlaceViewPagerAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.bean.ToolPlaceBean;
import com.example.dllo.zhangxiwei_travelapp.fragment.ToolSelPlaceViewFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/18.
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

        fragments.add(new ToolSelPlaceViewFragment(destinationsBeans));
        fragments.add(new ToolSelPlaceViewFragment(destinationsBeans2));


        viewPagerAdapter.setFragments(fragments);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
