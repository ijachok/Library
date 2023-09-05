package me.ijachok.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class CurrentlyReadingBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentlyreading_books);

        RecyclerView recyclerView = findViewById(R.id.currentlyReadingBooksRecView);

        BookRecViewAdapter adapter = new BookRecViewAdapter(this);
        adapter.setBooks(Database.getCurrentlyReadingBooks());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}