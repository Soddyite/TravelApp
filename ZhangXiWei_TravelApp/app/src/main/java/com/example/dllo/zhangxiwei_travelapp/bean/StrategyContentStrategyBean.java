package com.example.dllo.zhangxiwei_travelapp.bean;

import java.util.List;

/**
 * Created by dllo on 16/6/2.
 */
public class StrategyContentStrategyBean {

    /**
     * category_type : 6
     */

    private int category_type;
    private List<Pages> pages;

    public List<Pages> getPages() {
        return pages;
    }

    public void setPages(List<Pages> pages) {
        this.pages = pages;
    }

    public int getCategory_type() {
        return category_type;
    }

    public void setCategory_type(int category_type) {
        this.category_type = category_type;
    }

    public static final class Pages {
        /**
         * id : 62
         * title : 住宿指南
         */

        private int id;
        private String title;
        private List<Childern> childerns;

        public List<Childern> getChilderns() {
            return childerns;
        }

        public void setChilderns(List<Childern> childerns) {
            this.childerns = childerns;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static final class Childern {
            /**
             * id : 63
             * title : 常用住宿概览
             */

            private int id;
            private String title;
            private List<Section> sections;

            public List<Section> getSections() {
                return sections;
            }

            public void setSections(List<Section> sections) {
                this.sections = sections;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public static final class Section {
                /**
                 * id : 100
                 * title : 概览与地图
                 * description_user_ids : {}
                 * ctrip_attraction_ids : null
                 * description : 日本旅游目的地众多，以东京为中心的关东地区和以京都、大阪、神户为中心的关西地区是最热门的旅游地，遍布全境的樱花和天然温泉是其显著的特色。 相近的文字，完善友好的公共设施使得自由行非常容易。国内飞涨的物价已让日本的优质商品有了性价比，到了打折季节更会让人欲罢不能，加上与国人近似的饮食口味和高质量的天然食材，这里无疑还是吃货向往的天堂。
                 * travel_date : null
                 * : null
                 * attractions : []
                 * hotels : []
                 * pages : []
                 * photos : []
                 * items : []
                 */

                private int id;
                private String title;
                private Object ctrip_attraction_ids;
                private String description;
                private String travel_date;
                private User user;
                private List<Photos> photoses;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public Object getCtrip_attraction_ids() {
                    return ctrip_attraction_ids;
                }

                public void setCtrip_attraction_ids(Object ctrip_attraction_ids) {
                    this.ctrip_attraction_ids = ctrip_attraction_ids;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getTravel_date() {
                    return travel_date;
                }

                public void setTravel_date(String travel_date) {
                    this.travel_date = travel_date;
                }

                public User getUser() {
                    return user;
                }

                public void setUser(User user) {
                    this.user = user;
                }

                public List<Photos> getPhotoses() {
                    return photoses;
                }

                public void setPhotoses(List<Photos> photoses) {
                    this.photoses = photoses;
                }

                /**
                 * name : Aerisl
                 */
                public static final class User {
                    private int id;

                    private String name;

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


                /**
                 * image_url : http://w.chanyouji.cn/1405591989250p18t1svavvq3kafl1tfkc4665r1.jpg
                 * image_width : 1100
                 * image_height : 1216
                 * description : 日本概览地图
                 * trip_id : null
                 * note_id : null
                 * user_name : null
                 */
                public static class Photos {
                    private String image_url;
                    private int image_width;
                    private int image_height;
                    private String description;
                    private Object trip_id;
                    private Object note_id;
                    private Object user_name;

                    public String getImage_url() {
                        return image_url;
                    }

                    public void setImage_url(String image_url) {
                        this.image_url = image_url;
                    }

                    public int getImage_width() {
                        return image_width;
                    }

                    public void setImage_width(int image_width) {
                        this.image_width = image_width;
                    }

                    public int getImage_height() {
                        return image_height;
                    }

                    public void setImage_height(int image_height) {
                        this.image_height = image_height;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public Object getTrip_id() {
                        return trip_id;
                    }

                    public void setTrip_id(Object trip_id) {
                        this.trip_id = trip_id;
                    }

                    public Object getNote_id() {
                        return note_id;
                    }

                    public void setNote_id(Object note_id) {
                        this.note_id = note_id;
                    }

                    public Object getUser_name() {
                        return user_name;
                    }

                    public void setUser_name(Object user_name) {
                        this.user_name = user_name;
                    }
                }

            }
        }

    }


}
