package com.baluche.http.entity;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class Register {
    /**
     * code : 200
     * message : success
     * data : {"token":"3474379f786f66c0a9833c245cf51de3"}
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
         * token : 3474379f786f66c0a9833c245cf51de3
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
