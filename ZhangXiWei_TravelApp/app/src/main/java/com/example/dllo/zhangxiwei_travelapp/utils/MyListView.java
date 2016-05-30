package com.example.dllo.zhangxiwei_travelapp.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by dllo on 16/5/12.
 * 自定义listview,解决嵌套listview下拉冲突
 */
public class MyListView extends ListView {

    public MyListView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public MyListView(Context context) {

        super(context);

    }

    public MyListView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

    }

    @Override

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }


}
