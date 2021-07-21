package edu.zut.androidui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemListFragment extends Fragment {

    public ItemListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_with_items, container, false);
        MainActivity app = (MainActivity) getActivity();
        RecyclerView recyclerView = view.findViewById(R.id.newItemListRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(app);
        recyclerView.setLayoutManager(layoutManager);

        ItemList itemList;
        itemList = app.getCItemsList();

        ItemListRecyclerViewAdapter itemListRecyclerViewAdapter = new ItemListRecyclerViewAdapter(itemList, app, app);
        recyclerView.setAdapter(itemListRecyclerViewAdapter);

        return view;
    }
}



















