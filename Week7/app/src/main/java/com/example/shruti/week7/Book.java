package com.example.shruti.week7;

/**
 * Created by shruti on 4/2/2018.
 */

public class Book {

    private int id;

    private String title;
    private String author;
    private String rating;

    public Book() {
    }

    public Book(String title, String author, String rating) {
        super();
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setAuthor(String author) {
        this.author = author;
    }




    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
