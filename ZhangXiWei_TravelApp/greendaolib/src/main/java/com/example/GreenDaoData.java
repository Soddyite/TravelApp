package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoData {

    public static void main(String[] args){

        Schema schema = new Schema(1,"com.example.dllo.zhangxiwei_travelapp.entity");
        addNote(schema);
        addUser(schema);
        addCollect(schema);
        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //攻略页的最近浏览的表
    private static void addNote(Schema schema) {

        Entity entity = schema.addEntity("RecentStrategyEntity");
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("placeName");
        entity.addIntProperty("placeNum");

    }


    //注册用户的数据库
    private static void addUser(Schema schema){

        Entity entity = schema.addEntity("UserEntity");
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("userEmail");
        entity.addStringProperty("userPassWord");

    }


    //注册用户的数据库
    private static void addCollect(Schema schema){

        Entity entity = schema.addEntity("CollectEntity");
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("collectType");
        entity.addStringProperty("collectTitle");
        entity.addStringProperty("collectImageUrl");

    }



}
