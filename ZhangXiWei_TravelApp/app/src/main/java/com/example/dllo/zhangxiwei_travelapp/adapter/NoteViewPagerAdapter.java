package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class NoteViewPagerAdapter extends PagerAdapter {

    private List<View> views;

    public NoteViewPagerAdapter() {
    }

    public void setViews(List<View> views) {
        this.views = views;

    }

    @Override
    public int getCount() {
        return views == null ? 0 : views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = views.get(position);
        container.removeView(v);
    }


}
