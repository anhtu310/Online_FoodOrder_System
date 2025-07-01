package com.example.foodordersystem.ui.cart;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordersystem.R;
import com.example.foodordersystem.data.bean.CartItemBean;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartViewHolder> {

    private final List<CartItemBean> items;
    private final Context context;

    public CartItemAdapter(List<CartItemBean> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItemBean item = items.get(position);
        holder.tvItemName.setText(item.itemName);
        holder.tvPrice.setText(String.format("%.0f", item.price));
        holder.etQuantity.setText(String.valueOf(item.quantity));
        holder.ivItemImage.setImageURI(Uri.parse(item.imageUrl));

        holder.ibIncrease.setOnClickListener(v -> {
            item.quantity++;
            holder.etQuantity.setText(String.valueOf(item.quantity));
        });

        holder.ibDecrease.setOnClickListener(v -> {
            item.quantity--;
            if (item.quantity < 1) {
                items.remove(item);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
                return;
            }
            holder.etQuantity.setText(String.valueOf(item.quantity));
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName, tvPrice;
        EditText etQuantity;
        ImageButton ibIncrease, ibDecrease;
        ImageView ivItemImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItemImage = itemView.findViewById(R.id.imageView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            etQuantity = itemView.findViewById(R.id.etQuantity);
            ibIncrease = itemView.findViewById(R.id.ibIncrease);
            ibDecrease = itemView.findViewById(R.id.ibDecrease);
        }
    }
}