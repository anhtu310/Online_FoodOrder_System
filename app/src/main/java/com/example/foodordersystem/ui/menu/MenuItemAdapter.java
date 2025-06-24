package com.example.foodordersystem.ui.menu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordersystem.R;
import com.example.foodordersystem.data.database.DatabaseClient;
import com.example.foodordersystem.data.entity.MenuItem;

import java.util.List;
import java.util.concurrent.Executors;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuViewHolder> {

    private final List<MenuItem> items;
    private final Context context;
    private final Runnable onRefresh;

    public MenuItemAdapter(List<MenuItem> items, Context context, Runnable onRefresh) {
        this.items = items;
        this.context = context;
        this.onRefresh = onRefresh;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_menu, parent, false); // ⚠️ Phải dùng item_menu.xml, không phải activity_item_menu.xml
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = items.get(position);
        holder.txtName.setText(item.itemName);
        holder.txtPrice.setText(String.format("%.0f đ", item.price));

        // Nút Sửa
        holder.btnEdit.setOnClickListener(v -> {
            new MenuFormDialog(context, onRefresh, item).show();
        });

        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc muốn xóa món '" + item.itemName + "'?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        Executors.newSingleThreadExecutor().execute(() -> {
                            DatabaseClient.getInstance(context)
                                    .getAppDatabase()
                                    .menuDao()
                                    .deleteMenuItem(item);

                            ((Activity) context).runOnUiThread(() -> {
                                Toast.makeText(context, "Đã xóa món ăn", Toast.LENGTH_SHORT).show();
                                onRefresh.run();
                            });
                        });
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice;
        Button btnEdit, btnDelete;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}