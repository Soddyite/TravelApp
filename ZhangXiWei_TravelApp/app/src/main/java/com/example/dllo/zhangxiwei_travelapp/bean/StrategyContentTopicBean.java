package com.example.dllo.zhangxiwei_travelapp.bean;

/**
 * Created by dllo on 16/5/21.
 * 攻略详细页的专题页的实体类
 */
public class StrategyContentTopicBean {


    /**
     * id : 68
     * name : 东京速览
     * image_url : http://m.chanyouji.cn/destinations/165-landscape.jpg
     * title : 初行东京必读的旅行手册
     * destination_id : 165
     * updated_at : 1416542170
     */

    private int id;
    private String name;
    private String image_url;
    private String title;
    private int destination_id;
    private int updated_at;

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(int destination_id) {
        this.destination_id = destination_id;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }
}
