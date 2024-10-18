import java.util.*;

public class BookWishlist extends BookTemplate {
    //The list which holds all wishlist objects, stored in an ArrayList.
    private static final List<BookWishlist> wishlist = new ArrayList<>();

    //Constructor, initializes the BookWishlist object
    public BookWishlist(String title, String author, String genre, double rating, int yearPublished) {
        super(title, author, genre, rating, yearPublished, BookTypes.WISHLIST);
    }

    /**
     * 4) Options: The options functions allows the user to edit either the rating, genre, year published, or the author
     * and gives the user an option to add a book title to their wishlist
     * Add to wishlist
     * Remove from wishlist
     * Return to menu
     **/
    public void addABook() {
        //Adds the book to the library.
        boolean contains = false;
        for (int j = 0; j < BookData.getBookList().size(); j++) {
            BookData bookW = BookData.getBookList().get(j);
            //Checks if the bookList already contains this book.
            if (new BookData(this.getTitle(), this.getAuthor(), this.getGenre(), this.getRating(), this.getYearPublished()).equals(bookW) ||
                    this.getTitle().equalsIgnoreCase(bookW.getTitle())) {
                System.out.println("You already have this book in your library!");
                return;
            }
        }
        for (int i = 0; i < wishlist.size(); i++) {
            BookWishlist book = wishlist.get(i);
            //Checks if the wishlist already contains this book.
            if (this.equals(book) || this.getTitle().equalsIgnoreCase(book.getTitle())) {
                contains = true;
            }
        }
        if (contains) {
            System.out.println("'" + this.getTitle() + "' is already in your wishlist");
        } else {
            wishlist.add(this);
            System.out.println("The book '" + this.getTitle() + "' has been added to your wishlist!");
        }
    }
    public void removeABook(String title) {
        //Removes the indicated book from the wishlist.
        boolean contains = false;
        if (!wishlist.isEmpty()) {
            for (int i = 0; i < wishlist.size(); i++) {
                BookWishlist book = wishlist.get(i);
                //If the indicated title matches with an object's title in the wishlist
                if (book.getTitle().equalsIgnoreCase(title)) {
                    wishlist.remove(book);
                    System.out.println("The book '" + book.getTitle() + "' has been removed from your wishlist!");
                    contains = true;
                }
            }
            //Otherwise the wishlist does not contain this book.
            if (!contains) {
                System.out.println("The book is not in your wishlist.");
            }
        } else {
            System.out.println("Your wishlist is empty!");
        }
    }
    /**
     * 6) Output Special: has a sub menu that allows you to select more options
     * View Wishlist
     * Back to menu
     */
    public void printDataBase() {
        //Displays the entire wishlist, automatically converts each book object via toString
        //All relevant details of each object is printed.
        if (!wishlist.isEmpty()) {
            System.out.println("Your Wishlist: \n");
            for (int i = 0; i < wishlist.size(); i++) {
                BookWishlist book = wishlist.get(i);
                System.out.println(book + "\n");
            }
        } else {
            System.out.println("Your wishlist is empty.");
        }
    }

    /**
     * Special option within View Wishlist
     * Allows you to move a wishlist book to the library
     */
    public BookData WishlistToLibrary(String title) {
        //Attempts to add the indicated book to the library if not a duplicate.
        //If successful, removeABook is called on the wishlist object
        //And currentBook is assigned the return value of this method, then calls addABook.
        if (!wishlist.isEmpty()) {
            for (int i = 0; i < wishlist.size(); i++) {
                BookWishlist book = wishlist.get(i);
                if (book.getTitle().equalsIgnoreCase(title)) {
                    System.out.println("Attempting to move '" + book.getTitle() + "' to your library...");
                    //Returns the wishlist book, but converted to BookData class.
                    return new BookData(book.getTitle(), book.getAuthor(), book.getGenre(), book.getRating(), book.getYearPublished());
                }
            }
        } else {
            System.out.print("Your wishlist is empty.");
        }
        return null;
    }

    //Overrides
    @Override
    public String toString() {
        //Automatically converts all BookWishlist objects into this format if printed.
        return "Title: " + getTitle() +
                "\nAuthor: " + getAuthor() +
                "\nGenre: " + getGenre() +
                "\nRating: " + getRating() +
                "\nYear Published: " + getYearPublished();

    }

    @Override
    public boolean equals(Object object) {
        //Compares attributes of each object.
        //The objects must be an instance of BookWishlist and have the exact same attributes to return true.
        //Otherwise, it's false.
        if (this == object) {
            return true;
        }
        if (!(object instanceof BookWishlist)) {
            return false;
        }
        BookWishlist wishlist1 = (BookWishlist) object;
        return Objects.equals(getTitle(), wishlist1.getTitle()) &&
                Objects.equals(getAuthor(), wishlist1.getAuthor()) &&
                Objects.equals(getGenre(), wishlist1.getGenre()) &&
                Double.compare(wishlist1.getRating(), getRating()) == 0 &&
                getYearPublished() == wishlist1.getYearPublished();
    }

    //Getter
    public static List<BookWishlist> getWishlist() {
        //Used to retrieve the private wishlist outside BookWishlist.java
        return wishlist;
    }
}
