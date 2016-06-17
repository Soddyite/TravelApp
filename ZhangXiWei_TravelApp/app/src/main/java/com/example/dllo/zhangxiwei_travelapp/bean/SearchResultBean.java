package com.example.dllo.zhangxiwei_travelapp.bean;

/**
 * Created by dllo on 16/6/2.
 */
public class SearchResultBean {


    /**
     * id : 363031
     * name : 【日本｜瀬戸内国際芸術祭】島時間
     * photos_count : 242
     * start_date : 2016-03-27
     * end_date : 2016-04-02
     * days : 7
     * level : 3
     * views_count : 3641
     * comments_count : 17
     * likes_count : 137
     * source : app
     * front_cover_photo_url : http://p.chanyouji.cn/1459757443/F69A7850-DA09-4A91-90D9-D07FEC078E06.jpg
     * featured : false
     * user : {"id":131659,"name":"Jefa","image":"http://tp1.sinaimg.cn/1210178540/180/5610723616/0"}
     */

    private int id;
    private String name;
    private int photos_count;
    private String start_date;
    private String end_date;
    private int days;
    private int level;
    private int views_count;
    private int comments_count;
    private int likes_count;
    private String source;
    private String front_cover_photo_url;
    private boolean featured;
    /**
     * id : 131659
     * name : Jefa
     * image : http://tp1.sinaimg.cn/1210178540/180/5610723616/0
     */

    private UserBean user;

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

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFront_cover_photo_url() {
        return front_cover_photo_url;
    }

    public void setFront_cover_photo_url(String front_cover_photo_url) {
        this.front_cover_photo_url = front_cover_photo_url;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        private int id;
        private String name;
        private String image;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
