package com.example.foodordersystem;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodordersystem.data.database.DatabaseClient;
import com.example.foodordersystem.data.entity.User;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Test database
        testDatabase();
    }

    private void testDatabase() {
        Executors.newSingleThreadExecutor().execute(() -> {
            // Tạo user mới
            User user = new User();
            user.username = "test_user";
            user.password = "123456";
            user.email = "test@example.com";

            // Insert user vào DB
            DatabaseClient.getInstance(getApplicationContext())
                    .getAppDatabase()
                    .userDao()
                    .insertUser(user);

            // Lấy danh sách user ra log
            List<User> userList = DatabaseClient.getInstance(getApplicationContext())
                    .getAppDatabase()
                    .userDao()
                    .getAllUsers();

            for (User u : userList) {
                Log.d(TAG, "User: " + u.userId + " - " + u.username + " - " + u.email);
            }
        });
    }
}
