package com.baluche.http.entity;

/**
 * Created by Administrator on 2018/3/30 0030.
 */

public class Login {
    /**
     * code : 200
     * message : success
     * data : {"token":"aca89be30815ba95162362fbd762b51a"}
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
         * token : aca89be30815ba95162362fbd762b51a
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
