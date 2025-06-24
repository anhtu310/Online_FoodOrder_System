package com.example.foodordersystem.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_info",
        foreignKeys = {
                @ForeignKey(entity = Order.class,
                        parentColumns = "orderId",
                        childColumns = "orderId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = MenuItem.class,
                        parentColumns = "itemId",
                        childColumns = "itemId",
                        onDelete = ForeignKey.CASCADE)
        })
public class OrderInfo {

    @PrimaryKey(autoGenerate = true)
    public int orderInfoId;

    @ColumnInfo(name = "orderId", index = true)
    public int orderId;

    @ColumnInfo(name = "itemId", index = true)
    public int itemId;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "priceAtOrder")
    public double priceAtOrder;
}
