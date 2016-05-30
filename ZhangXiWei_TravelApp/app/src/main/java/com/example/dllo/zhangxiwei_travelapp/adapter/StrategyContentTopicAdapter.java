package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentTopicBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/21.
 * 攻略详情页专题页适配器
 */
public class StrategyContentTopicAdapter extends BaseAdapter {
    List<StrategyContentTopicBean> topicBeans;
    Context context;

    public StrategyContentTopicAdapter(Context context) {
        this.context = context;
    }

    public void setTopicBeans(List<StrategyContentTopicBean> topicBeans) {
        this.topicBeans = topicBeans;
        notifyDataSetChanged();
    }

    public void addTopicBeans(List<StrategyContentTopicBean> topicBeans){
        for (StrategyContentTopicBean topicBean : topicBeans) {
            this.topicBeans.add(topicBean);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return topicBeans == null ? 0 : topicBeans.size();
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

        TopicViewHolder topicViewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_strategy_content_topic_item,parent,false);
            topicViewHolder = new TopicViewHolder(convertView);
            convertView.setTag(topicViewHolder);
        }else {
            topicViewHolder = (TopicViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(topicBeans.get(position).getImage_url()).into(topicViewHolder.imageView);
        topicViewHolder.title.setText(topicBeans.get(position).getName());
        topicViewHolder.subtitle.setText(topicBeans.get(position).getTitle());


        return convertView;
    }

    class TopicViewHolder {
        ImageView imageView;
        TextView title,subtitle;


        public TopicViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.topic_item_imageview);
            title = (TextView) itemView.findViewById(R.id.topic_item_title);
            subtitle = (TextView) itemView.findViewById(R.id.topic_item_subtitle);

        }
    }

}
