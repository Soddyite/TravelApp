package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.ToolPlaceBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/5/18.
 * 工具页选择地点页的expandablelistview适配器
 */
public class ToolSelPlaceExListAdapter extends BaseExpandableListAdapter {
    Context context;
    List<ToolPlaceBean.DestinationsBean> destinationsBeans;
    List<String> parent;
    Map<String, List<String>> map;

    public ToolSelPlaceExListAdapter(Context context) {
        this.context = context;
        parent = new ArrayList<>();
        map = new HashMap<>();

    }


    public void setToolPlaceBeans(List<ToolPlaceBean.DestinationsBean> destinationsBeans) {
        this.destinationsBeans = destinationsBeans;
        for (ToolPlaceBean.DestinationsBean destinationsBean : destinationsBeans) {

            String key = destinationsBean.getName_zh_cn();
            parent.add(key);
            List<String> list = new ArrayList<>();
            for (ToolPlaceBean.DestinationsBean.ChildrenBean childrenBean : destinationsBean.getChildren()) {
                list.add(childrenBean.getName_zh_cn());
            }
            map.put(key, list);
        }
        notifyDataSetChanged();
    }


    @Override
    public int getGroupCount() {
        return parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(parent.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return map.get(parent.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tool_selplace_exlist_parent, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.tool_selplace_exlist_parent_tv);

        tv.setText(this.parent.get(groupPosition));
        tv.setTextColor(Color.GRAY);


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tool_selplace_exlist_child, parent, false);

        }
        TextView tv = (TextView) convertView.findViewById(R.id.tool_selplace_exlist_child_tv);
        tv.setText(map.get(this.parent.get(groupPosition)).get(childPosition));
        tv.setTextColor(Color.GRAY);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
