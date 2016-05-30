package com.example.dllo.zhangxiwei_travelapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.zhangxiwei_travelapp.adapter.MainAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.fragment.FragmentNote;
import com.example.dllo.zhangxiwei_travelapp.fragment.FragmentStrategy;
import com.example.dllo.zhangxiwei_travelapp.fragment.FragmentTools;
import com.example.dllo.zhangxiwei_travelapp.singleton.MyThreadPool;
import com.example.dllo.zhangxiwei_travelapp.utils.ExampleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * Created by dllo on 16/5/9.
 * 主页,部分代码为了实现极光推送
 */
public class MainActivity extends BaseActivity{

    MainAdapter mainAdapter;
    List<Fragment> fragments;
    ViewPager mainViewPager;
    TabLayout mainTabLayout;
    private ThreadPoolExecutor myThreadPool;

    public static boolean isForeground = false;

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

        registerMessageReceiver();

    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }




    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                //setCostomMsg(showMsg.toString());
            }
        }
    }




    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        myThreadPool.shutdown();
        super.onDestroy();
    }
}
