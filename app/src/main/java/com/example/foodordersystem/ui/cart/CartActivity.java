package com.example.foodordersystem.ui.cart;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordersystem.R;
import com.example.foodordersystem.data.bean.CartItemBean;
import com.example.foodordersystem.data.database.AppDatabase;
import com.example.foodordersystem.data.database.DatabaseClient;
import com.example.foodordersystem.data.entity.CartItem;
import com.example.foodordersystem.data.entity.MenuItem;
import com.example.foodordersystem.data.entity.User;
import com.example.foodordersystem.ui.menu.MenuItemAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class CartActivity extends AppCompatActivity {

    RecyclerView rvCart;
    CartItemAdapter adapter;
    List<CartItemBean> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvCart = findViewById(R.id.rvCart);
        adapter = new CartItemAdapter(items, this);
        rvCart.setAdapter(adapter);
        rvCart.setLayoutManager(new LinearLayoutManager(this));

        getCartItem();
    }

    private void getCartItem() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 1);
        if (userId == -1) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        Executors.newSingleThreadExecutor().execute(() -> {
            List<CartItemBean> beans = DatabaseClient.getInstance(this)
                .getAppDatabase()
                .cartDao()
                .getCartItemBeanByUser(userId);

            runOnUiThread(() -> {
                items.clear();
                items.addAll(beans);
                adapter.notifyDataSetChanged();
            });
        });
    }
}