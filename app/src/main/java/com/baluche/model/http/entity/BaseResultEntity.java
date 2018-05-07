package com.baluche.model.http.entity;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class BaseResultEntity {
    private int code;
    private String message;

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
}
