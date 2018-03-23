package com.baluche.model.entity;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class Weather {

    /**
     * code : 200
     * message : success
     * data : {"city":"南昌","weather":"0","temp":"16"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * city : 南昌
         * weather : 0
         * temp : 16
         */

        private String city;
        private String weather;
        private String temp;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }
    }
}
