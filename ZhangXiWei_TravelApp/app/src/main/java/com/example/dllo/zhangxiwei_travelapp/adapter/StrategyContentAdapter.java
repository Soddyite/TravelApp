package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.activity.StrategyContentDestination;
import com.example.dllo.zhangxiwei_travelapp.activity.StrategyContentStrategy;
import com.example.dllo.zhangxiwei_travelapp.activity.StrategyContentTopic;
import com.example.dllo.zhangxiwei_travelapp.activity.StrategyContentTravel;
import com.example.dllo.zhangxiwei_travelapp.bean.StrategyContentBean;
import com.example.dllo.zhangxiwei_travelapp.myinterface.StrategyClickListener;
import com.example.dllo.zhangxiwei_travelapp.myinterface.StrategyContentClickListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by dllo on 16/5/21.
 * 攻略详情页适配器
 */
public class StrategyContentAdapter extends BaseAdapter {

    List<StrategyContentBean> contentBeans;
    Context context;
    StrategyContentClickListener strategyClickListener;

    public StrategyContentAdapter(Context context) {

        this.context = context;
    }

    public void setContentBeans(List<StrategyContentBean> contentBeans) {
        this.contentBeans = contentBeans;
        notifyDataSetChanged();
    }

    public void setStrategyClickListener(StrategyContentClickListener strategyClickListener) {
        this.strategyClickListener = strategyClickListener;
    }

    @Override
    public int getCount() {
        return contentBeans == null ? 0 : contentBeans.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.strategy_content_listview_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        Picasso.with(context).load(contentBeans.get(position).getImage_url()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    viewHolder.relativeLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
                } else {
                    viewHolder.relativeLayout.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                }


            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }

        });


        viewHolder.nameChina.setText(contentBeans.get(position).getName_zh_cn());
        viewHolder.nameEnglish.setText(contentBeans.get(position).getName_en());

        viewHolder.radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strategyClickListener.MyStrategyContentOnClick(contentBeans.get(position).getId(), contentBeans.get(position).getName_zh_cn(), v.getId());
            }
        });

        viewHolder.radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strategyClickListener.MyStrategyContentOnClick(contentBeans.get(position).getId(), contentBeans.get(position).getName_zh_cn(), v.getId());
            }
        });

        viewHolder.radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strategyClickListener.MyStrategyContentOnClick(contentBeans.get(position).getId(), contentBeans.get(position).getName_zh_cn(), v.getId());
            }
        });

        viewHolder.radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strategyClickListener.MyStrategyContentOnClick(contentBeans.get(position).getId(), contentBeans.get(position).getName_zh_cn(), v.getId());
            }
        });

        return convertView;
    }


    class ViewHolder {

        RelativeLayout relativeLayout;
        TextView nameChina, nameEnglish;
        RadioButton radioButton1, radioButton2, radioButton3, radioButton4;


        public ViewHolder(View itemView) {

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.strategy_content_place_relativelayout);
            nameChina = (TextView) itemView.findViewById(R.id.strategy_content_place_chinaname);
            nameEnglish = (TextView) itemView.findViewById(R.id.strategy_content_place_englishname);
            radioButton1 = (RadioButton) itemView.findViewById(R.id.strategy_content_radiobutton_strategy);
            radioButton2 = (RadioButton) itemView.findViewById(R.id.strategy_content_radiobutton_travel);
            radioButton3 = (RadioButton) itemView.findViewById(R.id.strategy_content_radiobutton_destination);
            radioButton4 = (RadioButton) itemView.findViewById(R.id.strategy_content_radiobutton_topic);

        }
    }


}
