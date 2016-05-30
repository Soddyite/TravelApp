package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/10.
 * 游记也的listview适配器
 */
public class NoteListViewAdapter extends BaseAdapter {

    Context context;
    List<NoteBean> noteBeans = new ArrayList<>();



    public NoteListViewAdapter(Context context) {
        this.context = context;
    }

    public void setNoteBeans(List<NoteBean> noteBeans) {
        this.noteBeans.addAll(noteBeans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return noteBeans == null ? 0 : noteBeans.size();
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

        NoteViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_note_listview_item, parent, false);

            viewHolder = new NoteViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (NoteViewHolder) convertView.getTag();
        }


        NoteBean noteBean = noteBeans.get(position);
        String imageUrl = noteBean.getFront_cover_photo_url();


        Picasso.with(context).load(imageUrl).into(viewHolder.imageView);

        viewHolder.name.setText(noteBean.getName());
        viewHolder.startDate.setText(noteBean.getStart_date() + " / ");
        viewHolder.days.setText(noteBean.getDays() + " , ");
        viewHolder.photoCount.setText(noteBean.getPhotos_count() + "");

        return convertView;
    }

    public void addNoteBeans(List<NoteBean> myNoteBeans) {
        for (NoteBean myNoteBean : myNoteBeans) {
            noteBeans.add(myNoteBean);
        }
        notifyDataSetChanged();

    }


    class NoteViewHolder {
        ImageView imageView;
        TextView name, startDate, days, photoCount;

        public NoteViewHolder(View itemView) {

            imageView = (ImageView) itemView.findViewById(R.id.fragment_note_recycler_image);
            name = (TextView) itemView.findViewById(R.id.fragment_note_recycler_name);
            startDate = (TextView) itemView.findViewById(R.id.fragment_note_recycler_startdate);
            days = (TextView) itemView.findViewById(R.id.fragment_note_recycler_days);
            photoCount = (TextView) itemView.findViewById(R.id.fragment_note_recycler_photos_count);
            name.setTextColor(Color.WHITE);
            startDate.setTextColor(Color.WHITE);
            days.setTextColor(Color.WHITE);
            photoCount.setTextColor(Color.WHITE);

        }
    }
}
