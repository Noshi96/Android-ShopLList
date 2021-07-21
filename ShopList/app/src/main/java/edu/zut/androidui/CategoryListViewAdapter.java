package edu.zut.androidui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryListViewAdapter extends ArrayAdapter {

    private final Activity context;
    final CategoryList categoryList;

    public CategoryListViewAdapter(Activity context, CategoryList categoryList) {
        super(context, R.layout.row_category_list, categoryList.categoryNamesTab());
        this.context = context;
        this.categoryList = categoryList;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null)
            row = inflater.inflate(R.layout.row_category_list, null, true);

        TextView textViewCategory = row.findViewById(R.id.textViewCategory);
        TextView textViewItemsInCategory = row.findViewById(R.id.textViewItemsInCategory);
        ImageView imageFlag = row.findViewById(R.id.imageViewFlag);

        textViewCategory.setText(categoryList.getCategoryByIndex(position).getName());
        textViewItemsInCategory.setText(categoryList.getCategoryByIndex(position).getItemInside());
        imageFlag.setImageResource(categoryList.getCategoryByIndex(position).getImage());

        return row;
    }
}
