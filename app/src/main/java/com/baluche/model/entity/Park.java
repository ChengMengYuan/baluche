package com.baluche.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class Park {
    /**
     * code : 200
     * message : success
     * data : [{"title":"国贸广场停车场东入口","address":"江西省南昌市西湖区洪城路2-6号","net_park_id":"841","charge":"5.00","lat":"28.654519","lng":"115.908853","null_number":0,"distance":1},{"title":"南宾国际金融中心停车场","address":"南昌市西湖区八一大道2号","net_park_id":"779","charge":"5.00","lat":"28.6634534190","lng":"115.9082836575","null_number":0,"distance":997}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 国贸广场停车场东入口
         * address : 江西省南昌市西湖区洪城路2-6号
         * net_park_id : 841
         * charge : 5.00
         * lat : 28.654519
         * lng : 115.908853
         * null_number : 0
         * distance : 1
         */

        private String title;
        private String address;
        private String net_park_id;
        private String charge;
        private String lat;
        private String lng;
        private int null_number;
        private int distance;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNet_park_id() {
            return net_park_id;
        }

        public void setNet_park_id(String net_park_id) {
            this.net_park_id = net_park_id;
        }

        public String getCharge() {
            return charge;
        }

        public void setCharge(String charge) {
            this.charge = charge;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public int getNull_number() {
            return null_number;
        }

        public void setNull_number(int null_number) {
            this.null_number = null_number;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
