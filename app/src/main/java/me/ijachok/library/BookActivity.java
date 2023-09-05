package me.ijachok.library;

import static me.ijachok.library.BookRecViewAdapter.BOOK_ID_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

public class BookActivity extends AppCompatActivity {
    private ShapeableImageView imageBookCover;
    private MaterialButton buttonStartReading, buttonWantToRead, buttonMarkAsRead, buttonAddToFavourites;
    private TextView textTitleValue, textAuthorValue, textShDesc, textPagesValue, textLongDescription;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        imageBookCover = findViewById(R.id.imageBookCover);

        buttonStartReading = findViewById(R.id.buttonStartReading);
        buttonWantToRead = findViewById(R.id.buttonReadLater);
        buttonMarkAsRead = findViewById(R.id.buttonMarkAsRead);
        buttonAddToFavourites = findViewById(R.id.buttonAddToFavourites);

        textTitleValue = findViewById(R.id.textTitleValue);
        textAuthorValue = findViewById(R.id.textAuthorValue);
        textShDesc = findViewById(R.id.textShDesc);
        textPagesValue = findViewById(R.id.textPagesValue);
        textLongDescription = findViewById(R.id.textLongDescription);

        parent = findViewById(R.id.bookConstraintLayout);

        Intent intent = getIntent();

        if (intent != null) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book book = Database.getInstance().findBookById(bookId);
                if (book != null) {
                    setBook(book);

                    handleFavourites(book);
                    handleCurrentlyReading(book);
                    handleReadLater(book);
                    handleAlreadyRead(book);
                }
            }
        }

    }

    private void handleFavourites(Book book) {
        boolean isFavourite = Database.getInstance().isFavourite(book);

        if (isFavourite) {
            buttonAddToFavourites.toggle();
        }

        buttonAddToFavourites.addOnCheckedChangeListener((button, isChecked) -> {

            if (isChecked) {
                if (!Database.getInstance().addToFavourites(book)) {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            } else {
                Database.getInstance().removeFavourite(book);
            }
        });
    }

    private void handleCurrentlyReading(Book book) {
        boolean isCurrentlyReading = Database.getInstance().isCurrentlyReading(book);

        if (isCurrentlyReading) {
            buttonStartReading.toggle();
            // TODO: change "want to read" button
        }
        buttonStartReading.addOnCheckedChangeListener((button, isChecked) -> {

            if (isChecked) {
                if (!Database.getInstance().addToCurrentlyReading(book)) {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            } else {
                Database.getInstance().removeCurrentlyReading(book);
                Toast.makeText(this, "removed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void handleReadLater(Book book) {
        boolean isReadLater = Database.getInstance().isInReadLater(book);

        if (isReadLater) {
            buttonWantToRead.toggle();
        }
        buttonWantToRead.addOnCheckedChangeListener((button, isChecked) -> {

            if (isChecked) {
                if (!Database.getInstance().addToReadLater(book)) {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            } else {
                Database.getInstance().removeReadLater(book);
            }
        });

    }

    private void handleAlreadyRead(Book book) {
        boolean isAlreadyRead = Database.getInstance().isAlreadyRead(book);

        if (isAlreadyRead) {
            buttonMarkAsRead.toggle();
        }
        buttonMarkAsRead.addOnCheckedChangeListener((button, isChecked) -> {

            if (isChecked) {
                if (!Database.getInstance().addToAlreadyRead(book)) {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            } else {
                Database.getInstance().removeAlreadyRead(book);
            }
        });

    }

    private void setBook(Book book) {
        Glide.with(this).asBitmap().load(book.getImageURL()).into(imageBookCover);
        textTitleValue.setText(book.getTitle());
        textShDesc.setText(book.getShortDescription());
        textAuthorValue.setText(book.getAuthor());
        textPagesValue.setText(book.getPages() + "");
        textLongDescription.setText(book.getLongDescription());
    }
}