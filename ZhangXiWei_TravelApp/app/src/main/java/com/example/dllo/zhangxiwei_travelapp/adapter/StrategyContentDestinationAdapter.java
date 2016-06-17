package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentDestinationBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/21.
 *攻略详情页旅行地页适配器
 */
public class StrategyContentDestinationAdapter extends BaseAdapter {

    Context context;
    List<StrategyContentDestinationBean> destinationBeans;

    public StrategyContentDestinationAdapter(Context context) {
        this.context = context;
    }

    public void setDestinationBeans(List<StrategyContentDestinationBean> destinationBeans) {
        this.destinationBeans = destinationBeans;
        notifyDataSetChanged();
    }

    public void addDestinationBeans(List<StrategyContentDestinationBean> destinationBeans) {
        for (StrategyContentDestinationBean destinationBean : destinationBeans) {
            this.destinationBeans.add(destinationBean);
        }


    }

    @Override
    public int getCount() {
        return destinationBeans == null ? 0 : destinationBeans.size();
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

        DestinationViewHolder destinationViewHolder = null;


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_strategy_content_destination_item, parent, false);
            destinationViewHolder = new DestinationViewHolder(convertView);
            convertView.setTag(destinationViewHolder);
        } else {
            destinationViewHolder = (DestinationViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(destinationBeans.get(position).getImage_url()).into(destinationViewHolder.imageView);

        destinationViewHolder.strategyCount.setText(destinationBeans.get(position).getAttraction_trips_count() + "篇游记");
        destinationViewHolder.strategyCount.setTextColor(Color.WHITE);
        destinationViewHolder.placeName.setText(destinationBeans.get(position).getName());
        destinationViewHolder.placeName.setTextColor(Color.BLACK);
        destinationViewHolder.grade.setText("分数: " + destinationBeans.get(position).getUser_score());
        destinationViewHolder.grade.setTextColor(Color.BLACK);
        destinationViewHolder.content.setText(destinationBeans.get(position).getDescription());
        destinationViewHolder.content.setTextColor(Color.BLACK);

        return convertView;


    }

    class DestinationViewHolder {

        ImageView imageView;
        TextView strategyCount, placeName, grade, content;

        public DestinationViewHolder(View itemView) {

            imageView = (ImageView) itemView.findViewById(R.id.destination_item_imageview);
            strategyCount = (TextView) itemView.findViewById(R.id.destination_item_strategy_count);
            placeName = (TextView) itemView.findViewById(R.id.destination_item_placename);
            grade = (TextView) itemView.findViewById(R.id.destination_item_grade);
            content = (TextView) itemView.findViewById(R.id.destination_item_content);


        }
    }


}
