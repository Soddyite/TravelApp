package com.example.dllo.zhangxiwei_travelapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/5/17.
 */
public class MyApplication extends Application{

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }
}
