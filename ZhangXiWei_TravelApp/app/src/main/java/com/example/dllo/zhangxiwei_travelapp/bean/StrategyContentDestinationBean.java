package com.example.dllo.zhangxiwei_travelapp.bean;

/**
 * Created by dllo on 16/5/21.
 * 攻略详情页目的地页的实体类
 */
public class StrategyContentDestinationBean {


    /**
     * id : 34864
     * name : 浅草寺
     * attraction_trips_count : 717
     * user_score : 4.13
     * description : 浅草寺创建于628年，是东京都内最古老的寺院。江户时代将军德川家康把这里指定为幕府的祈愿所，是平安文化的中心地。
     * name_en : Sensoji Temple
     * attraction_type : sight
     * description_summary : 浅草寺创建于628年，是东京都内最古老的寺院。江户时代将军德川家康把这里指定为幕
     * image_url : http://m.chanyouji.cn/attractions/34864.jpg
     */

    private int id;
    private String name;
    private int attraction_trips_count;
    private String user_score;
    private String description;
    private String name_en;
    private String attraction_type;
    private String description_summary;
    private String image_url;

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

    public int getAttraction_trips_count() {
        return attraction_trips_count;
    }

    public void setAttraction_trips_count(int attraction_trips_count) {
        this.attraction_trips_count = attraction_trips_count;
    }

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getAttraction_type() {
        return attraction_type;
    }

    public void setAttraction_type(String attraction_type) {
        this.attraction_type = attraction_type;
    }

    public String getDescription_summary() {
        return description_summary;
    }

    public void setDescription_summary(String description_summary) {
        this.description_summary = description_summary;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
