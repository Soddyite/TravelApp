package com.example.dllo.zhangxiwei_travelapp.bean;

/**
 * Created by dllo on 16/6/1.
 */
public class SearchSingleBean {

    private int id;
    private String name;


    public SearchSingleBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
