package edu.zut.androidui;

import java.io.Serializable;

public class ShopListItem implements Serializable {

    public final String name;
    public final Integer image;

    public String amount;
    public Integer amountHelper;

    public final String unit;

    public ShopListItem(Item item) {
        this.name = item.getName();
        this.image = item.getImage();
        this.amount = item.getAmount() + " " + item.getUnit();
        this.amountHelper = item.getAmount();
        this.unit = item.getUnit();
    }

    public String getName() {
        return name;
    }

    public Integer getImage() {
        return image;
    }

    public String getAmount() {
        return amount;
    }

    public Integer getAmountHelper() {
        return amountHelper;
    }

    public void minusOneToAmount() {
        amountHelper -= 1;
        amount = amountHelper + " " + unit;
    }

    public void concatenationForDuplicatedItems(int itemAmount) {
        amountHelper += itemAmount;
        amount = amountHelper + " " + unit;
    }

}
