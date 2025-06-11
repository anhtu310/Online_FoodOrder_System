package com.example.learningenglish;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningenglish.data.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_topic_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.topicManagerActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.topicToolbar);
        setSupportActionBar(toolbar);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic(1, "Chào hỏi", ""));
        topics.add(new Topic(2, "Gia đình", ""));
        topics.add(new Topic(3, "Thời tiết", ""));
        topics.add(new Topic(4, "Chào hỏi", ""));
        topics.add(new Topic(5, "Gia đình", ""));
        topics.add(new Topic(6, "Thời tiết", ""));
        topics.add(new Topic(7, "Chào hỏi", ""));
        topics.add(new Topic(8, "Gia đình", ""));
        topics.add(new Topic(9, "Thời tiết", ""));

        TopicAdapter adapter = new TopicAdapter(this, topics);
        recyclerView.setAdapter(adapter);




    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "Bạn đã chọn Cài đặt", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }




}