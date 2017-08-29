package com.linkhand.mokao.entity;

import java.io.Serializable;

/**
 * Created by JCY on 2017/8/21.
 * 说明：
 */

public class Pay implements Serializable {
    private boolean flag = false;
    private String price;
    private int time;
    private int day;


    public Pay(String price, int time, int day,boolean flag) {
        this.price = price;
        this.time = time;
        this.day = day;
        this.flag = flag;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
