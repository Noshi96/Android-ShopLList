package edu.zut.androidui;

import java.io.Serializable;

public class Item implements Serializable {

    public String name;
    public final Integer image;
    public Integer amount;
    public final String unit;

    public Item(String name, Integer image, Integer amount, String unit) {
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public Integer getImage() {
        return image;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void plusOneToAmount() {
        amount += 1;
    }

    public void minusOneToAmount() {
        amount -= 1;
        if (amount < 1) {
            amount = 1;
        }
    }

}
