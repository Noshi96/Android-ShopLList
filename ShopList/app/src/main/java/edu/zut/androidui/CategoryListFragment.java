package edu.zut.androidui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class CategoryListFragment extends Fragment {

    private MainActivity app;

    public CategoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_with_categories, container, false);
        app = (MainActivity) getActivity();

        ListView listView = view.findViewById(R.id.listWithCategories);
        CategoryList categoriesList;
        categoriesList = app.getCCategoriesList();

        CategoryListViewAdapter categoryListViewAdapter = new CategoryListViewAdapter(app, categoriesList);
        listView.setAdapter(categoryListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                app.getCCategoriesList().categoryIndex = position;
                app.changeToCurrentItemListVec(app.getCurrentItemsListInCategory());
                app.changeFragmentToItemList();
            }
        });
        return view;
    }
}