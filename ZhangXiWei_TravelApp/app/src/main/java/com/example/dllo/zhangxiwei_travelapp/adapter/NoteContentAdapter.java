package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBeanContent;
import com.example.dllo.zhangxiwei_travelapp.utils.MyListView;

import java.util.List;

/**
 * Created by dllo on 16/5/12.
 */
public class NoteContentAdapter extends BaseAdapter {

    private List<NoteBeanContent.TripDaysBean> tripDaysBeans;
    Context context;

    public NoteContentAdapter(Context context) {
        this.context = context;

    }

    public void setNoteBeanContent(List<NoteBeanContent.TripDaysBean> tripDaysBeansripDaysBean) {
        this.tripDaysBeans = tripDaysBeansripDaysBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tripDaysBeans == null ? 0 : tripDaysBeans.size();
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

            convertView = LayoutInflater.from(context).inflate(R
                    .layout.note_content_out_listview_item, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);

        } else {

            myViewHolder = (MyViewHolder) convertView.getTag();

        }


        NoteBeanContent.TripDaysBean tripDaysBean = tripDaysBeans.get(position);

        myViewHolder.whichDay.setText("DAY " + tripDaysBean.getDay() + " ");
        myViewHolder.date.setText(tripDaysBean.getTrip_date() + " ");
        myViewHolder.week.setText("星期几");


        NoteContentAnotherAdapter noteContentAnotherAdapter = new NoteContentAnotherAdapter(context);
        noteContentAnotherAdapter.setNodesBeanList(tripDaysBeans.get(position).getNodes());
        myViewHolder.listView.setAdapter(noteContentAnotherAdapter);


        return convertView;
    }


    class MyViewHolder {

        TextView whichDay, date, week;
        MyListView listView;

        public MyViewHolder(View itemView) {

            listView = (MyListView) itemView.findViewById(R.id.note_content_out_listview);
            whichDay = (TextView) itemView.findViewById(R.id.note_content_out_which_day);
            date = (TextView) itemView.findViewById(R.id.note_content_out_date);
            week = (TextView) itemView.findViewById(R.id.note_content_week);

        }
    }


}
