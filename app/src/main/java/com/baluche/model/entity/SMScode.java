package com.baluche.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class SMScode {
    /**
     * code : 200
     * message : success
     * data : []
     */

    private int code;
    private String message;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
