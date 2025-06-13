package com.example.foodordersystem.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "userId",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = MenuItem.class,
                        parentColumns = "itemId",
                        childColumns = "itemId",
                        onDelete = ForeignKey.CASCADE)
        })
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    public int cartItemId;

    @ColumnInfo(name = "userId", index = true)
    public int userId;

    @ColumnInfo(name = "itemId", index = true)
    public int itemId;

    @ColumnInfo(name = "quantity")
    public int quantity;
}
