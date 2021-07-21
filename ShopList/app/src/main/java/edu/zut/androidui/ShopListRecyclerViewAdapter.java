package edu.zut.androidui;

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

public class ShopListRecyclerViewAdapter extends RecyclerView.Adapter<ShopListRecyclerViewAdapter.ItemListViewHolder> {

    final ShopList shopList;
    final Context context;
    final MainActivity app;

    public ShopListRecyclerViewAdapter(ShopList shopList, Context context, MainActivity app){
        this.shopList = shopList;
        this.context = context;
        this.app = app;
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shop_list, parent, false);

        return new ItemListViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return shopList.getVecShopList().size();
    }

    @Override
    public void onBindViewHolder(ItemListViewHolder holder, int position) {
        ShopListItem shopListItem = shopList.getShopListRowByIndex(position);
        holder.imageViewShopListRowImg.setImageResource(shopListItem.getImage());
        holder.textViewName.setText(shopListItem.getName());
        holder.textViewAmount.setText(shopListItem.getAmount());
        holder.position = position;
        holder.shopListItem = shopListItem;
        holder.app = app;
    }

    public class ItemListViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageViewShopListRowImg;
        private final TextView textViewName;
        private final TextView textViewAmount;
        public  final RecyclerView mRecyclerViewRow;
        int position;
        final View rootView;
        ShopListItem shopListItem;
        MainActivity app;

        public ItemListViewHolder(final View itemView) {
            super(itemView);

            rootView = itemView;
            imageViewShopListRowImg = itemView.findViewById(R.id.imageViewShopListRowImg);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAmount = itemView.findViewById(R.id.textViewAmount);
            mRecyclerViewRow = itemView.findViewById(R.id.shopList);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo", "onClick: item clicked " + position + " item " + shopListItem.getName());
                    app.getCShopList().removeItemFromShopList(shopListItem);
                    Toast.makeText(app.getApplicationContext(), "Kupiono " + shopListItem.getName(), Toast.LENGTH_SHORT).show();
                    app.writeData();
                    notifyDataSetChanged();
                }
            });

            itemView.findViewById(R.id.minusOneItemInShopListButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo", "Minus: item clicked " + position + " item " + shopListItem.getAmount());
                    shopListItem.minusOneToAmount();
                    if (shopListItem.getAmountHelper() < 1){
                        Toast.makeText(app.getApplicationContext(), "Kupiono " + shopListItem.getName(), Toast.LENGTH_SHORT).show();
                        app.getCShopList().removeItemFromShopList(shopListItem);
                    }
                    app.writeData();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
