package com.example.dllo.zhangxiwei_travelapp.utils;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by dllo on 16/5/30.
 */
public class WeekUtility {

    public static String getWeek(String date) {

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        Log.d("WeekUtility", "year:" + year);
        Log.d("WeekUtility", "" + date.substring(0, 4));

        Log.d("WeekUtility", "month:" + month);
        Log.d("WeekUtility", "" + date.substring(5, 7));

        Log.d("WeekUtility", "day:" + day);
        Log.d("WeekUtility", "" + date.substring(8, 10));
        Calendar calendar = Calendar.getInstance();//获得一个日历
        calendar.set(year, month - 1, day);//设置当前时间,月份是从0月开始计算
        int number = calendar.get(Calendar.DAY_OF_WEEK);//星期表示1-7，是从星期日开始，
        String[] str = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        return str[number];
    }
}
