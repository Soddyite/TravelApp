package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentStrategyBean;

import java.util.List;

/**
 * Created by dllo on 16/6/2.
 */
public class StrategyContentStrategyGridAdapter extends BaseAdapter {


    Context context;
    List<StrategyContentStrategyBean.Pages> pages;

    public StrategyContentStrategyGridAdapter(Context context) {
        this.context = context;
    }

    public void setPages(List<StrategyContentStrategyBean.Pages> pages) {
        this.pages = pages;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pages == null ? 0 : pages.size();
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
        GridViewHolder gridViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_strategy_content_strategy_grid_item, parent, false);
            gridViewHolder = new GridViewHolder(convertView);
            convertView.setTag(gridViewHolder);
        } else {
            gridViewHolder = (GridViewHolder) convertView.getTag();
        }

        gridViewHolder.textView.setText(pages.get(position).getTitle());


        return convertView;
    }

    class GridViewHolder {
        TextView textView;

        public GridViewHolder(View itemView) {

            textView = (TextView) itemView.findViewById(R.id.activity_strategy_content_strategy_grid_item_tv);
        }
    }


}
