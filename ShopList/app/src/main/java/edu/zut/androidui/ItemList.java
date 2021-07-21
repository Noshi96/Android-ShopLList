package edu.zut.androidui;

import java.io.Serializable;
import java.util.Vector;

public class ItemList implements Serializable {

    public int itemIndex;
    private final Vector<Item> VItems = new Vector<>();

    public ItemList() {
        itemIndex = 0;
        fillVector();
    }

    public ItemList(int index) {
        itemIndex = index;
    }

    public Vector<Item> getVecItems() {
        return VItems;
    }

    public Item getItemByIndex(int index) {
        return VItems.get(index);
    }

    private final String[] itemNames = {
            "Mydło",
            "Szczotka",
            "Gąbka",
            "Szampon",
    };

    private final Integer[] imageId = {
            R.drawable.soap,
            R.drawable.szczoteczka,
            R.drawable.gabka,
            R.drawable.szamponjpg,
    };

    private final String[] units = {
            "szt",
            "szt",
            "szt",
            "szt"
    };

    private final Integer[] amounts = {
            1, 1, 1, 1
    };

    private void fillVector() {
        Item item;
        for (int i = 0; i < imageId.length; i++) {
            item = new Item(itemNames[i], imageId[i], amounts[i], units[i]);
            VItems.add(item);
        }
        itemIndex = 0;
    }

}
