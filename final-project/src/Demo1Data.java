import java.util.*;

public class Demo1Data {
    /**
     * For simplicity, we decided to use four HashMaps, which share the same keys
     * but contain different types of values.
     */
    private static final HashMap<String, String> authorMap = new HashMap<>();
    private static final HashMap<String, String> genreMap = new HashMap<>();
    private static final HashMap<String, Double> ratingMap = new HashMap<>();
    private static final HashMap<String, Integer> yearMap = new HashMap<>();
    /**
     * We have a single ArrayList called wishlist to optionally put any book
     * into your wishlist to be viewed later on.
     */
    private static final ArrayList<String> wishlist = new ArrayList<>();

    /**
     *Getters to be used in junit testing
     */
    public static HashMap<String, String> getAuthorMap() {
        return new HashMap<>(authorMap);
    }
    public static HashMap<String, String> getGenreMap() {
        return new HashMap<>(genreMap);
    }
    public static HashMap<String, Double> getRatingMap() {
        return new HashMap<>(ratingMap);
    }
    public static HashMap<String, Integer> getYearMap() {
        return new HashMap<>(yearMap);
    }
    public static ArrayList<String> getWishlist() {
        return new ArrayList<>(wishlist);
    }

    /**
     * Add data (general categories)
     * Please select one of the following inputs:
     * 1) Add a book
     * Add a book title
     * Add rating
     * Add genre
     * Add year published
     * Add author
     */

