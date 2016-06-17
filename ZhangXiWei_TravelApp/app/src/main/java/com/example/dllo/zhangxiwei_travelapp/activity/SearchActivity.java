package com.example.dllo.zhangxiwei_travelapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.SearchViewPagerAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.bean.SearchBean;
import com.example.dllo.zhangxiwei_travelapp.bean.SearchSingleBean;
import com.example.dllo.zhangxiwei_travelapp.fragment.SearchFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/6/1.
 */
public class SearchActivity extends BaseActivity {

    EditText searchEt;
    TabLayout tabLayout;
    ViewPager viewPager;
    SearchViewPagerAdapter pagerAdapter;
    SearchBean searchBean;
    EditText editText;
    Button button;


    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

        searchEt = bindView(R.id.activity_search_title_et);
        tabLayout = bindView(R.id.activity_search_tablayout);
        viewPager = bindView(R.id.activity_search_viewpager);
        editText = bindView(R.id.activity_search_title_et);
        button = bindView(R.id.activity_search_title_btn);

    }

    @Override
    protected void initData() {


        pagerAdapter = new SearchViewPagerAdapter(getSupportFragmentManager());


        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getContext());
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/destinations/list.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                searchBean = gson.fromJson(response, SearchBean.class);

                List<Fragment> fragments = new ArrayList<>();

                List<SearchBean.OtherDestinationsBean> otherDestinationsBeans = searchBean.getOther_destinations();
                List<SearchSingleBean> searchSingleBeans1 = new ArrayList<>();
                for (SearchBean.OtherDestinationsBean otherDestinationsBean : otherDestinationsBeans) {
                    searchSingleBeans1.add(new SearchSingleBean(otherDestinationsBean.getId(), otherDestinationsBean.getName()));
                }


                SearchFragment fragment1 = new SearchFragment();
                fragment1.setSearchSingleBeans(searchSingleBeans1);
                fragments.add(fragment1);


                List<SearchBean.ChinaDestinationsBean> chinaDestinationsBeans = searchBean.getChina_destinations();
                List<SearchSingleBean> searchSingleBeans2 = new ArrayList<>();
                for (SearchBean.ChinaDestinationsBean chinaDestinationsBean : chinaDestinationsBeans) {
                    searchSingleBeans2.add(new SearchSingleBean(chinaDestinationsBean.getId(), chinaDestinationsBean.getName()));
                }

                SearchFragment fragment2 = new SearchFragment();
                fragment2.setSearchSingleBeans(searchSingleBeans2);
                fragments.add(fragment2);


                List<SearchSingleBean> searchSingleBeans3 = new ArrayList<>();
                for (int i = 1; i <= 12; i++) {
                    searchSingleBeans3.add(new SearchSingleBean(i, i + "月"));

                }

                SearchFragment fragment3 = new SearchFragment();
                fragment3.setSearchSingleBeans(searchSingleBeans3);

                fragments.add(fragment3);


                pagerAdapter.setFragments(fragments);
                viewPager.setAdapter(pagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.setTabTextColors(Color.rgb(0, 0, 0), Color.rgb(00, 153, 255));
                tabLayout.setSelectedTabIndicatorColor(Color.rgb(00, 153, 255));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        requestQueue.add(stringRequest);


        editText.setTextColor(Color.WHITE);
        button.setTextColor(Color.WHITE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(editText.getText());
                Intent intent = new Intent(MyApplication.getContext(), SearchResultActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("page", 4);
                startActivity(intent);

            }
        });

    }

}
