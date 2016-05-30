package com.example.dllo.zhangxiwei_travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;

import cn.jpush.android.api.JPushInterface;

//欢迎页,加入子线程实现延时跳转
public class WelcomeActivity extends BaseActivity {

    private ImageView pageImageView, ipImageView;
    private boolean isFinish = false;

    private Handler handler;


    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        pageImageView = bindView(R.id.welcome_page_logo);
        ipImageView = bindView(R.id.welcome_ip_logo);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                pageImageView.setImageResource(R.mipmap.welimg);
                pageImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ipImageView.setImageBitmap(null);

                return false;
            }
        });


    }

    @Override
    protected void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (!isFinish) {

                    try {
                        Thread.sleep(1500);

                        handler.sendEmptyMessage(0);


                        Thread.sleep(2000);


                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }
        }).start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    public void finish() {
        super.finish();
        isFinish = true;
    }
}
