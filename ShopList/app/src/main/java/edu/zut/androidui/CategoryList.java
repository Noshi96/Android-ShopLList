package edu.zut.androidui;

import java.io.Serializable;
import java.util.Vector;

public class CategoryList implements Serializable {

    public int categoryIndex;
    private final Vector<Category> VCategory = new Vector<>();

    public CategoryList() {
        categoryIndex = 0;
        fillVector();
    }

    public Category getCategoryByIndex(int index) {
        return VCategory.get(index);
    }

    private final Integer[] imageId = {
            R.drawable.bathroom,
            R.drawable.kitchen,
            R.drawable.jedzenie,
            R.drawable.car,
            R.drawable.kot,
            R.drawable.dog
    };

    private final String[] categoryNames = {
            "Łazienka",
            "Kuchnia",
            "Jedzenie",
            "Samochód",
            "Kot",
            "Pies"
    };

    private final String[] itemsNames = {
            "Items",
            "Items",
            "Items",
            "Items",
            "Items",
            "Items"
    };

    private void fillVector() {
        Category cCategory;
        for (int i = 0; i < imageId.length; i++) {
            cCategory = new Category(categoryNames[i], imageId[i], itemsNames[i]);
            VCategory.add(cCategory);
        }
        categoryIndex = 0;
    }

    public String[] categoryNamesTab() {
        String[] temp = new String[imageId.length];
        for (int i = 0; i < VCategory.size(); i++) {
            temp[i] = VCategory.get(i).name;
        }
        return temp;
    }
}
