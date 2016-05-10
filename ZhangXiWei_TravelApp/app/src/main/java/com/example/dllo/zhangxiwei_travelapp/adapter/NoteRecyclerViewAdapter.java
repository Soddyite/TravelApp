package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBean;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/10.
 */
public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder> {

    Context context;

    List<NoteBean> noteBeans = new ArrayList<>();

    public NoteRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setNoteBeans(List<NoteBean> noteBeans) {
        this.noteBeans.addAll(noteBeans);
        notifyDataSetChanged();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_note_recyclerview_item, parent, false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(view);

        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        NoteBean noteBean = noteBeans.get(position);
        String imageUrl = noteBean.getFront_cover_photo_url();

//        try {
//            InputStream is = (InputStream) new URL(imageUrl).getContent();
//            Bitmap bitmap = BitmapFactory.decodeStream(is);
//            holder.imageView.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        holder.imageView.setImageResource(R.mipmap.ic_launcher);

        holder.name.setText(noteBean.getName());
        holder.startDate.setText(noteBean.getStart_date() + " / ");
        holder.days.setText(noteBean.getDays() + " , ");
        holder.photoCount.setText(noteBean.getPhotos_count()+"");

    }

    @Override
    public int getItemCount() {
        return noteBeans == null ? 0 : noteBeans.size();
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, startDate, days, photoCount;

        public NoteViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.fragment_note_recycler_image);
            name = (TextView) itemView.findViewById(R.id.fragment_note_recycler_name);
            startDate = (TextView) itemView.findViewById(R.id.fragment_note_recycler_startdate);
            days = (TextView) itemView.findViewById(R.id.fragment_note_recycler_days);
            photoCount = (TextView) itemView.findViewById(R.id.fragment_note_recycler_photos_count);
        }
    }
}
