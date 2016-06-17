package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBeanContent;
import com.example.dllo.zhangxiwei_travelapp.utils.MyImageView;
import com.example.dllo.zhangxiwei_travelapp.utils.MyListView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/12.
 * 游记详情页的内层listview适配器
 */
public class NoteContentAnotherAdapter extends BaseAdapter {

    private List<NoteBeanContent.TripDaysBean.NodesBean.NotesBean> notesBeans;

    Context context;
    private String placeName;

    public NoteContentAnotherAdapter(Context context) {
        this.context = context;
    }

    public void setNodesBeanList(List<NoteBeanContent.TripDaysBean.NodesBean.NotesBean> notesBeans) {
        this.notesBeans = notesBeans;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return notesBeans == null ? 0 : notesBeans.size();
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

        MyViewHolder myViewHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.note_content_last_listview_item, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);

        } else {

            myViewHolder = (MyViewHolder) convertView.getTag();

        }

        NoteBeanContent.TripDaysBean.NodesBean.NotesBean notesBean = notesBeans.get(position);

        if (notesBean.getPhoto() != null && notesBean.getPhoto().getUrl() != null) {
            String imageUrl = notesBean.getPhoto().getUrl();
//            Picasso.with(context).load(imageUrl).into(myViewHolder.backImage);
            Picasso.with(context).load(imageUrl).resize(notesBean.getPhoto().getImage_width() / 4,
                    notesBean.getPhoto().getImage_height() / 4).centerInside().into(myViewHolder.backImage);
        } else {
            myViewHolder.backImage.setVisibility(View.GONE);
        }

        myViewHolder.content.setText(notesBean.getDescription());

        if (placeName != null && !(placeName.equals(""))) {

            myViewHolder.bottomImage.setImageResource(R.mipmap.small_bottom_image);
            myViewHolder.bottomSiteName.setText(placeName);
        } else {
            myViewHolder.bottomImage.setVisibility(View.GONE);
        }

        myViewHolder.bottomSupport.setText("13");
        myViewHolder.bottomMail.setText("5");

        return convertView;
    }

    public void setPlaceName(String s) {
        placeName = s;
    }

    class MyViewHolder {

        MyImageView backImage;
        ImageView bottomImage;
        TextView content, bottomSiteName;
        RadioButton bottomSupport, bottomMail;

        public MyViewHolder(View itemView) {

            backImage = (MyImageView) itemView.findViewById(R.id.note_content_in_image);
            content = (TextView) itemView.findViewById(R.id.note_content_in_content);
            bottomImage = (ImageView) itemView.findViewById(R.id.note_content_in_bottom_image);
            bottomSiteName = (TextView) itemView.findViewById(R.id.note_content_in_bottom_site_name);
            bottomSupport = (RadioButton) itemView.findViewById(R.id.note_content_in_bottom_support);
            bottomMail = (RadioButton) itemView.findViewById(R.id.note_content_in_bottom_mail);


            List<String> names = new ArrayList<>();

        }
    }
}
