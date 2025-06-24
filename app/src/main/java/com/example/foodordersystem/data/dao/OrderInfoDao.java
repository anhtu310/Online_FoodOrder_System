package com.example.foodordersystem.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodordersystem.data.entity.OrderInfo;

import java.util.List;

@Dao
public interface OrderInfoDao {
    @Insert
    void insert(OrderInfo orderInfo);

    @Query("SELECT * FROM order_info WHERE orderId = :orderId")
    List<OrderInfo> getOrderDetails(int orderId);
}
