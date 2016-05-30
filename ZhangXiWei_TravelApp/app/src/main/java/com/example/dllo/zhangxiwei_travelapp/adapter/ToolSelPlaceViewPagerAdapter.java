package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dllo on 16/5/19.
 * 工具页选择地点页的ViewPager适配器
 */
public class ToolSelPlaceViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] titles = {"国外", "国内"};

    public ToolSelPlaceViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }


    @Override
    public Fragment getItem(int position) {

        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}
