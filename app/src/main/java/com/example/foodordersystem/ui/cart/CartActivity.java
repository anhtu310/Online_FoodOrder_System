package com.example.foodordersystem.ui.cart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordersystem.R;
import com.example.foodordersystem.data.bean.CartItemBean;
import com.example.foodordersystem.data.database.DatabaseClient;
import com.example.foodordersystem.data.entity.CartItem;
import com.example.foodordersystem.data.entity.MenuItem;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView rvCart;
    CartItemAdapter adapter;
    List<CartItemBean> items;

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

        getCartItem();
    }

    private void getCartItem() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);
        if (userId == -1) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }
        List<CartItem> cartItems = DatabaseClient.getInstance(this)
            .getAppDatabase()
            .cartDao()
            .getCartItemsByUser(userId);

        List<MenuItem> menuItems = DatabaseClient.getInstance(this)
            .getAppDatabase()
            .menuDao()
            .getAllMenuItems();

        for (CartItem cartItem : cartItems) {
            CartItemBean item = new CartItemBean();
            for (MenuItem menuItem : menuItems) {
                if (cartItem.itemId == menuItem.itemId) {
                    item.cartItemId = cartItem.cartItemId;
                    item.userId = cartItem.userId;
                    item.itemId = cartItem.itemId;
                    item.quantity = cartItem.quantity;
                    item.itemName = menuItem.itemName;
                    item.price = menuItem.price;
                    item.imageUrl = menuItem.imageUrl;
                    items.add(item);
                    break;
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}