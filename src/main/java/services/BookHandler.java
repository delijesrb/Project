package services;

import core.Book;

import java.util.ArrayList;
import java.util.List;

public class BookHandler {
    private List<Book> books;


    public BookHandler(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
        System.out.println("Book is successfully added too library ");
    }

    public void remBook(int id) {
        boolean removed = false;
        if (books.isEmpty()) {
            System.err.println("NO BOOKS IM LIBRARY");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId() == id) {
                books.remove(i);
                System.out.println("Book is successfully removed from library");
                removed = true;
            }
        }
        if (!removed) {
            System.err.println("No book with this ID!!");
        }

    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.err.println("NO BOOKS IN LIBRARY");
            return;
        }
        for (Book b : books
        ) {
            System.out.println(b);
        }
    }

    public void showBookByTitle(String title) {
        boolean found = false;
        if (books.isEmpty()) {
            System.out.println(
                    "NO BOOKS IN LIBRARY"

            );
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(book);
                found = true;
            }

        }
        if (!found) {
            System.out.println("BOOK WITH THIS NAME IS NOT FOUND");
        }

    }

    public void loanBook(String title) {
        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equalsIgnoreCase(title)) {
                found = true;
                if (!book.isBorrowed()) {
                    System.out.println("*** You kan have it ***\n");
                    book.setBorrowed(true);

                } else {
                    System.err.println(("***THIS BOOK IS ALREADY BORROWED***\n"));
                }
                return;
            }

        }
        if (!found) {
            System.err.println("*** BOOK WITH THIS NAME IS NOT FOUND *** \n");
        }
    }

    public void restoreBook(String title) {
        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equalsIgnoreCase(title)) {
                found = true;
                if (book.isBorrowed()) {
                    System.out.println("Thanks you");
                    book.setBorrowed(false);

                } else {
                    System.out.println(("YOU DON'T TAKE THIS BOOK FROM US"));
                }
                return;
            }

        }
        if (!found) {
            System.out.println("*** BOOK WITH THIS NAME IS NOT FOUND ***");
        }
    }

}
