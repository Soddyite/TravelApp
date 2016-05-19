package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyBean;
import com.example.dllo.zhangxiwei_travelapp.myinterface.StrategyClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/18.
 */
public class StrategyRecyclerAdapter extends RecyclerView.Adapter<StrategyRecyclerAdapter.StrategyRecyclerViewHolder> {

    Context context;
    StrategyBean strategyBean;
    StrategyClickListener clickListener;

    public StrategyRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setStrategyBean(StrategyBean strategyBean) {
        this.strategyBean = strategyBean;
        notifyDataSetChanged();
    }

    @Override
    public StrategyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_strategy_recyclerview_item, parent, false);
        StrategyRecyclerViewHolder viewHolder = new StrategyRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final StrategyRecyclerViewHolder holder, int position) {

        holder.placeNameZh.setText(strategyBean.getDestinations().get(position).getName_zh_cn());
        holder.placeNameEn.setText(strategyBean.getDestinations().get(position).getName_en());
        holder.poiCount.setText(strategyBean.getDestinations().get(position).getPoi_count() + " 旅行地");
        Picasso.with(context).load(strategyBean.getDestinations().get(position).getImage_url()).into(holder.imageView);

        if (clickListener != null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    clickListener.MyStrategyOnClick(pos);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return strategyBean == null ? 0 : strategyBean.getDestinations().size();
    }


    public void setStrategyClickListener(StrategyClickListener clickListener) {
        this.clickListener = clickListener;
    }


    class StrategyRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView placeNameZh, placeNameEn, poiCount;
        ImageView imageView;

        public StrategyRecyclerViewHolder(View itemView) {
            super(itemView);
            placeNameZh = (TextView) itemView.findViewById(R.id.strategy_place_chinesename);
            placeNameEn = (TextView) itemView.findViewById(R.id.strategy_place_englishname);
            poiCount = (TextView) itemView.findViewById(R.id.strategy_poi_count);
            imageView = (ImageView) itemView.findViewById(R.id.strategy_back_image);
        }
    }


}
