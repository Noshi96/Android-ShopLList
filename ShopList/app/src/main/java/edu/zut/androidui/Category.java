package edu.zut.androidui;

import java.io.Serializable;

public class Category implements Serializable {

    public final String name;
    public final Integer  image;
    public String itemInside;

    public Category(String name, Integer image, String itemInside) {
        this.name = name;
        this.image = image;
        this.itemInside = itemInside;
    }

    public String getName() {
        return name;
    }

    public Integer getImage() {
        return image;
    }

    public String getItemInside() {
        return itemInside;
    }

    public void setItemInside(String itemInside) {
        this.itemInside = itemInside;
    }
}
