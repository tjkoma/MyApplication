package com.jshc.waveprogressbar.beans;

/**
 * Created by JinT on 2017/12/15 0015.
 */

public class GoodBean {
    private String goodImage;
    private String goodName;

    public GoodBean() {
    }

    public GoodBean(String goodImage, String goodName) {
        this.goodImage = goodImage;
        this.goodName = goodName;
    }

    public String getGoodImage() {
        return goodImage;
    }

    public void setGoodImage(String goodImage) {
        this.goodImage = goodImage;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
}
