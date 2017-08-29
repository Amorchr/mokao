package com.linkhand.mokao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JCY on 2017/8/21.
 * 说明：
 */

public class Sort implements Serializable {
    String id;
    String title;
    List<SortContent> mList;
    boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SortContent> getList() {
        return mList;
    }

    public void setList(List<SortContent> list) {
        mList = list;
    }

    public static class SortContent implements Serializable {
        private String contentId;
        private String name;

        public SortContent() {
        }

        public String getContentId() {
            return contentId;
        }

        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
