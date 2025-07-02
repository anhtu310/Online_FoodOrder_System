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

    public CartItem() {
    }
    public CartItem(int cartItemId, int userId, int itemId, int quantity) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public CartItem(int userId, int itemId, int quantity) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
