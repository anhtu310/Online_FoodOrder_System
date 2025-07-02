package com.example.foodordersystem.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.foodordersystem.data.entity.CartItem;
import com.example.foodordersystem.data.entity.MenuItem;
import com.example.foodordersystem.data.entity.User;

import java.util.Random;
import java.util.concurrent.Executors;

public class DatabaseClient {

    private static DatabaseClient instance;
    private final AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "FoodOrderDB")
            .fallbackToDestructiveMigration()
            .addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    insertMockData(context);
                }
            })
            .build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public void insertMockData(Context context) {
        Executors.newSingleThreadExecutor().execute(() -> {
            appDatabase.userDao().insertUser(new User("admin", "admin", "admin@admin"));
            for (int i = 0; i < 10; i++) {
                appDatabase.menuDao().insertMenuItem(new MenuItem("Item " + i,  i * 1.5, ""));
            }
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                appDatabase.cartDao().insertCartItem(new CartItem(1, random.nextInt(9) + 1, random.nextInt(100)));
            }
        });
    }
}
