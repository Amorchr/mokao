package com.linkhand.mokao.entity;

/**
 * Created by Amorr on 17/8/28.
 */
//{
//        "code": 100,
//        "msg": "此号码没有注册",
//        "referer": "",
//        "state": "fail"
//        }
public class Loginresult {
    private int code ;
    private String msg ;
    private String referer ;
    private String state ;

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
