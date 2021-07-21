package edu.zut.androidui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShopListFragment extends Fragment {

    public ShopListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_with_shop_list, container, false);
        MainActivity app = (MainActivity) getActivity();

        RecyclerView recyclerView = view.findViewById(R.id.shopList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(app);
        recyclerView.setLayoutManager(layoutManager);

        ShopList shopList;
        shopList = app.getCShopList();

        ShopListRecyclerViewAdapter shopListRecyclerViewAdapter = new ShopListRecyclerViewAdapter(shopList, app, app);
        recyclerView.setAdapter(shopListRecyclerViewAdapter);

        return view;
    }
}