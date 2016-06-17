package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.entity.CollectEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/31.
 */
public class UserActivityAdapter extends BaseAdapter {

    Context context;
    List<CollectEntity> collectEntities;

    public UserActivityAdapter(Context context) {
        this.context = context;
    }

    public void setCollectEntities(List<CollectEntity> collectEntities) {
        this.collectEntities = collectEntities;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return collectEntities == null ? 0 : collectEntities.size();
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

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_user_listview_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(collectEntities.get(position).getCollectImageUrl()).into(viewHolder.imageView);

        viewHolder.collectType.setText(collectEntities.get(position).getCollectType());
        viewHolder.collectTitle.setText(collectEntities.get(position).getCollectTitle());


        return convertView;
    }


    class ViewHolder {
        ImageView imageView;
        TextView collectType, collectTitle;

        public ViewHolder(View itemView) {

            imageView = (ImageView) itemView.findViewById(R.id.activity_user_listview_item_collectimage);
            collectType = (TextView) itemView.findViewById(R.id.activity_user_listview_item_collecttype);
            collectTitle = (TextView) itemView.findViewById(R.id.activity_user_listview_item_collecttitle);

        }
    }

}
