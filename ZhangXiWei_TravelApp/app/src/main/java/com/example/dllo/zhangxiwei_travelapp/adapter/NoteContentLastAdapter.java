package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBeanContent;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/13.
 */
public class NoteContentLastAdapter extends BaseAdapter {

    List<NoteBeanContent.TripDaysBean.NodesBean.NotesBean> notesBeanList;
    Context context;
    String siteName;


    public NoteContentLastAdapter(Context context) {
        this.context = context;
    }

    public void setNotesBeanList(List<NoteBeanContent.TripDaysBean.NodesBean.NotesBean> notesBeanList) {
        this.notesBeanList = notesBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return notesBeanList == null ? 0 : notesBeanList.size();
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

        NoteBeanContent.TripDaysBean.NodesBean.NotesBean notesBean = notesBeanList.get(position);

        if (notesBean.getPhoto() != null) {
            String imageUrl = notesBean.getPhoto().getUrl();

            Picasso.with(context).load(imageUrl).into(myViewHolder.backImage);
        }


        myViewHolder.bottomImage.setImageResource(R.mipmap.small_bottom_image);
        myViewHolder.content.setText(notesBean.getDescription());
        myViewHolder.bottomSiteName.setText(siteName);
        myViewHolder.bottomSupport.setText("13");
        myViewHolder.bottomMail.setText("5");

        return null;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    class MyViewHolder {

        ImageView backImage, bottomImage;
        TextView content, bottomSiteName;
        RadioButton bottomSupport, bottomMail;


        public MyViewHolder(View itemView) {


            backImage = (ImageView) itemView.findViewById(R.id.note_content_in_image);
            content = (TextView) itemView.findViewById(R.id.note_content_in_content);
            bottomImage = (ImageView) itemView.findViewById(R.id.note_content_in_bottom_image);
            bottomSiteName = (TextView) itemView.findViewById(R.id.note_content_in_bottom_site_name);
            bottomSupport = (RadioButton) itemView.findViewById(R.id.note_content_in_bottom_support);
            bottomMail = (RadioButton) itemView.findViewById(R.id.note_content_in_bottom_mail);


        }
    }


}
