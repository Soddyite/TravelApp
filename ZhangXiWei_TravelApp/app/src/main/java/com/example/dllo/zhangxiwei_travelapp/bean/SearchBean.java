package com.example.dllo.zhangxiwei_travelapp.bean;

import java.util.List;

/**
 * Created by dllo on 16/6/1.
 */
public class SearchBean {


    /**
     * id : 12
     * name : 台湾
     * destination_trips_count : 2687
     */

    private List<ChinaDestinationsBean> china_destinations;
    /**
     * id : 55
     * name : 日本
     * destination_trips_count : 3884
     */

    private List<OtherDestinationsBean> other_destinations;

    public List<ChinaDestinationsBean> getChina_destinations() {
        return china_destinations;
    }

    public void setChina_destinations(List<ChinaDestinationsBean> china_destinations) {
        this.china_destinations = china_destinations;
    }

    public List<OtherDestinationsBean> getOther_destinations() {
        return other_destinations;
    }

    public void setOther_destinations(List<OtherDestinationsBean> other_destinations) {
        this.other_destinations = other_destinations;
    }

    public static class ChinaDestinationsBean {
        private int id;
        private String name;
        private int destination_trips_count;

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

        public int getDestination_trips_count() {
            return destination_trips_count;
        }

        public void setDestination_trips_count(int destination_trips_count) {
            this.destination_trips_count = destination_trips_count;
        }
    }

    public static class OtherDestinationsBean {
        private int id;
        private String name;
        private int destination_trips_count;

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

        public int getDestination_trips_count() {
            return destination_trips_count;
        }

        public void setDestination_trips_count(int destination_trips_count) {
            this.destination_trips_count = destination_trips_count;
        }
    }
}
