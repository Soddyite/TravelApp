package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.activity.StrategyContentStrategy;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentStrategyBean;

import java.util.List;

/**
 * Created by dllo on 16/6/2.
 */
public class StrategyContentStrategyAdapter extends BaseAdapter {

    Context context;
    List<StrategyContentStrategyBean> contentStrategyBeans;
    int[] imageId = {R.mipmap.strategy_content_1, R.mipmap.strategy_content_2,
            R.mipmap.strategy_content_3, R.mipmap.strategy_content_4,
            R.mipmap.strategy_content_5, R.mipmap.strategy_content_6,
            R.mipmap.strategy_content_7, R.mipmap.strategy_content_8,
            R.mipmap.strategy_content_9,};

    public StrategyContentStrategyAdapter(Context context) {
        this.context = context;
    }

    public void setContentStrategies(List<StrategyContentStrategyBean> contentStrategyBeans) {
        this.contentStrategyBeans = contentStrategyBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contentStrategyBeans == null ? 0 : contentStrategyBeans.size();
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

        StrategyViewHolder strategyViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_strategy_content_strategy_item, parent, false);
            strategyViewHolder = new StrategyViewHolder(convertView);
            convertView.setTag(strategyViewHolder);
        } else {
            strategyViewHolder = (StrategyViewHolder) convertView.getTag();
        }

        strategyViewHolder.imageView.setImageResource(imageId[position]);
        StrategyContentStrategyGridAdapter adapter = new StrategyContentStrategyGridAdapter(context);
        adapter.setPages(contentStrategyBeans.get(position).getPages());
        strategyViewHolder.gridView.setAdapter(adapter);


        return convertView;
    }

    class StrategyViewHolder {
        ImageView imageView;
        GridView gridView;

        public StrategyViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.activity_strategy_content_strategy_item_iv);
            gridView = (GridView) itemView.findViewById(R.id.activity_strategy_content_strategy_item_gridview);

        }
    }


}
