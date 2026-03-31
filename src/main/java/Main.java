import core.Book;
import services.BookHandler;
import services.Storage;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage("Library.txt");

        List<Book> books = storage.loadBooks();

        BookHandler bookHandler = new BookHandler(books);




        boolean control = true;


        Scanner scanner = new Scanner(System.in);
        while (control) {
            System.out.println("1. ADD NEW BOOK \n" +
                    "2. SHOW BOOKS LIBRARY\n" +
                    "3. SEARCH BOOK BY TITLE \n" +
                    "4. SORT LIBRARY\n" +
                    "5. LOAN BOOK \n" +
                    "6. RETURN BOOK  \n" +
                    "7. REMOVE BOOK FROM LIBRARY \n" +
                    "8. EXIT \n" +
                    "______________\n");
            System.out.println("PLEASE TAKE A CHOOSE");
            int entry = 0;


            switch (getEntry(entry, scanner)) {
                case 1 -> addBook(scanner, bookHandler);
                case 2 -> bookHandler.showAllBooks();
                case 3 -> bookHandler.showBookByTitle(getTitle(scanner));
                case 4 -> {
                    System.out.println("1.SORT BY ID: \n" +
                            "2.SORT BY TITLE: \n" +
                            "3.SORT BY AUTHOR: \n" +
                            "4.SHOW ONLY AVAILABLE");
                    System.out.println("-------------------");
                    System.out.println("PLEASE TAKE A CHOOSE");
                    int sortCondition = 0;

                    switch (getEntry(sortCondition, scanner)) {

                        case 1 -> {
                            books.sort(Comparator.comparing(Book::getId));
                            for (Book b : books

                            ) {
                                System.out.println(b);
                            }
                        }
                        case 2 -> {
                            books.sort(Comparator.comparing(Book::getTitle));
                            for (Book book1 : books) {
                                System.out.println(book1);
                            }
                        }

                        case 3 -> {
                            books.sort(Comparator.comparing(Book::getAuthor));
                            for (Book book1 : books) {
                                System.out.println(book1);
                            }

                        }
                        case 4 -> {
                            for (Book book1 : books) {
                                if (!book1.isBorrowed()) {
                                    System.out.println(book1);
                                }
                            }
                        }
                        default -> System.err.println("Please take a choose from meni (1-4)");
                    }
                }
                case 5 -> bookHandler.loanBook(getTitle(scanner));
                case 6 -> bookHandler.restoreBook(getTitle(scanner));
                case 7 -> {
                    int id = 0;
                    System.out.println("Please enter book ID to remove it from library: ");
                    bookHandler.remBook(getEntry(id, scanner));
                }
                case 8 -> {
                    control = false;
                    storage.storageBooks(books);
                    scanner.close();
                }
                default -> System.err.println("Please take a choose from meni (1-8)");
            }


        }

    }

    private static int getEntry(int entry, Scanner scanner) {
        try {
            entry = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Only number ar available!");
            entry = 0;
            scanner.nextLine();
        }
        return entry;
    }

    private static String getTitle(Scanner scanner) {
        String title;
        System.out.println("Please enter book name: ");
        scanner.nextLine();
        title = scanner.nextLine();
        return title;
    }

    private static void addBook(Scanner scanner, BookHandler bookHandler) {
        System.out.println("Please enter book name");

        scanner.nextLine();

        String bookTitle = scanner.nextLine();
        System.out.println("Please enter author name: ");
        String bookAuthor = scanner.nextLine();
        Book book = new Book(bookTitle, bookAuthor);

        bookHandler.addBook(book);
        return;
    }
}
