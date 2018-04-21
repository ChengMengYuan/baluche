package com.baluche.http.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class Banner {

    /**
     * code : 200
     * message : success
     * data : [{"photo":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521546051954&di=58dd26f322c60e11641ae01c1cb56ff1&imgtype=0&src=http%3A%2F%2Fimg1.mydrivers.com%2Fimg%2F20131107%2F38c61c2a3dc44cd79c79f6305d237357.jpg","link":"http://www.baidu.com"},{"photo":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521546051952&di=214da765c9abb22bb2bffe50712fa508&imgtype=0&src=http%3A%2F%2Fcdnres.romjd.com%2FFiles%2FUploadedFilesimages%2Fda9858507f5.jpg","link":"http://www.google.com"}]
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
         * photo : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521546051954&di=58dd26f322c60e11641ae01c1cb56ff1&imgtype=0&src=http%3A%2F%2Fimg1.mydrivers.com%2Fimg%2F20131107%2F38c61c2a3dc44cd79c79f6305d237357.jpg
         * link : http://www.baidu.com
         */

        private String photo;
        private String link;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
