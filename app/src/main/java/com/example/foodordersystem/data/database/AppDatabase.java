package com.example.foodordersystem.data.database;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
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

    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "food_order_database"
                            )
                            .fallbackToDestructiveMigration() // Xoá DB nếu thay đổi version
                            .build();
                }
            }
        }
        return instance;
    }
}

