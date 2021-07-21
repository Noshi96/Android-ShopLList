package edu.zut.androidui;

import java.io.Serializable;
import java.util.Vector;

public class ShopList implements Serializable {

    public final int shopListIndex;
    private final Vector<ShopListItem> VShopList = new Vector<>();

    public int indexForDuplication;

    public ShopList() {
        shopListIndex = 0;
    }

    public Vector<ShopListItem> getVecShopList() {
        return VShopList;
    }

    public void addItemToShopList(ShopListItem shopListItem) {
        VShopList.add(shopListItem);
    }

    public void removeItemFromShopList(ShopListItem shopListItem) {
        VShopList.remove(shopListItem);
    }

    public ShopListItem getShopListRowByIndex(int index) {
        return VShopList.get(index);
    }

    public boolean isItemExistInList(String name) {
        for (int i = 0; i < VShopList.size(); i++) {
            if (VShopList.get(i).name.equals(name)) {
                indexForDuplication = i;
                return true;
            }
        }
        return false;
    }
}
