package com.example.foodordersystem.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "menu_items")
public class MenuItem {
    @PrimaryKey(autoGenerate = true)
    public int itemId;

    @ColumnInfo(name = "item_name")
    public String itemName;

    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "image_url")
    public String imageUrl;
}
