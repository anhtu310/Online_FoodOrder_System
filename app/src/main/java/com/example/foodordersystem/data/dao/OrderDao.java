package com.example.foodordersystem.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import com.example.foodordersystem.data.entity.Order;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void insertOrder(Order order);

    @Delete
    void deleteOrder(Order order);

    @Query("SELECT * FROM orders WHERE userId = :userId")
    List<Order> getOrdersByUser(int userId);
}
