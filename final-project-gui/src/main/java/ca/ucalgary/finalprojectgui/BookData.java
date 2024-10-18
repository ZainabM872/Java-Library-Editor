package ca.ucalgary.finalprojectgui;
import java.util.*;
/**
 * Names: Isaac, Halima, Zainab
 * UCIDs: 30219835, 30208584, 10192965
 * Date: Date: 2024-03-21
 * Tutorials: Tut 19, Tut 16, Tut 16
 */

public class BookData extends BookTemplate {
    //The list which holds all current books in the library, any books removed are not deleted, they are simply removed from the list.
    private static List<BookData> bookList = new ArrayList<>();
    //Constructor, initializes the BookData object.
    public BookData(String title, String author, String genre, double rating, int yearPublished) {
        super(title, author, genre, rating, yearPublished, BookTypes.BOOK);
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
    //Adds the BookData object to bookList if it does not already exist.
    public int addABook() {
        //Adds the book to the library.
        boolean contains = false;
        for (int i = 0; i < bookList.size(); i++) {
            BookData book = bookList.get(i);
            //extend equals
            //checks if there is any duplicate entries by comparing all attributes. Also prevents duplicate titles.
            if (this.equals(book) || this.getTitle().equalsIgnoreCase(book.getTitle())) {
                contains = true;
            }
        }
        if (contains) {
//            System.out.println("This book already exists in the library!");
            return 2;
        } else {
            bookList.add(this);
//            System.out.println("The book '" + this.getTitle() + "' has been added to the library!");
            return 1;
        }
    }
    /**
     * 2) Remove a book: The remove a book function allows the user to either remove a singular book object from bookList
     * or remove all book objects from the bookList.
     * Remove a book - deletes a singular book
     * Remove all books - deletes all books
     */
    //Removes the indicated book from the bookList based on its title
    public int removeABook(String title) {
        //Removes the indicated book from the library.
        if (!bookList.isEmpty()) {
            boolean contains = false;
            for (int i = 0; i < bookList.size(); i++) {
                BookData book = bookList.get(i);
                //If the indicated title matches with an object's title in the bookList
                if (book.getTitle().equalsIgnoreCase(title)) {
                    bookList.remove(book);
                    contains = true;
//                    System.out.println("The book '" + book.getTitle() + "' has been removed from the library!");
                    return 1;
                }
            //Otherwise the book title does not exist.
            if (!contains) {
//                System.out.println("Book title not found. Please try again.");
                return 2;
                }
            }
        } else {
//            System.out.println("Your library is empty!");
            return 3;
        }
        return 0;
    }

    public boolean removeAllBooks() {
        //Clears the library.
        //Clears bookList of all its objects.
        if (!bookList.isEmpty()) {
            bookList.clear();
//            System.out.println("All books have been removed from the library!");
            return true;
        } else {
//            System.out.println("Your library is already empty!");
            return false;
        }
    }

    /** 3) Searching function: The searching function is supposed to search for all books by the specified author, book
     * title, genre or the rating of the book by user input. The user input is taken and is compared to all objects in bookList
     * If the object contains the specified attribute, such as the requested author, contains will be set to true, and a
     * concatenated string of all books will be printed out. Otherwise, the entry is invalid and does not exist among the objects.
     */
    public String searchByAuthor(String author) {
        //Displays all books made by a specified author.
        //searchByAuthor iterates through bookList and interacts with each book object, comparing the user prompt
        //to the book.getAuthor() value. If equal, it adds the title of that book object to endString to be printed at the end of the loop.
        if (!bookList.isEmpty()) {
            boolean contains = false;
            String endString = "Books by " + author + ": \n";
            for (int i = 0; i < bookList.size(); i++) {
                BookData book = bookList.get(i);
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    endString += "Title: " + book.getTitle() + "\n";
                    contains = true;

                }
            }
            if (contains) {
                //System.out.print(endString);
                return endString;
            //returns a boolean for easier testing.
            } else {
                //System.out.println("Invalid entry. Author does not exist.");
                return ("Invalid");
            }
        } else {
            //System.out.println("Your library is empty!");
            return "";
        }

    }
    public String searchByTitle(String title) {
        //Displays the book with the specified title
        //searchByTitle iterates through bookList and interacts with each book object, comparing the user prompt
        //to the book.getTitle() value. If equal, it will print out the book, and it's information.
        if (!bookList.isEmpty()) {
            boolean contains = false;
            for (int i = 0; i < bookList.size(); i++) {
                BookData book = bookList.get(i);
                if (book.getTitle().equalsIgnoreCase(title)) {
                    String info = ("Title: " + title +
                            "\nAuthor: " + book.getAuthor() +
                            "\nGenre: " + book.getGenre() +
                            "\nRating: " + book.getRating() +
                            "\nYear Published: " + book.getYearPublished() + "\n");
                    contains = true;
                    return info;
                }
            }
            if (contains) {
            } else {
                //System.out.println("Invalid entry. Title does not exist");
                return "Invalid";
            }
        } else {
            //System.out.println("Your library is empty!");
            return "";
        }
        return "Error";
    }
    public String searchByGenre(String genre) {
        //Displays all books with the specified genre.
        //The exact same as searchByAuthor, but for genre.
        if (!bookList.isEmpty()) {
            boolean contains = false;
            String endString = "Books with the " + genre + " genre: \n";
            for (int i = 0; i < bookList.size(); i++) {
                BookData book = bookList.get(i);
                if (book.getGenre().equalsIgnoreCase(genre)) {
                    endString += "Title: " + book.getTitle() + "\n";
                    contains = true;
                }
            }
            if (contains) {
                //System.out.print(endString);
                return endString;
            } else{
                //System.out.println("Invalid entry. Genre does not exist.");
                return "Invalid";
            }
        } else{
            //System.out.println("Your library is empty!");
            return "";
        }
    }
    public String searchByRating(double rating){
            //Displays all books with the specified Rating
            //The exact same as searchByAuthor and searchByGenre
            if (!bookList.isEmpty()) {
                boolean contains = false;
                String endString = "Books with a " + rating + " rating: \n";
                for (int i = 0; i < bookList.size(); i++) {
                    BookData book = bookList.get(i);
                    if (book.getRating() == rating) {
                        endString += "Title: " + book.getTitle() + "\n";
                        contains = true;
                    }
                }
                if (contains) {
                    //System.out.print(endString);
                    return endString;
                } else {
                    //System.out.println("Invalid entry, there is no book with this rating.");
                    return "Invalid";
                }
            } else {
                //System.out.println("Your library is empty!");
                return "";
            }
        }
    /**
     * 4) Options: The options functions allows the user to edit either the rating, genre, year published, or the author
     * and gives the user an option to add a book title to their wishlist
     * Edit the book - primary option
         * Edit rating - sub option
         * Edit genre - sub option
         * Edit year published - sub option
         * Edit author - sub option
     * Return to menu
     **/
    public int editBookAuthor(String title, String newAuthor) {
        //Reassigns the Book's author to the user's prompt.
        //editBookAuthor iterates through bookList to compare the user's title prompt to the titles of all objects.
        //If a match is found, it will take the Book object and set its new Author using book.setAuthor(newAuthor).
        if(!bookList.isEmpty()) {
            boolean contains = false;
            //if book title is in the bookList
            for (int i = 0; i < bookList.size(); i++) {
                BookData book = bookList.get(i);
                if (book.getTitle().equalsIgnoreCase(title)) {
                    book.setAuthor(newAuthor);
                    contains = true;
                }
            }
            if (contains) {
//                System.out.println("The book: " + title + ", has been assigned a new author, " + newAuthor + ".");
                return 1;
            } //replace the old author with the new author input at the previous value
            else {
//                System.out.println("Invalid entry. Book title does not exist.");
                return 2;
            }
        }else{
//            System.out.println("Your library is empty!");
            return 3;
        }
    }
    public int editBookGenre(String title, String newGenre) {
        //Reassigns the Book's genre to the user's prompt.
        //Functionally the same as editBookAuthor, but for genre.
        if(!bookList.isEmpty()){
            boolean contains = false;
            for(int i = 0; i < bookList.size(); i++){
                BookData book = bookList.get(i);
                if(book.getTitle().equalsIgnoreCase(title)){
                    book.setGenre(newGenre);
                    contains = true;
                }
            }
            if(contains){
//                System.out.println("The book: " + title + ", has been assigned a new genre, " + newGenre + ".");
                return 1;
            }else {
//                System.out.println("Invalid entry. Book title does not exist.");
                return 2;
            }
        } else{
//            System.out.println("Your library is empty!");
            return 3;
        }
    }
    public int editBookRating(String title, double newRating) {
        //Reassigns the Book's rating to the user's prompt.
        //Functionally the same as editBookAuthor and editBookGenre, but also has a check for proper input between 1-5 in menu.java
        if (!bookList.isEmpty()) {
            for (int i = 0; i < bookList.size(); i++) {
                BookData book = bookList.get(i);
                if (book.getTitle().equalsIgnoreCase(title)) {
                    book.setRating(newRating);
//                    System.out.println("The book: " + title + ", has been assigned a new rating of: " + newRating + "!");
                    return 1;
                } else {
//                    System.out.println("Invalid entry. Book title does not exist.");
                    return 2;
                }
            }
        } else {
//            System.out.println("Your library is empty!");
            return 3;
        }
        return 0;
    }
    public int editBookYear(String title, int newYear) {
        //Reassigns the Book's year published to the user's prompt.
        //Functionally the same as the rest, also has a check to make sure the year entered is valid (0 < x < 2024).
        if (!bookList.isEmpty()) {
            for (int i = 0; i < bookList.size(); i++) {
                BookData book = bookList.get(i);
                if (book.getTitle().equalsIgnoreCase(title)) {
                    book.setYearPublished(newYear);
                    System.out.println("The book: " + title + ", has been assigned a new year of: " + newYear + "!");
                    return 1;
                } else {
                    System.out.println("Invalid entry. Book title does not exist.");
                    return 2;
                }
            }
        } else {
            System.out.println("Your library is empty!");
            return 3;
        }
        return 0;
    }

