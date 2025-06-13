package com.example.foodordersystem.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "userId",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE)
        })
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int orderId;

    @ColumnInfo(name = "userId", index = true)
    public int userId;

    @ColumnInfo(name = "orderDate")
    public String orderDate;

    @ColumnInfo(name = "totalPrice")
    public double totalPrice;

    @ColumnInfo(name = "status")
    public String status;
}
