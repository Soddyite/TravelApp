package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyBean;
import com.example.dllo.zhangxiwei_travelapp.myinterface.StrategyClickListener;

import java.util.List;

/**
 * Created by dllo on 16/5/16.
 * 攻略页外层listview适配器
 */
public class StrategyListViewAdapter extends BaseAdapter {

    Context context;
    List<StrategyBean> strategyBeans;
    String[] names = {"国外-亚洲", "国外-欧洲", "美洲,大洋洲,非洲与南极洲", "国内-港澳台", "国内-大陆"};
    private StrategyRecyclerAdapter recyclerAdapter;
    private StrateListViewHolder strateListViewHolder;
    private StrategyClickListener clickListener;

    public StrategyListViewAdapter(Context context) {
        this.context = context;
    }

    public void setStrategyBeans(List<StrategyBean> strategyBeans) {
        this.strategyBeans = strategyBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return strategyBeans == null ? 0 : strategyBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        strateListViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_strategy_listview_item, parent, false);
            strateListViewHolder = new StrateListViewHolder(convertView);
            convertView.setTag(strateListViewHolder);
        } else {
            strateListViewHolder = (StrateListViewHolder) convertView.getTag();
        }

        strateListViewHolder.textView.setText(names[position]);
        recyclerAdapter = new StrategyRecyclerAdapter(context);
        recyclerAdapter.setStrategyBean(strategyBeans.get(position));
        strateListViewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        strateListViewHolder.recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setStrategyClickListener(clickListener);
        return convertView;
    }

    public void onClick(StrategyClickListener clickListener) {
        this.clickListener = clickListener;
        Log.d("StrategyListViewAdapter", "clickListener==null:" + (clickListener == null));
    }

    class StrateListViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public StrateListViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.strategy_area_name);
            recyclerView = (RecyclerView) view.findViewById(R.id.fragment_strategy_recyclerview);
        }
    }


}
