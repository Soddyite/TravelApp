package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
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
import com.example.dllo.zhangxiwei_travelapp.utils.MyListView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/12.
 */
public class NoteContentAnotherAdapter extends BaseAdapter {

    private List<NoteBeanContent.TripDaysBean.NodesBean> nodesBeanList;
    Context context;

    public NoteContentAnotherAdapter(Context context) {
        this.context = context;
    }

    public void setNodesBeanList(List<NoteBeanContent.TripDaysBean.NodesBean> nodesBeanList) {
        this.nodesBeanList = nodesBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return nodesBeanList == null ? 0 : nodesBeanList.size();
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

            convertView = LayoutInflater.from(context).inflate(R.layout.note_content_in_listview_item, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);

        } else {

            myViewHolder = (MyViewHolder) convertView.getTag();

        }

        myViewHolder.siteType.setImageResource(R.mipmap.site_type2);
        if (nodesBeanList.get(position).getEntry_name() != null) {
            myViewHolder.siteName.setText(nodesBeanList.get(position).getEntry_name().toString());

        }

        if (nodesBeanList.get(position).getNotes().size() > 0) {

            NoteBeanContent.TripDaysBean.NodesBean.NotesBean notesBean = nodesBeanList.get(position).getNotes().get(0);

            if (notesBean.getPhoto() != null) {
                String imageUrl = notesBean.getPhoto().getUrl();

                Picasso.with(context).load(imageUrl).into(myViewHolder.backImage);
            }

            myViewHolder.bottomImage.setImageResource(R.mipmap.small_bottom_image);
            myViewHolder.content.setText(notesBean.getDescription());
            if (nodesBeanList.get(position).getEntry_name() != null) {
                myViewHolder.bottomSiteName.setText(nodesBeanList.get(position).getEntry_name().toString());

            }
            myViewHolder.bottomSupport.setText("13");
            myViewHolder.bottomMail.setText("5");
        }

        return convertView;
    }

    class MyViewHolder {

        ImageView siteType;
        TextView siteName;
        ImageView backImage, bottomImage;
        TextView content, bottomSiteName;
        RadioButton bottomSupport, bottomMail;


        public MyViewHolder(View itemView) {


            siteType = (ImageView) itemView.findViewById(R.id.note_content_in_site_type);
            siteName = (TextView) itemView.findViewById(R.id.note_content_in_site_name);
            backImage = (ImageView) itemView.findViewById(R.id.note_content_in_image);
            content = (TextView) itemView.findViewById(R.id.note_content_in_content);
            bottomImage = (ImageView) itemView.findViewById(R.id.note_content_in_bottom_image);
            bottomSiteName = (TextView) itemView.findViewById(R.id.note_content_in_bottom_site_name);
            bottomSupport = (RadioButton) itemView.findViewById(R.id.note_content_in_bottom_support);
            bottomMail = (RadioButton) itemView.findViewById(R.id.note_content_in_bottom_mail);


        }
    }

}
