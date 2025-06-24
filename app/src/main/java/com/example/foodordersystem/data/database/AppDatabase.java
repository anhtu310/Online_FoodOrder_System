package com.example.foodordersystem.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.foodordersystem.data.dao.OrderInfoDao;
import com.example.foodordersystem.data.dao.UserDao;
import com.example.foodordersystem.data.dao.MenuDao;
import com.example.foodordersystem.data.dao.CartDao;
import com.example.foodordersystem.data.dao.OrderDao;
import com.example.foodordersystem.data.entity.OrderInfo;
import com.example.foodordersystem.data.entity.User;
import com.example.foodordersystem.data.entity.MenuItem;
import com.example.foodordersystem.data.entity.CartItem;
import com.example.foodordersystem.data.entity.Order;

@Database(
        entities = {
                User.class,
                Order.class,
                MenuItem.class,
                CartItem.class,
                OrderInfo.class
        },
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract MenuDao menuDao();
    public abstract CartDao cartDao();
    public abstract OrderDao orderDao();
    public abstract OrderInfoDao orderInfoDao();
}

