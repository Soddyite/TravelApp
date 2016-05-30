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
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBeanContent;
import com.example.dllo.zhangxiwei_travelapp.utils.MyListView;
import com.example.dllo.zhangxiwei_travelapp.utils.ItemHeightUtility;
import com.example.dllo.zhangxiwei_travelapp.utils.WeekUtility;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/12.
 * 游记详细信息页的外层expandablelistview的适配器
 */
public class NoteContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter {


    int day = 1;
    String date = null;

    Context context;
    private List<NoteBeanContent.TripDaysBean.NodesBean> nodesBeans = new ArrayList<>();


    public NoteContentAdapter(Context context) {
        this.context = context;

    }

    public void setNoteBeanContent(List<NoteBeanContent.TripDaysBean> tripDaysBeans) {


        for (NoteBeanContent.TripDaysBean tripDaysBean : tripDaysBeans) {
            if (tripDaysBean.getDay() > 0) {
                day = tripDaysBean.getDay();
            }
            if (tripDaysBean.getTrip_date() != null) {
                date = tripDaysBean.getTrip_date();
            }

            for (NoteBeanContent.TripDaysBean.NodesBean nodesBean : tripDaysBean.getNodes()) {
                nodesBean.setDay(day);
                nodesBean.setDate(date);
                Log.d("NoteContentAdapter", "day:" + day);
            }


        }


        for (NoteBeanContent.TripDaysBean tripDaysBean : tripDaysBeans) {
            for (int i = 0; i < tripDaysBean.getNodes().size(); i++) {
                nodesBeans.add(tripDaysBean.getNodes().get(i));
            }
        }


        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_content_in_listview_item, parent, false);
        NoteContentViewHolder viewHolder = new NoteContentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NoteContentViewHolder viewHolder = (NoteContentViewHolder) holder;

        viewHolder.imageView.setImageResource(R.mipmap.site_type2);


        NoteContentAnotherAdapter anotherAdapter = new NoteContentAnotherAdapter(context);


        if (nodesBeans.get(position).getEntry_name() != null) {

            viewHolder.textView.setText(nodesBeans.get(position).getEntry_name().toString());
        }


        if (nodesBeans.get(position).getEntry_name() != null) {

            anotherAdapter.setPlaceName(nodesBeans.get(position).getEntry_name().toString());
        }
        anotherAdapter.setNodesBeanList(nodesBeans.get(position).getNotes());
        viewHolder.myListView.setAdapter(anotherAdapter);
        ItemHeightUtility.setListViewHeightBasedOnChildren(viewHolder.myListView);


    }

    @Override
    public long getHeaderId(int position) {

        return nodesBeans.get(position).getDay();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.note_content_out_listview_item, parent, false);
        NoteContentHeaderViewHolder headerViewHolder = new NoteContentHeaderViewHolder(view);
        return headerViewHolder;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.d("NoteContentAdapter", "position:" + position);
        NoteContentHeaderViewHolder viewHolder = (NoteContentHeaderViewHolder) holder;

        NoteBeanContent.TripDaysBean.NodesBean nodesBean = nodesBeans.get(position);
        viewHolder.whichDay.setText("DAY " + nodesBean.getDay() + " ");
        if (nodesBean.getDate() != null) {

            viewHolder.date.setText(nodesBean.getDate() + " ");
            viewHolder.week.setText(WeekUtility.getWeek(nodesBean.getDate()));

        } else {
            viewHolder.date.setText("");
            viewHolder.week.setText("");
        }



    }

    @Override
    public int getItemCount() {

        return nodesBeans == null ? 0 : nodesBeans.size();


    }


    class NoteContentViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        MyListView myListView;

        public NoteContentViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.note_content_in_site_type);
            textView = (TextView) itemView.findViewById(R.id.note_content_in_site_name);
            myListView = (MyListView) itemView.findViewById(R.id.note_content_in_listview);
        }
    }

    class NoteContentHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView whichDay;
        TextView date;
        TextView week;

        public NoteContentHeaderViewHolder(View itemView) {
            super(itemView);

            whichDay = (TextView) itemView.findViewById(R.id.note_content_out_which_day);
            date = (TextView) itemView.findViewById(R.id.note_content_out_date);
            week = (TextView) itemView.findViewById(R.id.note_content_out_week);
        }
    }


//    @Override
//    public int getGroupCount() {
//        return tripDaysBeans == null ? 0 : tripDaysBeans.size();
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return tripDaysBeans == null ? 0 : tripDaysBeans.get(groupPosition).getNodes().size();
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return tripDaysBeans.get(groupPosition);
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return tripDaysBeans.get(groupPosition).getNodes().get(childPosition);
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        GroupViewHolder viewHolder = null;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.note_content_out_listview_item, null);
//            viewHolder = new GroupViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (GroupViewHolder) convertView.getTag();
//        }
//
//
//        NoteBeanContent.TripDaysBean tripDaysBean = tripDaysBeans.get(groupPosition);
//        viewHolder.whichDay.setText("DAY " + tripDaysBean.getDay() + " ");
//        viewHolder.date.setText(tripDaysBean.getTrip_date() + " ");
//        viewHolder.week.setText("星期几");
//
//        return convertView;
//
//    }
//
//    class GroupViewHolder {
//        TextView whichDay;
//        TextView date;
//        TextView week;
//
//
//        public GroupViewHolder(View itemView) {
//            whichDay = (TextView) itemView.findViewById(R.id.note_content_out_which_day);
//            date = (TextView) itemView.findViewById(R.id.note_content_out_date);
//            week = (TextView) itemView.findViewById(R.id.note_content_out_week);
//
//        }
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//
//        ChildViewHolder childViewHolder = null;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.note_content_in_listview_item, parent, false);
//            childViewHolder = new ChildViewHolder(convertView);
//            convertView.setTag(childViewHolder);
//        } else {
//            childViewHolder = (ChildViewHolder) convertView.getTag();
//        }
//
//        if (tripDaysBeans.get(groupPosition).getNodes().get(childPosition).getEntry_name() != null) {
//
//            childViewHolder.imageView.setImageResource(R.mipmap.site_type2);
//
//            childViewHolder.textView.setText(tripDaysBeans.get(groupPosition).getNodes().get(childPosition).getEntry_name().toString());
//
//        }
//
//        NoteContentAnotherAdapter anotherAdapter = new NoteContentAnotherAdapter(context);
//
//        if (tripDaysBeans.get(groupPosition).getNodes().get(childPosition).getEntry_name() != null) {
//            anotherAdapter.setPlaceName(tripDaysBeans.get(groupPosition).getNodes().get(childPosition).getEntry_name().toString());
//        }
//
//        anotherAdapter.setNodesBeanList(tripDaysBeans.get(groupPosition).getNodes().get(childPosition).getNotes());
//        childViewHolder.myListView.setAdapter(anotherAdapter);
//
//        return convertView;
//    }
//
//
//    class ChildViewHolder {
//        ImageView imageView;
//        TextView textView;
//        MyListView myListView;
//
//        public ChildViewHolder(View itemView) {
//
//            imageView = (ImageView) itemView.findViewById(R.id.note_content_in_site_type);
//            textView = (TextView) itemView.findViewById(R.id.note_content_in_site_name);
//            myListView = (MyListView) itemView.findViewById(R.id.note_content_in_listview);
//        }
//
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }

    public interface StickyRecyclerHeadersAdapter<VH extends RecyclerView.ViewHolder> {
        public long getHeaderId(int position);

        public VH onCreateHeaderViewHolder(ViewGroup parent);

        public void onBindHeaderViewHolder(VH holder, int position);

        public int getItemCount();
    }

}
