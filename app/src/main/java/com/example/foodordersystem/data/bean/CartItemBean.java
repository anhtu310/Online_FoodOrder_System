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
}
