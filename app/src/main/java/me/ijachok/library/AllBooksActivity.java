package me.ijachok.library;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView AllBooksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);



        AllBooksRecView = findViewById(R.id.allBooksRecView);
        adapter = new BookRecViewAdapter(this);
        adapter.setBooks(Database.getAllBooks());

        AllBooksRecView.setAdapter(adapter);
        AllBooksRecView.setLayoutManager(new LinearLayoutManager(this));
    }



}