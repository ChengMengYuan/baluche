package com.baluche.model.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Suggest {
    private String net_park_id;
    private String title;
    private String address;
    @Generated(hash = 1843235587)
    public Suggest(String net_park_id, String title, String address) {
        this.net_park_id = net_park_id;
        this.title = title;
        this.address = address;
    }
    @Generated(hash = 878441091)
    public Suggest() {
    }
    public String getNet_park_id() {
        return this.net_park_id;
    }
    public void setNet_park_id(String net_park_id) {
        this.net_park_id = net_park_id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
