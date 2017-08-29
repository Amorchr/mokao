package com.linkhand.mokao.entity;

import java.io.Serializable;

/**
 * Created by JCY on 2017/8/21.
 * 说明： 题库
 */

public class Questions implements Serializable {

    String name;
    String time;
    int join;
    int error;

    public Questions() {
    }

    public Questions(String name, String time, int join) {
        this.name = name;
        this.time = time;
        this.join = join;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getJoin() {
        return join;
    }

    public void setJoin(int join) {
        this.join = join;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
