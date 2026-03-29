package core;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private int id;
    public static int counter;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book() {
    }


    public Book(int id, String title, String author, boolean isBorrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
    }

    public Book(String title, String author) {

        this.id = ++counter;

        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public Book setId( ) {
        this.id = counter;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public Book setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isBorrowed);
    }

    @Override
    public String toString() {
        return "Book: " +
                "id=" + id +
                "| title='" + title + '\'' +
                "| author='" + author + '\'' +
                "| isBorrowed=" + isBorrowed;
    }
}
