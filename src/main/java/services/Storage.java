package services;

import core.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private  String fileName ;
     static String file = "";

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public  List<Book> loadBooks(){
        List<Book> books = new ArrayList<>();

        String file = "";
        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(fileName))){

            int r ;
            while ((r =  fis.read())!=-1){

                file += (char)r;



            }

        } catch (FileNotFoundException e) {
            System.err.println("FILE DOS NOT EXIST "+ e.getMessage());
        } catch (IOException e) {
            System.err.println("ERROR BY FILE RIDING  "+ e.getMessage());
        }
        String[] line = file.split("\n");

        for (int i = 0; i < line.length; i++) {
            String[] model = line[i].split(",");
            if (model.length == 4){


                Book book = new Book(Integer.parseInt(model[0]),model[1],model[2],Boolean.parseBoolean(model[3].trim()));
                Book.counter = book.getId();

                books.add(book);
            }
        }


        return books;
    }
    public void storageBooks(List<Book> books){
        try ( FileWriter fileWriter = new FileWriter(new File(fileName));){

            for (int i = 0; i < books.size(); i++) {
                Book b = books.get(i);
                fileWriter.write(b.getId()+","+b.getTitle()+","+b.getAuthor()+","+b.isBorrowed()+"\n");
            }

        } catch (IOException e) {
            System.err.println("FILE WRITING ERROR");
        }
    }
}
