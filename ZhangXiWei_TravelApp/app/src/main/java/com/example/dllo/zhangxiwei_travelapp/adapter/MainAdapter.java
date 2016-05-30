package com.example.dllo.zhangxiwei_travelapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 * 主页ViewPager的适配器
 */
public class MainAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] titles = {"游记", "攻略", "工具箱"};

    public MainAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }


    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
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
        return titles[position];
    }
}
