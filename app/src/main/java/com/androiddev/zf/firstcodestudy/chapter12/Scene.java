package com.androiddev.zf.firstcodestudy.chapter12;

/**
 * Created by greedy on 2017/4/12.
 */

public class Scene {

    private String name;

    private int imageId;

    public Scene(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
