package com.example.dllo.zhangxiwei_travelapp.singleton;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.entity.DaoMaster;
import com.example.dllo.zhangxiwei_travelapp.entity.DaoSession;
import com.example.dllo.zhangxiwei_travelapp.entity.RecentStrategyEntityDao;

/**
 * Created by dllo on 16/5/24.
 * GreenDao的单例
 */
public class RecentStrategySingleton {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;
    private DaoMaster.DevOpenHelper helper;
    private RecentStrategyEntityDao entityDao;

    private static RecentStrategySingleton ourInstance = new RecentStrategySingleton();


    private RecentStrategySingleton() {
        context = MyApplication.getContext();
    }


    public static RecentStrategySingleton getInstance() {
        if (ourInstance == null) {
            synchronized (RecentStrategySingleton.class) {
                if (ourInstance == null) {
                    ourInstance = new RecentStrategySingleton();
                }
            }
        }
        return ourInstance;
    }

    public DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, "RecentStrategy.DB", null);
        }
        return helper;
    }

    private SQLiteDatabase getDb() {
        if (db == null) {
            db = getHelper().getWritableDatabase();

        }
        return db;
    }

    private DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }

    public RecentStrategyEntityDao getEntityDao() {
        if (entityDao == null) {
            entityDao = getDaoSession().getRecentStrategyEntityDao();
        }
        return entityDao;
    }

}
