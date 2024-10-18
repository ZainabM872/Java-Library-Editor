import java.util.*;
import java.io.*;

public class Menu {
    //Scanner for user input.
    private static final Scanner sc = new Scanner(System.in);
    //Initialized to default attributes, otherwise a NullPointerException occurs.
    public static BookData currentBook = new BookData("","","",0,0);
    public static BookWishlist wish = new BookWishlist("","","",0,0);

    /**
     * Main Menu function, combines all functions.
     */
    public static void menu() {
        boolean valid = false;
        while (!valid) {
            //Repeats for as long as valid is false, stops repeating when the program exits.
            try {
                //Try catch statement to prevent any exceptions or invalid input types.
                System.out.print("""
                                                
                        Please select one of the following inputs:\s
                        1) Add a book\s
                        2) Remove a book\s
                        3) Search\s
                        4) Options\s
                        5) View Library\s
                        6) Special\s
                        7) Program Options\s
                        """);
                int option = sc.nextInt();
                //Scans the next input as an integer, to be used in the switch statement
                switch (option) {
                    case 1:
                        //Allows the user to add a book to the library with details.
                        addBookMenu();
                        break;
                    case 2:
                        //Displays the options to remove a book
                        removeBookMenu();
                        break;
                    case 3:
                        //Displays the options to search by category
                        search();
                        break;
                    case 4:
                        //Display options (such as edit, add to wishlist, etc)
                        options();
                        break;
                    case 5:
                        //Print entire library (with nice format)
                        outputGeneral();
                        break;
                    case 6:
                        //Special options
                        outputSpecial();
                        break;
                    case 7:
                        //Program options
                        ProgramOptions();
                        break;
                    default:
                        //invalid cases
                        System.out.println("Invalid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type");
                sc.nextLine();
            } catch (NullPointerException e) {
                System.out.println("Your library or wishlist is empty!");
            } catch (Exception e) {
                System.out.println("Something unexpected has occurred.");
            }
        }
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
    private static void addBookMenu() {
        //Add a book to the library
        boolean loop = true;
        while (loop) {
            System.out.println("Add a book title: ");
            sc.nextLine();
            String title = sc.nextLine();

            System.out.println("Add a rating for the book between 1 and 5: ");
            double rating = sc.nextDouble();
            sc.nextLine();
            while (rating < 1 || rating > 5) {
                System.out.println("Please enter a rating between 1 and 5: ");
                rating = sc.nextDouble();
                sc.nextLine();
            }

            System.out.println("Add the genre of the book: ");
            String genre = sc.nextLine();

            System.out.println("Enter the year the book was published: ");
            int year = sc.nextInt();
            sc.nextLine();
            while (year < 0 || year > 2024) {
                System.out.println("Please enter a valid year: ");
                year = sc.nextInt();
                sc.nextLine();
            }

            System.out.println("Enter the author's last name: ");
            String author = sc.nextLine();
            loop = false;
            //All valid user input is stored into an object called currentBook.
            currentBook = new BookData(title, author, genre, rating, year);
            currentBook.addABook();
        }
    }

    /**
     * 2) Remove a book: The remove a book function allows the user to either remove a singular book object from bookList
     * or remove all book objects from the bookList.
     * Remove a book - deletes a singular book
     * Remove all books - deletes all books
     */
    private static void removeBookMenu() {
        //Remove a book from the library
        boolean loop2 = true;
        while (loop2) {
            System.out.print("""
                    a) Remove a book\s
                    b) Remove all books\s
                    c) Back to menu\s
                    """);
            String options = sc.next();
            sc.nextLine();
            //Always need to clear the empty space after scanning without nextLine()
            switch (options) {
                case "a":
                    System.out.println("Enter the title of the book you want to remove: ");
                    String title = sc.nextLine();
                    currentBook.removeABook(title);
                    loop2 = false;
                    break;
                case "b":
                    currentBook.removeAllBooks();
                    loop2 = false;
                    break;
                case "c":
                    loop2 = false;
                    //breaks the loop and goes back to the main menu
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
                //Repeats the loop if an invalid input occurs
            }
        }
    }

    /** 3) Searching function: The searching function is supposed to search for all books by the specified author, book
     * title, genre or the rating of the book by user input. The user input is taken and is compared to all objects in bookList
     * If the object contains the specified attribute, such as the requested author, contains will be set to true, and a
     * concatenated string of all books will be printed out. Otherwise, the entry is invalid and does not exist among the objects.
     */
    private static void search() {
        //Search (Displays all entries related to the corresponding prompt.)
        boolean loop3 = true;
        while (loop3) {
            System.out.print("""
                    Please select one of the following inputs:\s
                    a) Search by author\s
                    b) Search by title\s
                    c) Search by genre\s
                    d) Search by rating\s
                    e) Back to menu\s
                    """);
            String select = sc.next();
            sc.nextLine();
            switch (select) {
                case "a":
                    System.out.println("Please enter the last name of the author:");
                    String author = sc.next();
                    sc.nextLine();
                    currentBook.searchByAuthor(author);
                    loop3 = false;
                    break;
                case "b":
                    System.out.println("Please enter the title of the book:");
                    String bookTitle = sc.nextLine();
                    currentBook.searchByTitle(bookTitle);
                    loop3 = false;
                    break;
                case "c":
                    System.out.println("Please enter the genre you would like to search:");
                    String genre = sc.next();
                    sc.nextLine();
                    currentBook.searchByGenre(genre);
                    loop3 = false;
                    break;
                case "d":
                    System.out.println("Please enter a number between 1 and 5:");
                    double rating = sc.nextDouble();
                    sc.nextLine();
                    while (rating < 1 || rating > 5) {
                        System.out.println("Invalid rating. Please enter a rating between 1 and 5: ");
                        rating = sc.nextDouble();
                        sc.nextLine();
                    }
                    currentBook.searchByRating(rating);
                    loop3 = false;
                    break;
                case "e":
                    //returns to main menu
                    loop3 = false;
                    break;
            }
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
     * Add to wishlist
     * Remove from wishlist
     * Return to menu
     **/
    private static void options() {
        //Options
        boolean loop4 = true;
        while (loop4) {
            System.out.print("""
                    Please select one of the following options.\s
                    a) Edit book\s
                    b) Add to wishlist\s
                    c) Remove from wishlist \s
                    d) Back to menu\s
                    """);
            String options2 = sc.next();
            sc.nextLine();

            switch (options2) {
                case "a":
                    boolean aCase = true;
                    while (aCase) {
                        //nested switch statement
                        System.out.print("""
                                Please select one of the edit inputs:\s
                                1) Edit author\s
                                2) Edit genre\s
                                3) Edit rating\s
                                4) Edit year published\s
                                """);
                        int edit = sc.nextInt();
                        sc.nextLine();

                        switch (edit) {
                            case 1:
                                System.out.println("Please enter the title of the book you would like to edit.");
                                String title = sc.nextLine();
                                System.out.println("Please enter the name you would like to change the author to.");
                                String newAuthor = sc.nextLine();
                                currentBook.editBookAuthor(title, newAuthor);
                                aCase = false;
                                break;
                            case 2:
                                System.out.println("Please enter the title of the book you would like to edit.");
                                String title2 = sc.nextLine();
                                System.out.println("Please enter the genre you would like to change " + title2 + " to.");
                                String newGenre = sc.nextLine();
                                currentBook.editBookGenre(title2, newGenre);
                                aCase = false;
                                break;
                            case 3:
                                System.out.println("Please enter the title of the book you would like to edit.");
                                String title3 = sc.nextLine();
                                System.out.println("Please enter the book's new rating between 1 and 5: ");
                                double newRating = sc.nextDouble();
                                sc.nextLine();
                                while (newRating < 1 || newRating > 5) {
                                    System.out.println("Invalid rating. Please enter a rating between 1 and 5:");
                                    newRating = sc.nextDouble();
                                    sc.nextLine();
                                }
                                currentBook.editBookRating(title3, newRating);
                                aCase = false;
                                break;
                            case 4:
                                System.out.println("Please enter the title of the book you would like to edit.");
                                String title4 = sc.nextLine();
                                System.out.println("Please enter the book's new year published: ");
                                int newYear = sc.nextInt();
                                sc.nextLine();
                                while (newYear < 0 || newYear > 2024) {
                                    System.out.println("Invalid year. Please enter a valid year.");
                                    newYear = sc.nextInt();
                                    sc.nextLine();
                                }
                                currentBook.editBookYear(title4, newYear);
                                aCase = false;
                                break;
                            default:
                                System.out.println("Invalid option.");
                                break;
                        }
                    }
                    loop4 = false;
                    break;
                case "b":
                    //Very similar functionality to option 1.
                    boolean loop = true;
                    while (loop) {
                        System.out.println("Please enter the book you would like to add to your wishlist: ");
                        String title = sc.nextLine();

                        System.out.println("Add a rating for the book between 1 and 5: ");
                        double rating = sc.nextDouble();
                        sc.nextLine();
                        while (rating < 1 || rating > 5) {
                            System.out.println("Please enter a rating between 1 and 5: ");
                            rating = sc.nextDouble();
                            sc.nextLine();
                        }

                        System.out.println("Add the genre of the book: ");
                        String genre = sc.nextLine();

                        System.out.println("Enter the year the book was published: ");
                        int year = sc.nextInt();
                        sc.nextLine();
                        while (year < 0 || year > 2024) {
                            System.out.println("Please enter a valid year: ");
                            year = sc.nextInt();
                            sc.nextLine();
                        }

                        System.out.println("Enter the author's last name: ");
                        String author = sc.nextLine();
                        loop = false;
                        //creates a BookWishlist object with valid user inputs.
                        wish = new BookWishlist(title, author, genre, rating, year);
                        wish.addABook();
                    }
                    loop4 = false;
                    break;
                case "c":
                    System.out.println("Please enter the book you would like to remove from your wishlist:");
                    String removeWish = sc.nextLine();
                    wish.removeABook(removeWish);
                    loop4 = false;
                    break;
                case "d":
                    System.out.println("Returning to the main menu...");
                    loop4 = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    /**
     * 5) Output General:
     * Print the entire database:
     */
    private static void outputGeneral() {
        System.out.println("Printing the entire library! \n");
        currentBook.printDataBase();
    }

    /**
     * 6) Output Special: has a sub menu that allows you to select more options
     * Highest rated (out of 5 stars)
     * Recommendations
     * Newest Entries
     * View wishlist
     * Back to menu
     */
    private static void outputSpecial() {
        boolean valid = false;
        while (!valid) {
            System.out.println(
                    "a) Highest rated (out of 5 stars)\n" +
                            "b) Recommendations\n" +
                            "c) Newest Entry\n" +
                            "d) View wishlist\n" +
                            "e) Back to menu");
            sc.nextLine();
            String subOption = sc.nextLine();

            switch (subOption) {
                case "a": //check if subOption == a
                    currentBook.highestRated();
                    valid = true;
                    break;
                case "b":
                    currentBook.recommendation();
                    valid = true;
                    break;
                case "c":
                    currentBook.newestEntry();
                    valid = true;
                    break;
                case "d":
                    wish.printDataBase();
                    boolean loopdiloop = false;
                    if (!BookWishlist.getWishlist().isEmpty()) {
                        //getter to retrieve the wishlist.
                        while (!loopdiloop) {
                            //another loop to repeat options upon invalid input
                            System.out.print("""
                                            Would you like to move a wishlist book to your library?\s
                                            a) Yes\s
                                            b) No\s
                                            """);
                            String entry = sc.next();
                            sc.nextLine();
                            switch (entry) {
                                case "a":
                                    System.out.println("Please enter the book you would like to move to your library: ");
                                    String newBook = sc.nextLine();
                                    try {
                                        //If the book can be moved from the wishlist to the library
                                        currentBook = wish.WishlistToLibrary(newBook);
                                        //removes the book from the wishlist and adds it to the bookList
                                        wish.removeABook(newBook);
                                        currentBook.addABook();
                                    } catch (NullPointerException e) {
                                        System.out.println("Returning to menu...");
                                    }
                                    loopdiloop = true;
                                    break;
                                case "b":
                                    loopdiloop = true;
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                                    break;
                            }
                        }

                    }
                    valid = true;
                    break;
                case "e":
                    System.out.println("Returning to the main menu...");
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
    /**
     * 7) Program Options: has a submenu with four options
     * Exit program
     * Save data
     * Load data
     * Back to menu
     */
    private static void ProgramOptions() {
        boolean valid = false;
        while (!valid) {
            //sub menu
            System.out.print("""
                Please select one of the following options:
                a) Exit program\s
                b) Save data\s
                c) Load data\s
                d) Back to menu\s
                """);
            String option = sc.next(); //store input
            sc.nextLine();
            switch (option) {
                case "a":
                    System.out.println("Exiting Program!");
                    System.exit(0);
                case "b":
                    //Saves library and wishlist
                    try {
                        FileOperations.saveToFileData("Library.csv", BookData.getBookList());
                        FileOperations.saveToFileWishlist("Wishlist.csv", BookWishlist.getWishlist());
                        System.out.println("Saving Data...");

                    } catch (IOException e) {
                        System.out.println("Could not save to file: " + e.getMessage());
                    }
                    break;
                case "c":
                    //Loads library and wishlist
                    //Loading will override the current library and wishlist.
                    BookData.getBookList().clear();
                    BookWishlist.getWishlist().clear();
                    try {
                        System.out.println("Loading Data and emptying current libraries...");
                        List<BookData> LibrarySave = FileOperations.readFromFileData("Library.csv");
                        for (BookData book : LibrarySave) {
                            //Assigns currentBook to each saved library book, then calls addABook on all of them.
                            currentBook = book;
                            currentBook.addABook();
                        }
                        List<BookWishlist> WishlistSave = FileOperations.readFromFileWishlist("Wishlist.csv");
                        for (BookWishlist wishBook : WishlistSave) {
                            //Assigns currentBook to each saved wishlist book, then calls addABook on all of them.
                            wish = wishBook;
                            wish.addABook();
                        }
                    } catch (IOException e) {
                        System.out.println("Could not read from file: " + e.getMessage());
                    }
                    valid = true;
                    break;
                case "d":
                    //returns back to the main menu
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
