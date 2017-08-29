package com.linkhand.mokao.entity;

/**
 * Created by Amorr on 17/8/28.
 */

//        "code": 200,
//        "msg": "发送成功",
//        "info": 2125,
//        "referer": "",
//        "state": "fail"
//        }
public class LoginSend {
    private int code;
    private String msg;
    private int info;
    private String referer;
    private String state;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

