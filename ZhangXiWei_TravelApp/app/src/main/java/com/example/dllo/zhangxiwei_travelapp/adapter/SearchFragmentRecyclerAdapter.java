package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.SearchBean;
import com.example.dllo.zhangxiwei_travelapp.bean.SearchSingleBean;
import com.example.dllo.zhangxiwei_travelapp.myinterface.SearchClickListener;

import java.util.List;

/**
 * Created by dllo on 16/6/1.
 */
public class SearchFragmentRecyclerAdapter extends RecyclerView.Adapter<SearchFragmentRecyclerAdapter.SearchViewHolder> {

    Context context;
    List<SearchSingleBean> searchSingleBeans;

    SearchClickListener searchClickListener;


    public void setSearchClickListener(SearchClickListener searchClickListener) {
        this.searchClickListener = searchClickListener;
    }


    public SearchFragmentRecyclerAdapter(Context context) {
        this.context = context;

    }

    public void setSearchSingleBeans(List<SearchSingleBean> searchSingleBeans) {
        this.searchSingleBeans = searchSingleBeans;
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_search_recyclerview_item, parent, false);
        SearchViewHolder viewHolder = new SearchViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder, int position) {

        holder.textView.setText(searchSingleBeans.get(position).getName());
        holder.textView.setTextColor(Color.BLACK);

        if (searchClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    searchClickListener.mySearchOnClick(pos);
                }
            });
        }

    }

    @Override
    public int getItemCount() {

        return searchSingleBeans == null ? 0 : searchSingleBeans.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.fragment_search_recyclerview_item_tv);
        }
    }
}
