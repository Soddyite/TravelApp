package com.example.dllo.zhangxiwei_travelapp;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.zhangxiwei_travelapp.adapter.MainAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.fragment.FragmentNote;
import com.example.dllo.zhangxiwei_travelapp.fragment.FragmentStrategy;
import com.example.dllo.zhangxiwei_travelapp.fragment.FragmentTools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import threadpool.MyThreadPool;

/**
 * Created by dllo on 16/5/9.
 */
public class MainActivity extends BaseActivity{

    MainAdapter mainAdapter;
    List<Fragment> fragments;
    ViewPager mainViewPager;
    TabLayout mainTabLayout;
    private ThreadPoolExecutor myThreadPool;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {

        mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainViewPager = bindView(R.id.main_viewpager);
        mainTabLayout = bindView(R.id.main_tablayout);

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        FragmentNote fragmentNote = new FragmentNote();
        FragmentStrategy fragmentStrategy = new FragmentStrategy();
        FragmentTools fragmentTools = new FragmentTools();

        fragments.add(fragmentNote);
        fragments.add(fragmentStrategy);
        fragments.add(fragmentTools);

        mainAdapter.setFragments(fragments);
        mainViewPager.setAdapter(mainAdapter);
        mainTabLayout.setupWithViewPager(mainViewPager);
        mainTabLayout.setTabTextColors(Color.rgb(0, 0, 0), Color.rgb(00, 153, 255));
        mainTabLayout.setSelectedTabIndicatorColor(Color.rgb(00, 153, 255));


        myThreadPool = MyThreadPool.getInstance().getThreadPool();
        myThreadPool.execute(FragmentNote.noteRotateRunning);


    }

    @Override
    protected void onDestroy() {

        myThreadPool.shutdown();
        super.onDestroy();
    }
}
