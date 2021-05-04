package storage;

import model.Book;

import java.io.*;

import static controller.Library.BOOK_LIST;

public class IOFile {
    public static void writeBookList() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Library.doc"));
            for (Book book : BOOK_LIST) {
                bufferedWriter.write(book.toString1());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBookList() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Library.doc"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
