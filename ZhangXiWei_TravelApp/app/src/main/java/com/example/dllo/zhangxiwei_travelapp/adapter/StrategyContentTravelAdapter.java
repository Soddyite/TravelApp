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
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentTravelBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/21.
 * 攻略详情页行程页适配器
 */
public class StrategyContentTravelAdapter extends BaseAdapter {
    Context context;
    List<StrategyContentTravelBean> contentTravelBeansbeans;

    public StrategyContentTravelAdapter(Context context) {
        this.context = context;
    }

    public void setContentTravelBeansbeans(List<StrategyContentTravelBean> contentTravelBeansbeans) {
        this.contentTravelBeansbeans = contentTravelBeansbeans;
        notifyDataSetChanged();
    }

    public void addContentTravelBeansbeans(List<StrategyContentTravelBean> contentTravelBeansbeans){
        for (StrategyContentTravelBean contentTravelBeansbean : contentTravelBeansbeans) {
            this.contentTravelBeansbeans.add(contentTravelBeansbean);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contentTravelBeansbeans == null ? 0 : contentTravelBeansbeans.size();
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
        TravelViewHolder travelViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_strategy_content_travel_item, parent, false);
            travelViewHolder = new TravelViewHolder(convertView);
            convertView.setTag(travelViewHolder);
        } else {
            travelViewHolder = (TravelViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(contentTravelBeansbeans.get(position).getImage_url()).into(travelViewHolder.imageView);

        travelViewHolder.name.setText(contentTravelBeansbeans.get(position).getName());
        travelViewHolder.name.setTextColor(Color.WHITE);
        travelViewHolder.content.setText(contentTravelBeansbeans.get(position).getDescription());
        travelViewHolder.days.setText(contentTravelBeansbeans.get(position).getPlan_days_count() + "天");
        travelViewHolder.days.setTextColor(Color.WHITE);
        travelViewHolder.placeCount.setText(contentTravelBeansbeans.get(position).getPlan_nodes_count() + "个旅行地");
        travelViewHolder.placeCount.setTextColor(Color.WHITE);

        return convertView;
    }


    class TravelViewHolder {

        ImageView imageView;
        TextView days, placeCount, name, content;

        public TravelViewHolder(View itemView) {

            imageView = (ImageView) itemView.findViewById(R.id.travel_item_imageview);
            days = (TextView) itemView.findViewById(R.id.travel_item_daystv);
            placeCount = (TextView) itemView.findViewById(R.id.travel_item_placestv);
            name = (TextView) itemView.findViewById(R.id.travel_item_nametv);
            content = (TextView) itemView.findViewById(R.id.travel_item_content_tv);


        }
    }


}
