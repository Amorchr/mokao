package com.linkhand.mokao.entity;

import java.util.List;

/**
 * Created by Amorr on 17/8/29.
 */

//{
//        "code": 200,
//        "msg": "查询成功",
//        "info": [
//        {
//        "id": "1",
//        "label": "主要负责人",
//        "logo": "http://mokao.177678.top/data/upload/20170829/59a4c04796293.jpg"
//        },
//        {
//        "id": "2",
//        "label": "安全生产管理人员",
//        "logo": "http://mokao.177678.top/data/upload/20170829/59a4c06f2b462.png"
//        },
//        {
//        "id": "3",
//        "label": "特种作业人员",
//        "logo": "http://mokao.177678.top/data/upload/20170829/59a4c07d9c787.png"
//        }
//        ],
//        "referer": "",
//        "state": "fail"
//        }


public class Getbanner {

    /**
     * id : 1
     * label : 主要负责人
     * logo : http://mokao.177678.top/data/upload/20170829/59a4c04796293.jpg
     */

    private String id;
    private String label;
    private String logo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
