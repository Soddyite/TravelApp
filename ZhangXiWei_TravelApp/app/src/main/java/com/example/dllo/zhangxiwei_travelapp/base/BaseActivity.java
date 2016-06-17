package com.example.dllo.zhangxiwei_travelapp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by dllo on 16/5/9.
 * activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        initView();
        initData();
    }

    //三个抽象方法,绑定布局,绑定组件,赋值
    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    //绑定组件的方法
    //组件实例化的方法,不需要转型了
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }






}