    /**
     * 5) Output General:
     * Prints the entire database:
     * Using toString, it displays all information about each book in the library.
     * Each book has its own entry number as well.
     *
     * FUNCTIONALITY REPLACED IN MAINCONTROLLER.
     */
    public void printDataBase() {
//        //initializes entry to 1, a counter goes up 1 per index in bookList.
//        //the object, book, is automatically converted to display its details by the @Override of toString.
//        int entry = 1;
//        if (!bookList.isEmpty()) {
//            for (int i = 0; i < bookList.size(); i++) {
//                BookData book = bookList.get(i);
//                //Uses toString to automatically convert book.
//                System.out.println("Entry Number: " + entry + book);
//                entry++;
//            }
//        } else {
//            System.out.println("Your library is empty.");
//        }
//    }
    }
    /**
     * 6) Output Special: has a sub menu that allows you to select more options
     * Highest rated (out of 5 stars)
     * Recommendations
     * Newest Entries
     * Back to menu
     */
    public String highestRated() {
        //Initializes largestRating to 0, every book object has its rating compared to largestRating.
        //Anytime book has a higher rating, that rating is assigned to be the largestRating, until the end of the loop.
        //In case any books have the same largestRating, they are concatenated to have multiple highest rated books.
        double largestRating = 0; //stores the highest rating
        String topRated = "";
        //The ArrayList which holds all highest rated books.
        if (!bookList.isEmpty()) {
            for (int i = 0; i < bookList.size(); i++) {
                BookData book = bookList.get(i);
                if (largestRating < book.getRating()) {
                    largestRating = book.getRating();
                    topRated = book.getTitle() + "' with a rating of: " + largestRating + "\n";
                } else if (largestRating == book.getRating()) {
                    topRated += "'" + book.getTitle() + "' with a rating of: " + largestRating + "\n";
                }
            }
           return ("The highest rated book(s) is/are: \n'" + topRated);
        } else {
            return ("Your library is Empty.");
        }
    }
    public BookData recommendation() {
        //Generates a random number up to a max size of the bookList.
        //This number is taken and used to index into bookList to retrieve a random book.
        //The book object has its title retrieved and is recommended to the user.
        if (!bookList.isEmpty()) {
            //Recommendations
            int randIndex = (int) (Math.random()*bookList.size()); //random index within size of booklist
            return bookList.get(randIndex); //get a random object
//            System.out.println("We recommend: " + bookList.get(randIndex).getTitle());
        }
        else {
            return null;
//            System.out.println("There are no books in the library to recommend.");
        }
    }
    public String newestEntry() {
        //Takes the very last index of the bookList and retrieves the book's title.
        //The most recent book should always be the last index in this program.
        if (!bookList.isEmpty()) {
            BookData book = bookList.getLast();
            return("Recently added: " + book.getTitle());
        } else {
            return("There are no recent entries.");
        }
    }
    //Overrides
    @Override
    public String toString() {
        //Automatically converts all BookData objects into this format if printed.
        return "\nTitle: " + getTitle() +
                "\nAuthor: " + getAuthor() +
                "\nGenre: " + getGenre() +
                "\nRating: " + getRating() +
                "\nYear Published: " + getYearPublished() + "\n";
    }
    @Override
    public boolean equals(Object object) {
        //Compares attributes of each object.
        //The objects must be an instance of BookData and have the exact same attributes to return true.
        //Otherwise, it's false.
        if (this == object) {
            return true;
        }
        if (!(object instanceof BookData)) {
            return false;
        }
        BookData data = (BookData) object;
        return  Objects.equals(getTitle(), data.getTitle()) &&
                Objects.equals(getAuthor(), data.getAuthor()) &&
                Objects.equals(getGenre(), data.getGenre()) &&
                Double.compare(data.getRating(), getRating()) == 0 &&
                getYearPublished() == data.getYearPublished();
    }
    //Getter
    public static List<BookData> getBookList() {
        //Used to retrieve the private bookList outside BookData.java.
        return bookList;
    }
    public static void setBookList(List<BookData> newBookList) {
        bookList = newBookList;
    }
}
