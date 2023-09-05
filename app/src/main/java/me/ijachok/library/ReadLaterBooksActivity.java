package me.ijachok.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ReadLaterBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_later_books);
        RecyclerView recyclerView = findViewById(R.id.readLaterBooksRecView);

        BookRecViewAdapter adapter = new BookRecViewAdapter(this);
        adapter.setBooks(Database.getReadLaterBooks());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}