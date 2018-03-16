package com.jshc.waveprogressbar.beans;

/**
 * Created by JinT on 2018/3/16 0016.
 */

public class CardBean {
    private int type;
    private String name;
    private String status;

    public CardBean(int type, String name, String status) {
        this.type = type;
        this.name = name;
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
