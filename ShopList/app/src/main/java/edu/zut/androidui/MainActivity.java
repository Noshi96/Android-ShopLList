package edu.zut.androidui;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private final Fragment listWithCategories = new CategoryListFragment();
    private final Fragment ShopListFragment = new ShopListFragment();
    private final Fragment newItemList = new ItemListFragment();

    private ArrayList<Fragment> tabFragments;
    private CategoryList categoryList = new CategoryList();
    private ShopList shopList = new ShopList();
    private ItemList itemList = new ItemList();
    private Vector<ItemList> itemsInEachCategory = new Vector<>();
    TabLayout tabLayout;

    public ItemList getCurrentItemsListInCategory() {
        return itemsInEachCategory.get(categoryList.categoryIndex);
    }

    public void changeToCurrentItemListVec(ItemList currentItemList) {
        itemList = currentItemList;
    }

    public ShopList getCShopList() {
        return shopList;
    }

    public CategoryList getCCategoriesList() {
        return categoryList;
    }

    public ItemList getCItemsList() {
        return itemList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("STATE", "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabFragments = new ArrayList<>();
        tabFragments.add(listWithCategories);
        tabFragments.add(newItemList);
        tabFragments.add(ShopListFragment);

        tabLayout = findViewById(R.id.layout_tab);

        tabLayout.getTabAt(0).setIcon(R.drawable.house);
        tabLayout.getTabAt(1).setIcon(R.drawable.plus);
        tabLayout.getTabAt(2).setIcon(R.drawable.shopping_list);
        tabLayout.addOnTabSelectedListener(this);

        if (savedInstanceState == null) {
            replaceFragment(listWithCategories);
        }

        fillItemList();
        readData();
    }

    protected void replaceFragment(Fragment fragment) {
        if (findViewById(R.id.fragment_center) == null) {
            return;
        }
        if (fragment == null) {
            return;
        }

        String tag = fragment.getClass().getName();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_center, fragment, tag);
        fragmentTransaction.commit();
    }


    public void changeFragmentToItemList() {
        //String tag = newItemList.getClass().getName();
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.fragment_center, newItemList, tag);
        //fragmentTransaction.commit();
        Fragment fragment = tabFragments.get(1);
        tabLayout.getTabAt(1).select();
        replaceFragment(fragment);
    }

    /**
     * Writes notes' data to disk file.
     */
    public void writeData() {
        try {
            FileOutputStream file_out = openFileOutput("storage.dat", MODE_PRIVATE);
            ObjectOutputStream outputWriter = new ObjectOutputStream(file_out);
            outputWriter.writeObject(shopList);
            outputWriter.writeObject(itemList);
            outputWriter.writeObject(categoryList);
            outputWriter.writeObject(itemsInEachCategory);
            outputWriter.close();
            file_out.close();
            //Toast.makeText(getBaseContext(), "Data saved to storage.dat", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads notes' data from disk file.
     */
    public void readData() {
        try {
            FileInputStream file_in = openFileInput("storage.dat");
            ObjectInputStream inputReader = new ObjectInputStream(file_in);
            shopList = (ShopList) inputReader.readObject();
            itemList = (ItemList) inputReader.readObject();
            categoryList = (CategoryList) inputReader.readObject();
            itemsInEachCategory = (Vector<ItemList>) inputReader.readObject();
            inputReader.close();
            file_in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStart() {
        Log.d("STATE", "onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("STATE", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("STATE", "onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("STATE", "onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("STATE", "onResume()");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d("STATE", "onRestart()");
        super.onRestart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int index = tab.getPosition();
        Log.d("TAB", String.valueOf(index));

        Fragment fragment = tabFragments.get(index);
        replaceFragment(fragment);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.d("NAVIGATION", String.valueOf(menuItem.getItemId()));
        return super.onOptionsItemSelected(menuItem);
    }

    public void fillItemsInside(ItemList itemList, int index) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            result.append(itemList.getItemByIndex(i).getName()).append(" ");
        }
        categoryList.getCategoryByIndex(index).setItemInside(result.toString());
    }

    public void fillItemList() {
        String[] itemNamesKitchen = {
                "Mleko",
                "Płatki",
                "Sok",
                "Maslo",
        };

        Integer[] imageIdKitchen = {
                R.drawable.mleko,
                R.drawable.platki,
                R.drawable.sok,
                R.drawable.maslo,
        };

        String[] units = {
                "szt",
                "szt",
                "szt",
                "szt"
        };

        Integer[] amounts = {
                1, 1, 1, 1
        };

        String[] itemNamesCat = {
                "Karma",
                "Żwirek",
                "Drapak",
                "Zabawka"
        };

        Integer[] imageIdCat = {
                R.drawable.karmakota,
                R.drawable.zwirek,
                R.drawable.drapak,
                R.drawable.zabawkakot,
        };

        String[] itemNamesDog = {
                "Karma",
                "Zabawka",
                "Smycz",
        };

        Integer[] imageIdDog = {
                R.drawable.karmapsa,
                R.drawable.zabawkapies,
                R.drawable.smycz,
        };

        String[] itemNamesFood = {
                "Ziemniaki",
                "Marchew",
                "Pietruszka",
                "Koperek",
        };

        Integer[] imageidFood = {
                R.drawable.ziemniaki,
                R.drawable.marchew,
                R.drawable.pietruszka,
                R.drawable.maklowicz,
        };

        String[] itemNamesCar = {
                "Olej",
                "Odświeżacz",
                "Opony",
                "Płyn do spryskiwaczy",
        };

        Integer[] imageIdCar = {
                R.drawable.olej,
                R.drawable.odswiezacz,
                R.drawable.opony,
                R.drawable.plyn,

        };

        ItemList itemListKitchen = new ItemList(1);
        ItemList itemListFood = new ItemList(2);
        ItemList itemListCar = new ItemList(3);
        ItemList itemListCat = new ItemList(4);
        ItemList itemListDog = new ItemList(5);
        Item item;

        itemsInEachCategory.add(itemList);
        fillItemsInside(itemList, 0);

        for (int i = 0; i < imageIdKitchen.length; i++) {
            item = new Item(itemNamesKitchen[i], imageIdKitchen[i], amounts[i], units[i]);
            itemListKitchen.getVecItems().add(item);
        }

        itemsInEachCategory.add(itemListKitchen);
        fillItemsInside(itemListKitchen, 1);

        for (int i = 0; i < imageidFood.length; i++) {
            item = new Item(itemNamesFood[i], imageidFood[i], amounts[i], units[i]);
            itemListFood.getVecItems().add(item);
        }

        itemsInEachCategory.add(itemListFood);
        fillItemsInside(itemListFood, 2);

        for (int i = 0; i < imageIdCar.length; i++) {
            item = new Item(itemNamesCar[i], imageIdCar[i], amounts[i], units[i]);
            itemListCar.getVecItems().add(item);
        }

        itemsInEachCategory.add(itemListCar);
        fillItemsInside(itemListCar, 3);

        for (int i = 0; i < imageIdCat.length; i++) {
            item = new Item(itemNamesCat[i], imageIdCat[i], amounts[i], units[i]);
            itemListCat.getVecItems().add(item);
        }

        itemsInEachCategory.add(itemListCat);
        fillItemsInside(itemListCat, 4);

        for (int i = 0; i < imageIdDog.length; i++) {
            item = new Item(itemNamesDog[i], imageIdDog[i], amounts[i], units[i]);
            itemListDog.getVecItems().add(item);
        }
        itemsInEachCategory.add(itemListDog);
        fillItemsInside(itemListDog, 5);
    }
}
