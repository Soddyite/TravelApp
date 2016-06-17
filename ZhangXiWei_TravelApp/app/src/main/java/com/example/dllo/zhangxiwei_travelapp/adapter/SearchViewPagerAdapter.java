package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/6/1.
 */
public class SearchViewPagerAdapter extends FragmentPagerAdapter {

    Context context;
    List<Fragment> fragments;
    String[] titleNames = {"国外", "国内", "四季"};


    public SearchViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleNames[position];
    }


}