    public static void addABook(String title, double rating, int year, String author, String genre) {
        if (Demo1Data.authorMap.containsKey(title)) {
            System.out.println("The book '" + title + "' already exists in the library!");
        }

        // Add the book to the database
        // 4 separate HashMaps
        Demo1Data.authorMap.put(title, author);
        Demo1Data.genreMap.put(title, genre);
        Demo1Data.ratingMap.put(title, rating);
        Demo1Data.yearMap.put(title, year);

        System.out.println("The book '" + title + "' has been added to the library!");
    }
    /**
     * 2) Remove a book: The remove a book function allows the user to either remove a singular book from the hashmaps or
     * remove all the books and their details from each hashmap
     * Remove a book - deletes a singular book
     * Remove all books - deletes all books
     */
    public static void removeABook(String title) {
        if (Demo1Data.authorMap.containsKey(title)) {
            //If authorMap has the key, then all maps do since they all have duplicate keys
            //so all maps get the same key removed.
            Demo1Data.authorMap.remove(title);
            Demo1Data.genreMap.remove(title);
            Demo1Data.ratingMap.remove(title);
            Demo1Data.yearMap.remove(title);

            System.out.println("The book '" + title + "' has been removed from the library!");
        } else {
            System.out.println("Book title not found. Please try again.");
        }
    }
    public static void removeAllBooks() {
        //Clears all HashMaps
        Demo1Data.authorMap.clear();
        Demo1Data.genreMap.clear();
        Demo1Data.ratingMap.clear();
        Demo1Data.yearMap.clear();
        System.out.println("All books have been removed from the library!");
    }
    /*** 3) Searching function: The searching function is supposed to search a book or books by either the author, book
     * title, genre or the rating of the book by user input. The input is then compared to the hashmap and if it is in the
     * hashmap then the title of the book/books with using the corresponding values.
     * Search by author - if the author exists in the author hashmap, the books by the author is then printed out
     * Search by title name - if the title exists in the author hashmap, the title, along with the book's author,
     * genre, and rating is printed
     * Search by genre - if the genre exists in the genre hashmap, the books of that genre are printed out
     * Search by rating - if a book/books of the specified rating exists, the books are printed out
     * */
    public static String searchByAuthor(String author) {
        if(Demo1Data.authorMap.containsValue(author)){
            //if the author is contained in the value of the authorMap hashmap
            //System.out.println("Books by " + author + ":");
            for(Map.Entry<String, String> entry : Demo1Data.authorMap.entrySet()){
                //each key,value pair is retrieved then if the key value is equal to the users input then
                // the key is extracted and printed
                if(entry.getValue().equals(author)){
                    System.out.println("Books by " + author + ":\nTitle: " + entry.getKey());
                    return "Books by " + author + ":\nTitle: " + entry.getKey();
                }
            }
        }
        System.out.println("Invalid entry. Author does not exist.");
        return "Invalid entry. Author does not exist.";

    }
    public static String searchByTitle(String title) {
        if(Demo1Data.authorMap.containsKey(title)){
            //each key,value pair is retrieved then if the key value is equal to the users input then
            // the key is extracted and printed
            System.out.println("Title: " + title +
            "\nAuthor: " + Demo1Data.authorMap.get(title) +
                    "\nGenre: " + Demo1Data.genreMap.get(title) +
                    "\nRating: " + Demo1Data.ratingMap.get(title) +
                    "\nYear Published: " + Demo1Data.yearMap.get(title));
            return
                    "Title: " + title +
                            "\nAuthor: " + Demo1Data.authorMap.get(title) +
                            "\nGenre: " + Demo1Data.genreMap.get(title) +
                            "\nRating: " + Demo1Data.ratingMap.get(title) +
                            "\nYear Published: " + Demo1Data.yearMap.get(title);
        } else {
            //invalid input
            System.out.println("Invalid entry. Title does not exist.");
            return "Invalid entry. Title does not exist.";
        }
    }
    public static String searchByGenre(String genre) {
        if(Demo1Data.genreMap.containsValue(genre)) {
            //if the genre is a value in the genreMap, prints out all books containing that genre
            //System.out.println("Books with the " + genre + " genre:");
            for (Map.Entry<String, String> entry : Demo1Data.genreMap.entrySet()) {
                //each key,value pair is retrieved then if the key value is equal to the users input then
                // the key is extracted and printed
                if (entry.getValue().equals(genre)) {
                    System.out.println("Books with the " + genre + " genre:\nTitle: " + entry.getKey());
                    return "Books with the " + genre + " genre:\nTitle: " + entry.getKey();
                }
            }
        }
        System.out.println("Invalid entry, genre does not exist");
        return "Invalid entry, genre does not exist";

    }
    public static String searchByRating(double rating) {
        if (rating < 1 || rating > 5) {
            //checks to see if an invalid input of a number less than 1 or greater than 5 is input and gives
            // an error message and allows the user to input a valid rating
            System.out.println("Invalid rating. Please enter a number between 1 and 5:");
            return "Invalid rating. Please enter a number between 1 and 5:";
        }

        System.out.println("Books with a "+ rating +" rating: ");
        for (Map.Entry<String, Double> entry : Demo1Data.ratingMap.entrySet()) {
            if (entry.getValue().equals(rating)) {
                //if value matches a value in the key value pair then the title(key) is retrieved and printed
                System.out.println("Books with a "+ rating +" rating:\nTitle: " + entry.getKey());
                return "Books with a "+ rating +" rating:\nTitle: " + entry.getKey();
            }
        }
        return null;
    }
    /**
     * 4) Options: The options functions allows the user to edit either the rating, genre, year published, or the author
     * and gives the user an option to add a book title to their wishlist
     * Edit the book - primary option
     * Edit rating - sub option
     * Edit genre - sub option
     * Edit year published - sub option
     * Edit author - sub option
     * Add to wishlist
     * Remove from wishlist
     **/
    public static void editBookAuthor(String title, String newAuthor) {
        if(Demo1Data.authorMap.containsKey(title)){
            //if book title is in the map
            Demo1Data.authorMap.replace(title, newAuthor);
            //replace the old author with the new author input at the previous value
            System.out.println("The book: " + title + ", has been assigned a new author, " + newAuthor + ".");
        } else {
            System.out.println("Invalid entry. Book title does not exist.");
        }
    }
    public static void editBookGenre(String title, String newGenre) {
        if(Demo1Data.genreMap.containsKey(title)){
            //if title in map
            Demo1Data.genreMap.replace(title, newGenre);
            //replace old genre with new genre in value
            System.out.println("The book: " + title + ", has been assigned a new genre, " + newGenre + ".");
        } else {
            System.out.println("Invalid entry. Book title does not exist.");
        }
    }
    public static void editBookRating(String title, double newRating) {
        if (newRating >= 1 && newRating <= 5) {
            //checks to see if an invalid input of a number less than 1 or greater than 5 is input and gives
            // an error message and allows the user to input a valid rating
            if (Demo1Data.ratingMap.containsKey(title)) {
                Demo1Data.ratingMap.replace(title, newRating);
                //replace old rating with new rating in value
                System.out.println("The book: " + title + ", has been assigned a new rating of: " + newRating + "!");
            } else {
                System.out.println("Invalid entry. Book title does not exist.");
            }
        } else {
            System.out.println("Invalid rating. Please enter a number between 1 and 5.");
        }
    }
    public static void editBookYear(String title, int newYear) {
        if (newYear > 0 && newYear <= 2024) {
            //checks to see if an invalid input of a number less than 1 or greater than the current
            // year is input and gives an error message and allows the user to input a valid rating
            if(Demo1Data.yearMap.containsKey(title)){
                Demo1Data.yearMap.replace(title, newYear);
                System.out.println("The book: " + title + ", has been assigned a new year of: " + newYear + "!");
            } else {
                System.out.println("Invalid entry. Book title does not exist.");
            }
        } else {
            System.out.println("Invalid year. Please enter a valid year.");
        }
    }
    public static void addToWishlist(String bookTitle) {
        if (Demo1Data.wishlist.contains(bookTitle)) {
            System.out.println(bookTitle + " is already in your wishlist.");
        } else {
            Demo1Data.wishlist.add(bookTitle);
            System.out.println(bookTitle + " has been added to your wishlist.");
        }
    }
    public static void removeFromWishlist(String bookTitle) {
        if (Demo1Data.wishlist.isEmpty()) {
            //if the wishlist does not contain any value/ is empty
            System.out.println("Your wishlist is empty. This is nothing to remove");
        } else if (Demo1Data.wishlist.contains(bookTitle)) {
            Demo1Data.wishlist.remove(bookTitle);
            System.out.println(bookTitle + " has been removed from your wishlist.");
        } else {
            System.out.println("The book is not in your wishlist.");
        }
    }
    public static void clearWishlist(){
        Demo1Data.wishlist.clear();
    }
    /**
     * 5) Output General:
     * Print the entire database:
     */
    public static void printDataBase() {

        int entry = 1;
        for (String title : Demo1Data.authorMap.keySet()) {
            //for each key in authorMap (since all of them share the same keys)
            //assign the value to ____ from the ____Map(key)
            String author = Demo1Data.authorMap.get(title);
            String genre = Demo1Data.genreMap.get(title);
            Double rating = Demo1Data.ratingMap.get(title);
            Integer year = Demo1Data.yearMap.get(title);

            System.out.println(
                    "Entry Number: " + entry +
                            "\nTitle: " + title +
                            "\nAuthor: " + author +
                            "\nGenre: " + genre +
                            "\nRating: " + rating +
                            "\nYear Published: " + year +
                            "\n");
            entry++; //print out every entry in the database
        }
    }
    /**
     * 6) Output Special: has a sub menu that allows you to select more options
     * Highest rated (out of 5 stars)
     * Recommendations
     * Newest Entries
     * View wishlist
     * Back to menu
     */
    public static void highestRated() {
        if (!Demo1Data.ratingMap.isEmpty()) {
            double largestRating = 0; //stores the highest rating
            String topRated = ""; //stores the title of the highest rated book
            for (Map.Entry<String, Double> entry : Demo1Data.ratingMap.entrySet()) { //iterates through the entries in ratingMap
                if (largestRating < entry.getValue()) {
                    largestRating = entry.getValue(); //largestRating is updated to the rating of the current book.
                    topRated = entry.getKey() + "'"; //updated to store the title associated with the highest rating
                } else if (largestRating == entry.getValue()) { //two books have the same rating
                    topRated = topRated + (", and '" + entry.getKey() + "'"); //both names are added to the string
                }
            }
            System.out.println("The highest rated book(s) is/are: '" + topRated + " with a rating of: " +largestRating);
        } else {
            System.out.println("The library is Empty");
        }
    }
    public static void recommendation() {
        //Recommendations
        if (!Demo1Data.authorMap.isEmpty()) {
            int index = (int) (Math.random() * Demo1Data.authorMap.size()); //generates a random index from 0 to the length of the authormap hashmap
            Iterator<String> iterate = Demo1Data.authorMap.keySet().iterator(); //iterate through the authorMap
            for (int i = 0; i < index; i++) { //iterates through the iterator "index" number of times
                iterate.next(); //gets the next key in the map
            }
            String randomBook = iterate.next(); //random book selected from the map
            System.out.println("We recommend: " + randomBook);
        } else {
            System.out.println("There are no books in the library to recommend.");
        }
    }
    public static void newestEntry(){
        //Most Recent Entry: last entry in the arraylist
        //convert the authorMap into an arraylist
        List<String> values = new ArrayList<>(Demo1Data.authorMap.keySet()); //access the titles
        if(values.size() > 0){
            int lastIndex = values.size()-1;
            String recent = values.get(lastIndex);
            System.out.println("Recently added: " + recent);
        } else {
            System.out.println("There are no recent entries.");
        }
    }
    public static void printWishlist(){
        if (Demo1Data.wishlist.size() > 0) {
            System.out.println("Wishlist:" + Demo1Data.wishlist); //print out wishlist
        } else {
            System.out.println("Wishlist is Empty.");
        }
    }
    public static void exitProgram() {
        //force closes the program, ignores the while loop in menu.
        System.out.println("Exiting Program!");
        System.exit(0);
    }
}
