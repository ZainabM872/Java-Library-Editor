package ca.ucalgary.finalprojectgui;
/**
 * Names: Isaac, Halima, Zainab
 * UCIDs: 30219835, 30208584, 10192965
 * Date: Date: 2024-03-21
 * Tutorials: Tut 19, Tut 16, Tut 16
 */
public abstract class BookTemplate {
    private String title;
    //title instance variable
    private String author;
    //author instance variable
    private String genre;
    //genre instance variable
    private double rating;
    //rating instance variable
    private int yearPublished;
    //yearPublished instance variable
    private BookTypes type;
    //enumerator


    /**
     * BookTemplate: Used to access instance variables of the book object
     * @param title title of the book
     * @param author author of the book
     * @param genre genre of the book
     * @param rating rating of the book
     * @param yearPublished year the book was published
     * @param type type of book ("regular" book or wishlist book)
     */
    public BookTemplate(String title, String author, String genre, double rating, int yearPublished, BookTypes type) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        this.yearPublished = yearPublished;
        this.type = type;
    }

    // return values
    protected String getTitle() {
        return title;
    }
    protected String getAuthor() {
        return author;
    }
    protected String getGenre() {
        return genre;
    }
    protected double getRating() {
        return rating;
    }
    protected int getYearPublished() {
        return yearPublished;
    }
    // set values
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
    public void setAuthor(String newAuthor) {
        this.author = newAuthor;
    }
    public void setGenre(String newGenre) {
        this.genre = newGenre;
    }
    public void setRating(double newRating) {
        this.rating = newRating;
    }
    public void setYearPublished(int newYearPublished) {
        this.yearPublished = newYearPublished;
    }

    // abstract methods
    public abstract int addABook();
    public abstract int removeABook(String title);
    public abstract void printDataBase();
}
