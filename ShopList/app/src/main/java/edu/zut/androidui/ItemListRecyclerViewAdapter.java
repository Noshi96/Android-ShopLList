package edu.zut.androidui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemListRecyclerViewAdapter extends RecyclerView.Adapter<ItemListRecyclerViewAdapter.ItemListViewHolder> {

    final ItemList itemsLists;
    final Context context;
    final MainActivity app;

    public ItemListRecyclerViewAdapter(ItemList itemsLists, Context context, MainActivity app) {
        this.itemsLists = itemsLists;
        this.context = context;
        this.app = app;
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_list, parent, false);

        return new ItemListViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return itemsLists.getVecItems().size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ItemListViewHolder holder, int position) {
        Item ItemInCategory = itemsLists.getItemByIndex(position);
        holder.imageViewNewItem.setImageResource(ItemInCategory.getImage());
        holder.newNameOfTheItem.setText(ItemInCategory.getName());
        holder.newAmount.setText(ItemInCategory.getAmount().toString());
        holder.position = position;
        holder.item = ItemInCategory;
        holder.app = app;
    }

    public class ItemListViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewNewItem;
        private final TextView newNameOfTheItem;
        private final TextView newAmount;
        final View rootView;
        int position;
        Item item;
        MainActivity app;

        public final RecyclerView mRecyclerViewRow;


        public ItemListViewHolder(final View itemView) {
            super(itemView);

            rootView = itemView;
            imageViewNewItem = itemView.findViewById(R.id.imageViewNewItem);
            newNameOfTheItem = itemView.findViewById(R.id.newNameOfTheItem);
            newAmount = itemView.findViewById(R.id.newAmount);

            mRecyclerViewRow = itemView.findViewById(R.id.newItemListRecycler);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

/*                    Log.d("demo", "onClick: item clicked " + position + " item " + cItemInCategory.getName());

                    String amountAndUnit = cItemInCategory.getAmount() + " " + cItemInCategory.getUnit();
                    CShopListRow cShopListRow = new CShopListRow(cItemInCategory);

                    app.getCShopList().addItemToShopList(cShopListRow);

                    Log.d("demo", "size of Shop List " + app.getCItemsList().getVecItems().size() + " new item " + cItemInCategory.getName());
                    Toast.makeText(app.getApplicationContext(), "Dodano " + cItemInCategory.getName() + " do listy", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    app.writeData();*/



                    /*
                    // To działa ale nie aktualizuje amount po klikaniu plusów czyli do listy shoplist dodaje tylko wartość amount tą pierwotną
                    Log.d("demo", "onClick: item clicked " + position + " item " + cItemInCategory.getName());
                    app.getCItemsList().itemIndex = position;

                    String amountAndUnit = app.getCItemsList().getCurrentItem().getAmount() + " " + app.getCItemsList().getCurrentItem().getUnit();
                    CShopListRow cShopListRow = new CShopListRow(app.getCItemsList().getCurrentItem().getName(), app.getCItemsList().getCurrentItem().getImage(), amountAndUnit);

                    app.getCShopList().addItemToShopList(cShopListRow);

                    Log.d("demo", "size of Shop List " + app.getCItemsList().getVecItems().size() + " new item " + cItemInCategory.getName());
                    notifyDataSetChanged();
                     */
                }
            });

            itemView.findViewById(R.id.newNameOfTheItem).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo", "onClick: item clicked " + position + " item " + item.getName());

                    ShopListItem shopListItem = new ShopListItem(item);

                    if (app.getCShopList().isItemExistInList(item.name)) {
                        app.getCShopList().getShopListRowByIndex(app.getCShopList().indexForDuplication).concatenationForDuplicatedItems(item.getAmount());
                    } else {
                        app.getCShopList().addItemToShopList(shopListItem);
                    }

                    Log.d("demo", "size of Shop List " + app.getCItemsList().getVecItems().size() + " new item " + item.getName());
                    Toast.makeText(app.getApplicationContext(), "Dodano " + item.getName() + " do listy", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    app.writeData();
                }
            });


            itemView.findViewById(R.id.moreButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.plusOneToAmount();
                    Log.d("demo", "Plus: item clicked " + position + " item " + item.getAmount());
                    app.writeData();
                    notifyDataSetChanged();
                }
            });

            itemView.findViewById(R.id.lessButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.minusOneToAmount();
                    Log.d("demo", "Minus: item clicked " + position + " item " + item.getAmount());
                    app.writeData();
                    notifyDataSetChanged();
                }
            });


        }

    }
}



















