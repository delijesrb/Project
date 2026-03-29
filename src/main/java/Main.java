import core.Book;
import services.BookHandler;
import services.Storage;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage("Library.txt");

        List<Book> books =storage.loadBooks();

        BookHandler bookHandler = new BookHandler(books);

        int message ;


        boolean control = true;
        String title;


        Scanner scanner =  new Scanner(System.in);
        while (control) {
            System.out.println("1. ADD NEW BOOK \n"+
                                "2. SHOW BOOKS LIBRARY\n" +
                                "3. SEARCH BOOK BY TITLE \n"+
                                "4. SORT LIBRARY\n"+
                                "5. LOAN BOOK \n"+
                                "6. RETURN BOOK  \n"+
                                "7. REMOVE BOOK FROM LIBRARY \n"+
                                "8. EXIT \n"+
                                "______________\n");
            System.out.println("PLEASE TAKE A CHOOSE");

            try {
                message = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Only number ar available!");
                message = 0;
                scanner.nextLine();
            }

            switch (message){
                case  1:
                    System.out.println("Please enter book name");

                    scanner.nextLine();

                    String bookTitle = scanner.nextLine();
                    System.out.println("Please enter author name: ");
                    String bookAuthor = scanner.nextLine();
                    Book book = new Book(bookTitle,bookAuthor);

                    bookHandler.addBook(book);
                    break;
                case 2:
                    bookHandler.showAllBooks();
                    break;

                case 3:
                    System.out.println("Please enter book name: ");
                    scanner.nextLine();
                    title = scanner.nextLine();
                    bookHandler.showBookByTitle(title);
                    break;

                case 4:
                    System.out.println("1.SORT BY ID: \n" +
                            "2.SORT BY TITLE: \n" +
                            "3.SORT BY AUTHOR: \n"+
                            "4.SHOW ONLY AVAILABLE");
                    System.out.println("-------------------");
                    System.out.println("PLEASE TAKE A CHOOSE");
                    int sortCondition ;
                    try {
                        sortCondition = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Only number ar available!");
                        sortCondition = 0;
                        scanner.nextLine();
                    }
                    switch (sortCondition){

                        case 1-> {books.sort(Comparator.comparing(Book::getId));
                            for (Book b:books

                            ) {
                                System.out.println(b);
                            }
                        }
                        case 2-> {books.sort(Comparator.comparing(Book::getTitle));
                            for (Book book1 : books) {
                                System.out.println(book1);
                            }
                            }

                        case 3-> {
                            books.sort(Comparator.comparing(Book::getAuthor));
                            for (Book book1 : books) {
                                System.out.println(book1);
                            }

                        }
                        case 4-> {
                            for (Book book1 : books) {
                                if (!book1.isBorrowed()) {
                                    System.out.println(book1);
                                }
                            }
                        }
                        default -> {
                            return;
                        }
                    }

                    break;
                case 5:
                    System.out.println("Please enter book name: ");
                    scanner.nextLine();
                    title = scanner.nextLine();
                    bookHandler.loanBook(title);

                    break;

                case 6:
                    System.out.println("Please enter book name : ");
                    scanner.nextLine();
                    title = scanner.nextLine();
                    bookHandler.restoreBook(title);

                    break;

                case 7:
                    int id = 0;
                    try {
                        System.out.println("Please enter book id: ");
                        id = scanner.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("Only number ar available!!");
                        scanner.nextLine();
                    }
                    bookHandler.remBook(id);
                    break;
                case 8:
                    control = false;
                    storage.storageBooks(books);
                    break;
            }


        }

    }
}
