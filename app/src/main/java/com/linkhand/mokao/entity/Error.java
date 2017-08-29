package com.linkhand.mokao.entity;

import java.io.Serializable;

/**
 * Created by JCY on 2017/8/22.
 * 说明： 错题
 */

public class Error implements Serializable {
    private String name;
    private int current;
    private int count;

    public Error() {

    }

    public Error(String name, int current, int count) {
        this.name = name;
        this.current = current;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
