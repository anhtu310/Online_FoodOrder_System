package com.example.foodordersystem.data.bean;

import androidx.room.ColumnInfo;

public class CartItemBean {
    public int cartItemId;
    public int userId;
    public int itemId;
    public int quantity;
    public String itemName;
    public double price;
    public String imageUrl;

    public CartItemBean() {
    }

    public CartItemBean(int cartItemId, int userId, int itemId, int quantity, String itemName, double price, String imageUrl) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemName = itemName;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public double getTotal() {
        return price * quantity;
    }
}
