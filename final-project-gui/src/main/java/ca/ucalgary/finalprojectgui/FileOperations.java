package ca.ucalgary.finalprojectgui;
import java.io.*;
import java.util.*;
/**
 * Names: Isaac, Halima, Zainab
 * UCIDs: 30219835, 30208584, 10192965
 * Date: 2024-03-21
 * Tutorials: Tut 19, Tut 16, Tut 16
 */

/**
 * 7) Program Options:
 * Save data
 * Load data
 */
public class FileOperations {
    public static void saveToFileData(File filename, List<BookData> bookList) throws IOException {
        //Takes an input filename and the bookList to read from.
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < bookList.size(); i++) {
            //Loops through each object in bookList, retrieving all object information
            //Stores it in a String called line, separating values by commas.
            BookData book = bookList.get(i);
            String line = book.getTitle() + "," +
                    book.getAuthor() + "," +
                    book.getGenre() + "," +
                    book.getRating() + "," +
                    book.getYearPublished();
            line = line.replace("\n", " ");
            //line is written to filename and the loop repeats for every subsequent object.
            bw.write(line + "\n");
        }
        bw.close();
        fw.close();
    }
    public static List<BookData> readFromFileData(File filename) throws IOException {
        //A list of the saved books is initialized.
        List<BookData> savedBookList = new ArrayList<>();
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            //filename.cvs is read, and each entry separated by a comma is assigned to
            //the objects corresponding attributes.
            //Each new line signifies a new object.
            String[] data = line.split(",");
            BookData book = new BookData(data[0].trim(),
                    data[1].trim(),
                    data[2].trim(),
                    Double.parseDouble(data[3].trim()),
                    Integer.parseInt(data[4].trim()));
            //The object created from the file is added to the saved books list
            savedBookList.add(book);
        }
        br.close();
        fr.close();
        //The saved books list is returned to be converted to currentBook in Menu.java.
        return savedBookList;
    }
    public static void saveToFileWishlist(File filename, List<BookWishlist> wishList) throws IOException {
        //Takes an input filename and the wishlist to read from.
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < wishList.size(); i++) {
            //Loops through each object in wishlist, retrieving all object information
            //Stores it in a String called line, separating values by commas.
            BookWishlist book = wishList.get(i);
            String line = book.getTitle() + "," +
                    book.getAuthor() + "," +
                    book.getGenre() + "," +
                    book.getRating() + "," +
                    book.getYearPublished();
            line = line.replace("\n", " ");
            //line is written to filename and the loop repeats for every subsequent object.
            bw.write(line + "\n");
        }
        bw.close();
        fw.close();
    }
    public static List<BookWishlist> readFromFileWishlist(File filename) throws IOException {
        //A list of the saved wishlist books is initialized.
        List<BookWishlist> savedWishlist = new ArrayList<>();
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            //filename.cvs is read, and each entry separated by a comma is assigned to
            //the objects corresponding attributes.
            //Each new line signifies a new object.
            String[] data = line.split(",");
            BookWishlist book = new BookWishlist(data[0].trim(),
                    data[1].trim(),
                    data[2].trim(),
                    Double.parseDouble(data[3].trim()),
                    Integer.parseInt(data[4].trim()));
            //The object created from the file is added to the saved wishlist books list
            savedWishlist.add(book);
        }
        br.close();
        fr.close();
        //The saved wishlist books list is returned to be converted to wish in Menu.java.
        return savedWishlist;
    }
}