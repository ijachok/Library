package me.ijachok.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.color.DynamicColors;

public class MainActivity extends AppCompatActivity {
    Button btnAllBooks, btnFavourites, btnCurReading,btnReadLater, btnAlRead, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DynamicColors.applyToActivitiesIfAvailable(this.getApplication());
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnFavourites = findViewById(R.id.btnFavourites);
        btnCurReading = findViewById(R.id.btnCurReading);
        btnReadLater = findViewById(R.id.btnReadLater);
        btnAlRead = findViewById(R.id.btnAlRead);
        btnAbout = findViewById(R.id.btnAbout);

        btnAllBooks.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, AllBooksActivity.class)));

        btnFavourites.setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, FavouriteBooksActivity.class);
            intent.putExtra("isFavourite", 1);
            startActivity(intent);
        });

        btnCurReading.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, CurrentlyReadingBooksActivity.class)));

        btnReadLater.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, ReadLaterBooksActivity.class)));

        btnAlRead.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, AlreadyReadBooksActivity.class)));

        Database.getInstance();

    }

}