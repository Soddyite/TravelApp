package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class RecentStrategy {

    public static void main(String[] args){

        Schema schema = new Schema(1,"com.example.dllo.zhangxiwei_travelapp.entity");
        addNote(schema);
        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void addNote(Schema schema) {

        Entity entity = schema.addEntity("RecentStrategyEntity");
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("placeName");
        entity.addIntProperty("placeNum");


    }
}
