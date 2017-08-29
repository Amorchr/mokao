package com.linkhand.mokao.entity;

/**
 * Created by JCY on 2017/8/21.
 * 说明：
 */

public class HomeSort {
    private String id;
    private String name;
    private String imageurl;

    public HomeSort() {
    }

    public HomeSort(String name, String imageurl) {
        this.name = name;
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
