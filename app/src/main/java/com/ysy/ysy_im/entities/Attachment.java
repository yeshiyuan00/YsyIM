package com.ysy.ysy_im.entities;

import java.io.Serializable;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
public class Attachment implements Serializable{

    private static final long serialVersionUID = 1L;
    private String url;
    private String name;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name) {
        this.name = name;
    }
}
