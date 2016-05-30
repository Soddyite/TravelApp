package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.ToolSelPlaceExListAdapter;
import com.example.dllo.zhangxiwei_travelapp.bean.ToolPlaceBean;

import java.util.List;

/**
 * Created by dllo on 16/5/19.
 * 工具页选择地点页的ViewPager的fragment
 */
public class ToolSelPlaceViewFragment extends Fragment implements ExpandableListView.OnChildClickListener {


    private ExpandableListView expandableListView;
    List<ToolPlaceBean.DestinationsBean> destinationsBeans;

    public ToolSelPlaceViewFragment() {
    }

    public ToolSelPlaceViewFragment(List<ToolPlaceBean.DestinationsBean> destinationsBeans) {
        this.destinationsBeans = destinationsBeans;
    }

    public void setDestinationsBeans(List<ToolPlaceBean.DestinationsBean> destinationsBeans) {
        this.destinationsBeans = destinationsBeans;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tool_select_place_item, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }


    private void initView() {

        expandableListView = (ExpandableListView) getView().findViewById(R.id.tool_select_place_exlist);
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        expandableListView.setIndicatorBounds(width - 80, width - 20);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
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
        intent.putExtra("placeName",placeName);
        intent.putExtra("placeId", placeId);
        getActivity().setResult(2, intent);
        getActivity().finish();

        return true;
    }
}
