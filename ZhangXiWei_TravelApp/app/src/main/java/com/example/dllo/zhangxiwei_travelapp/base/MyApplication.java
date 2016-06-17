package com.example.dllo.zhangxiwei_travelapp.base;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by dllo on 16/5/17.
 * 自定义Application
 */
public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }


    //各个平台的配置，建议放在全局Application或者程序入口
    {

        PlatformConfig.setSinaWeibo("2663880163", "06678c10a612387d90678fc94840e4bd");
        //新浪微博 appkey appsecret
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        // QQ和Qzone appid appkey


    }
}
