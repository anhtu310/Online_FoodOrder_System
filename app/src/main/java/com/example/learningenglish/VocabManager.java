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
import com.example.learningenglish.data.model.Vocab;

import java.util.ArrayList;
import java.util.List;

public class VocabManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vocab_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.vocabManagerActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.vocabToolbar);

        setSupportActionBar(toolbar);



        List<Vocab> vocabs = new ArrayList<>();
        vocabs.add(new Vocab(1, "Hello", "Xin chào ", "\"/həˈləʊ/\"", "Hello bro ", "","https://img.freepik.com/free-vector/calligraphic-writing-hello_1020-450.jpg?semt=ais_hybrid&w=740" ));
        vocabs.add(new Vocab(2, "Hello", "Xin chào ", "\"/həˈləʊ/\"", "Hello bro ", "","https://img.freepik.com/free-vector/calligraphic-writing-hello_1020-450.jpg?semt=ais_hybrid&w=740" ));
        vocabs.add(new Vocab(3, "Hello", "Xin chào ", "\"/həˈləʊ/\"", "Hello bro ", "","https://img.freepik.com/free-vector/calligraphic-writing-hello_1020-450.jpg?semt=ais_hybrid&w=740" ));
        vocabs.add(new Vocab(4, "Hello", "Xin chào ", "\"/həˈləʊ/\"", "Hello bro ", "","https://img.freepik.com/free-vector/calligraphic-writing-hello_1020-450.jpg?semt=ais_hybrid&w=740" ));
        vocabs.add(new Vocab(5, "Hello", "Xin chào ", "\"/həˈləʊ/\"", "Hello bro ", "","https://img.freepik.com/free-vector/calligraphic-writing-hello_1020-450.jpg?semt=ais_hybrid&w=740" ));
        vocabs.add(new Vocab(6, "Hello", "Xin chào ", "\"/həˈləʊ/\"", "Hello bro ", "","https://img.freepik.com/free-vector/calligraphic-writing-hello_1020-450.jpg?semt=ais_hybrid&w=740" ));
        vocabs.add(new Vocab(7, "Hello", "Xin chào ", "\"/həˈləʊ/\"", "Hello bro ", "","https://img.freepik.com/free-vector/calligraphic-writing-hello_1020-450.jpg?semt=ais_hybrid&w=740" ));
        vocabs.add(new Vocab(8, "Hello", "Xin chào ", "\"/həˈləʊ/\"", "Hello bro ", "","https://img.freepik.com/free-vector/calligraphic-writing-hello_1020-450.jpg?semt=ais_hybrid&w=740" ));
        vocabs.add(new Vocab(9, "Hello", "Xin chào ", "\"/həˈləʊ/\"", "Hello bro ", "","https://img.freepik.com/free-vector/calligraphic-writing-hello_1020-450.jpg?semt=ais_hybrid&w=740" ));
        vocabs.add(new Vocab(10, "Hello", "Xin chào ", "\"/həˈləʊ/\"", "Hello bro ", "","https://img.freepik.com/free-vector/calligraphic-writing-hello_1020-450.jpg?semt=ais_hybrid&w=740" ));


        RecyclerView recyclerView = findViewById(R.id.vocabRecyclerView);
        VocabAdapter adapter = new VocabAdapter(this, vocabs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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