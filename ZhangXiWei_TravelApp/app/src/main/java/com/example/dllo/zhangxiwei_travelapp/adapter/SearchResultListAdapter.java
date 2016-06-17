package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.SearchResultBean;
import com.example.dllo.zhangxiwei_travelapp.myinterface.SearchClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/6/1.
 */
public class SearchResultListAdapter extends BaseAdapter {

    Context context;
    List<SearchResultBean> searchResultBeans;


    public SearchResultListAdapter(Context context) {
        this.context = context;
    }

    public void setSearchResultBeans(List<SearchResultBean> searchResultBeans) {
        this.searchResultBeans = searchResultBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return searchResultBeans == null ? 0 : searchResultBeans.size();
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


        SearchResultViewHolder searchResultViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_search_result_item, parent, false);
            searchResultViewHolder = new SearchResultViewHolder(convertView);
            convertView.setTag(searchResultViewHolder);
        } else {
            searchResultViewHolder = (SearchResultViewHolder) convertView.getTag();
        }


        Picasso.with(context).load(searchResultBeans.get(position).getFront_cover_photo_url()).into(searchResultViewHolder.backImage);

        Picasso.with(context).load(searchResultBeans.get(position).getUser().getImage()).into(searchResultViewHolder.headImage);

        searchResultViewHolder.name.setText(searchResultBeans.get(position).getName());
        searchResultViewHolder.date.setText(searchResultBeans.get(position).getStart_date());
        searchResultViewHolder.days.setText(searchResultBeans.get(position).getDays() + "天,");
        searchResultViewHolder.count.setText(searchResultBeans.get(position).getComments_count() + "图");


        return convertView;
    }


    class SearchResultViewHolder {

        ImageView backImage, headImage;
        TextView name, date, days, count;

        public SearchResultViewHolder(View itemView) {
            backImage = (ImageView) itemView.findViewById(R.id.activity_search_result_title_backimage);
            headImage = (ImageView) itemView.findViewById(R.id.activity_search_result_title_headimage);
            name = (TextView) itemView.findViewById(R.id.activity_search_result_title_name);
            date = (TextView) itemView.findViewById(R.id.activity_search_result_title_date);
            days = (TextView) itemView.findViewById(R.id.activity_search_result_title_days);
            count = (TextView) itemView.findViewById(R.id.activity_search_result_title_count);


        }
    }


}
