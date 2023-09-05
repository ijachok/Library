package me.ijachok.library;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private String imageURL;
    private int pages;
    private int id;
    private String shortDescription;
    private String longDescription;
    private boolean isExpanded;
    private static int count = 0;

    public Book(String title, String author, String imageURL, int pages, String shortDescription, String longDescription) {
        count++;
        this.id = count;
        this.title = title;
        this.author = author;
        this.imageURL = imageURL;
        this.pages = pages;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.isExpanded = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages &&
                id == book.id &&
                title.equals(book.title) &&
                author.equals(book.author) &&
                imageURL.equals(book.imageURL) &&
                Objects.equals(shortDescription, book.shortDescription)
                && Objects.equals(longDescription, book.longDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, imageURL, pages, id, shortDescription, longDescription);
    }
}
