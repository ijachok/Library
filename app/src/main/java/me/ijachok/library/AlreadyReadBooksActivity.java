package me.ijachok.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AlreadyReadBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_books);

        RecyclerView recyclerView = findViewById(R.id.alreadyReadBooksRecView);

        BookRecViewAdapter adapter = new BookRecViewAdapter(this);
        adapter.setBooks(Database.getAlreadyReadBooks());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}