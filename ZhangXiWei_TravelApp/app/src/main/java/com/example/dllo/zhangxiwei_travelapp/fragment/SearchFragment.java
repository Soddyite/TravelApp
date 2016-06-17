package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.activity.NoteContentActivity;
import com.example.dllo.zhangxiwei_travelapp.activity.SearchResultActivity;
import com.example.dllo.zhangxiwei_travelapp.adapter.SearchFragmentRecyclerAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseFragment;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.bean.SearchBean;
import com.example.dllo.zhangxiwei_travelapp.bean.SearchSingleBean;
import com.example.dllo.zhangxiwei_travelapp.myinterface.SearchClickListener;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/6/1.
 */
public class SearchFragment extends BaseFragment implements SearchClickListener {

    RecyclerView recyclerView;
    SearchFragmentRecyclerAdapter recyclerAdapter;
    List<SearchSingleBean> searchSingleBeans;



    public void setSearchSingleBeans(List<SearchSingleBean> searchSingleBeans) {
        this.searchSingleBeans = searchSingleBeans;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView() {
        recyclerView = bindView(R.id.fragment_search_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(MyApplication.getContext(), 3));


    }

    @Override
    public void initData() {

        recyclerAdapter = new SearchFragmentRecyclerAdapter(MyApplication.getContext());
        recyclerAdapter.setSearchSingleBeans(searchSingleBeans);

        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.setSearchClickListener(this);

    }

    @Override
    public void mySearchOnClick(int pos) {
        Log.d("SearchFragment", "pos:" + pos);
        Intent intent = new Intent(SearchFragment.this.getContext(), SearchResultActivity.class);
        intent.putExtra("placeId", searchSingleBeans.get(pos).getId());
        String name = searchSingleBeans.get(pos).getName();
        if (name.equals("1月") || name.equals("2月") || name.equals("3月") || name.equals("4月") ||
                name.equals("5月") || name.equals("6月") || name.equals("7月") || name.equals("8月") ||
                name.equals("9月") || name.equals("10月") || name.equals("11月") || name.equals("12月")) {
            intent.putExtra("page", 3);
        } else {
            intent.putExtra("page", 1);
        }
        startActivity(intent);

    }


}
