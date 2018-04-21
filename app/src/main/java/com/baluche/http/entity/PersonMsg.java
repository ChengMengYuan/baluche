package com.baluche.http.entity;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class PersonMsg {
    /**
     * code : 200
     * message : success
     * data : {"user_id":96842,"headimgurl":"http://a.baluche.cn/avatar/1","headimgtime":0,"nickname":null,"birthday":0,"sex":0,"tel":"18720129026","wallet":"0.00","openid":null,"sort":null,"create_time":1522465254,"update_time":null,"status":null,"yd_car":null,"yd_car_add":null,"yd_red":null,"mini_openid":null}
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
         * user_id : 96842
         * headimgurl : http://a.baluche.cn/avatar/1
         * headimgtime : 0
         * nickname : null
         * birthday : 0
         * sex : 0
         * tel : 18720129026
         * wallet : 0.00
         * openid : null
         * sort : null
         * create_time : 1522465254
         * update_time : null
         * status : null
         * yd_car : null
         * yd_car_add : null
         * yd_red : null
         * mini_openid : null
         */

        private int user_id;
        private String headimgurl;
        private int headimgtime;
        private Object nickname;
        private int birthday;
        private int sex;
        private String tel;
        private String wallet;
        private Object openid;
        private Object sort;
        private int create_time;
        private Object update_time;
        private Object status;
        private Object yd_car;
        private Object yd_car_add;
        private Object yd_red;
        private Object mini_openid;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public int getHeadimgtime() {
            return headimgtime;
        }

        public void setHeadimgtime(int headimgtime) {
            this.headimgtime = headimgtime;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getWallet() {
            return wallet;
        }

        public void setWallet(String wallet) {
            this.wallet = wallet;
        }

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public Object getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(Object update_time) {
            this.update_time = update_time;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getYd_car() {
            return yd_car;
        }

        public void setYd_car(Object yd_car) {
            this.yd_car = yd_car;
        }

        public Object getYd_car_add() {
            return yd_car_add;
        }

        public void setYd_car_add(Object yd_car_add) {
            this.yd_car_add = yd_car_add;
        }

        public Object getYd_red() {
            return yd_red;
        }

        public void setYd_red(Object yd_red) {
            this.yd_red = yd_red;
        }

        public Object getMini_openid() {
            return mini_openid;
        }

        public void setMini_openid(Object mini_openid) {
            this.mini_openid = mini_openid;
        }
    }
}
