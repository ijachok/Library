package me.ijachok.library;

import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    public static final String BOOK_ID_KEY = "bookId";
    private List<Book> books = new ArrayList<>();
    private final Context context;
    private boolean isFavouriteBooksActivity;

    public BookRecViewAdapter(Context context) {
        this.context = context;
    }

    public BookRecViewAdapter(Context context, boolean isFavouriteBooksActivity) {
        this.context = context;
        this.isFavouriteBooksActivity = isFavouriteBooksActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(books.get(position).getTitle());
        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtShDescription.setText(books.get(position).getShortDescription());
        Glide.with(context).asBitmap().load(books.get(position).getImageURL()).into(holder.bookCoverImage);

        if (Database.getInstance().isFavourite(books.get(position))) {
            holder.btnToggleFav.toggle();
        }

        holder.btnToggleFav.addOnCheckedChangeListener((button, isChecked) -> {
            if (isChecked) {
                if (!Database.getInstance().addToFavourites(books.get(position))) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            } else {
                Database.getInstance().removeFavourite(books.get(position));
                if (isFavouriteBooksActivity) {
                    notifyItemRemoved(position);
                }
            }
        });

        holder.parent.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookActivity.class);
            intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
            context.startActivity(intent);
        });

        if (books.get(holder.getAdapterPosition()).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setRotation(180);
            holder.parent.setElevation(10);
        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MaterialCardView parent;
        private final ShapeableImageView bookCoverImage;
        private final TextView txtTitle, txtShDescription, txtAuthor;
        private final ImageView downArrow;
        private final RelativeLayout expandedRelLayout;
        private final MaterialButton btnToggleFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.bookCardView);
            bookCoverImage = itemView.findViewById(R.id.bookCoverImage);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtShDescription = itemView.findViewById(R.id.txtShDescription);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            downArrow = itemView.findViewById(R.id.downArrow);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            btnToggleFav = itemView.findViewById(R.id.btnToggleFav);

            downArrow.setOnClickListener(view -> {
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }

}
