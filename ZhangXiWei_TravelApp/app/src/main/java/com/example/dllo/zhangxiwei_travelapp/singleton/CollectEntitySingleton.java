package com.example.dllo.zhangxiwei_travelapp.singleton;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.entity.CollectEntity;
import com.example.dllo.zhangxiwei_travelapp.entity.CollectEntityDao;
import com.example.dllo.zhangxiwei_travelapp.entity.DaoMaster;
import com.example.dllo.zhangxiwei_travelapp.entity.DaoSession;
import com.example.dllo.zhangxiwei_travelapp.entity.UserEntityDao;

/**
 * Created by dllo on 16/5/31.
 */
public class CollectEntitySingleton {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;
    private DaoMaster.DevOpenHelper helper;
    private CollectEntityDao entityDao;


    private static CollectEntitySingleton ourInstance = new CollectEntitySingleton();


    private CollectEntitySingleton() {
        context = MyApplication.getContext();
    }


    public static CollectEntitySingleton getInstance() {
        if (ourInstance == null) {
            synchronized (RecentStrategySingleton.class) {
                if (ourInstance == null) {
                    ourInstance = new CollectEntitySingleton();
                }
            }
        }
        return ourInstance;
    }

    public DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, "UserEntity.DB", null);
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

    public CollectEntityDao getEntityDao() {
        if (entityDao == null) {
            entityDao = getDaoSession().getCollectEntityDao();
        }
        return entityDao;
    }


}
