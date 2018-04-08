package com.baluche.model.entity;

/**
 * Created by Administrator on 2018/4/8 0008.
 */

public class Portrait {

    /**
     * code : 200
     * message : success
     * data : {"url":"http://a.baluche.cn/avatar/96843/1523167223"}
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
         * url : http://a.baluche.cn/avatar/96843/1523167223
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
