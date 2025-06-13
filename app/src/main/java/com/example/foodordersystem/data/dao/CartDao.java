package com.example.foodordersystem.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import com.example.foodordersystem.data.entity.CartItem;

import java.util.List;

@Dao
public interface CartDao {
    @Insert
    void insertCartItem(CartItem cartItem);

    @Update
    void updateCartItem(CartItem cartItem);

    @Delete
    void deleteCartItem(CartItem cartItem);

    @Query("SELECT * FROM cart_items WHERE userId = :userId")
    List<CartItem> getCartItemsByUser(int userId);

    @Query("DELETE FROM cart_items WHERE userId = :userId")
    void clearCart(int userId);
}
