package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.dllo.zhangxiwei_travelapp.base.BaseFragment;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.ToolSelPlaceExListAdapter;
import com.example.dllo.zhangxiwei_travelapp.bean.ToolPlaceBean;

import java.util.List;

/**
 * Created by dllo on 16/5/19.
 * 工具页选择地点页的ViewPager的fragment
 */
public class ToolSelPlaceViewFragment extends BaseFragment implements ExpandableListView.OnChildClickListener {


    private ExpandableListView expandableListView;
    List<ToolPlaceBean.DestinationsBean> destinationsBeans;


    public void setDestinationsBeans(List<ToolPlaceBean.DestinationsBean> destinationsBeans) {
        this.destinationsBeans = destinationsBeans;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_tool_select_place_item;
    }

    @Override
    public void initView() {

        expandableListView = (ExpandableListView) getView().findViewById(R.id.tool_select_place_exlist);
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        expandableListView.setIndicatorBounds(width - 80, width - 20);

    }

    @Override
    public void initData() {

        ToolSelPlaceExListAdapter toolSelPlaceExListAdapter = new ToolSelPlaceExListAdapter(MyApplication.getContext());
        toolSelPlaceExListAdapter.setToolPlaceBeans(destinationsBeans);
        expandableListView.setAdapter(toolSelPlaceExListAdapter);

        expandableListView.setOnChildClickListener(this);

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        String placeName = destinationsBeans.get(groupPosition).getChildren().get(childPosition).getName_zh_cn();
        int placeId = destinationsBeans.get(groupPosition).getChildren().get(childPosition).getId();
        Intent intent = getActivity().getIntent();
        intent.putExtra("placeName", placeName);
        intent.putExtra("placeId", placeId);
        getActivity().setResult(2, intent);
        getActivity().finish();

        return true;
    }



}
