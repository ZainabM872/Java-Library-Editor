package ca.ucalgary.finalprojectgui;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * Names: Isaac, Halima, Zainab
 * UCIDs: 30219835, 30208584, 10192965
 * Date: Date: 2024-04-11
 * Tutorials: Tut 19, Tut 16, Tut 16
 */

public class MainController {

    //initialize booklist
    private String title = "";

    private String author = "";

    private String genre = "";

    //Set to -1 as a default value.
    private double rating = -1;

    private int year = -1;

    //Initialize both BookData and BookWishlist objects to prevent NullPointerException
    private BookData currentBook = new BookData("", "","",-1,-1);
    private BookWishlist wish = new BookWishlist("","","",-1,-1);

    private boolean validAuthor = false;
    private boolean validGenre = false;
    private boolean validTitle = false;
    private boolean validRating = false;
    private boolean validYear = false;

    private boolean editauthor = false;
    private boolean editrating = false;
    private boolean edityear = false;
    private boolean editgenre = false;

    //GUI ELEMENTS

    //ADD BOOK
    @FXML
    public TextArea InfoArea;

    @FXML
    private Button ABAddBook;

    @FXML
    private TextField ABAuthor;

    @FXML
    private TextField ABGenre;

    @FXML
    private TextField ABRating;

    @FXML
    private TextField ABTitle;

    @FXML
    private TextField ABYear;

    @FXML
    private Pane ABPane;

    @FXML
    private Button addBook;

    //REMOVE BOOK
    @FXML
    private Pane RBPane;

    @FXML
    private Button RBRemoveABook;

    @FXML
    private Button RBRemoveAll;

    @FXML
    private TextField RBTitle;

    @FXML
    private Button removeABook;

    //SEARCH BOOK
    @FXML
    private MenuButton SearchMenu;

    @FXML
    private MenuItem SearchAuthor;


    @FXML
    private MenuItem SearchGenre;

    @FXML
    private MenuItem SearchRating;

    @FXML
    private MenuItem SearchTitle;

    @FXML
    private TextField STitle;

    @FXML
    private Pane STitlePane;

    @FXML
    private Button STitleSearch;

    @FXML
    private TextField SAuthor;

    @FXML
    private Button SSearch;

    @FXML
    private Pane SPane;

    @FXML
    private TextField SGenre;

    @FXML
    private Pane SGenrePane;

    @FXML
    private Button SGenreSearch;

    @FXML
    private Pane SRatingPane;

    @FXML
    private TextField SRating;

    @FXML
    private Button SRatingSearch;

    //OPTIONS
    @FXML
    private MenuButton OptionsMenu;

    @FXML
    private MenuItem Edit;

    @FXML
    private MenuButton EditDropDown;

    //EDIT
    @FXML
    private TextField editAuthor;

    @FXML
    private TextField editTitle;

    @FXML
    private TextField editGenre;

    @FXML
    private TextField editRating;

    @FXML
    private TextField editYear;

    @FXML
    private MenuItem EditAuthor;

    @FXML
    private MenuItem EditGenre;

    @FXML
    private MenuItem EditRating;

    @FXML
    private MenuItem EditYear;

    @FXML
    private Button EditButton;

    @FXML
    private Button EditA;

    @FXML
    private Pane EPane;

    @FXML
    private Pane EditPane;

    //WISHLIST
    @FXML
    private MenuItem AddWishlist;

    @FXML
    private Button AWAddBook;

    @FXML
    private MenuItem RemoveWishlist;

    @FXML
    private Pane WTLPane;

    @FXML
    private TextField MTLTitle;

    @FXML
    private Button MoveToLibrary;

    @FXML
    private MenuItem MoveToLibraryOption;

    @FXML
    private Button RWRemoveAll;

    @FXML
    private Button RWRemoveBook;

    //SPECIAL
    @FXML
    private MenuButton SpecialMenu;

    @FXML
    private MenuItem HighestRated;

    @FXML
    private MenuItem Recommendation;

    @FXML
    private MenuItem NewestEntry;

    //VIEW LIBRARY
    @FXML
    private MenuItem ViewWishlist;

    @FXML
    private Button ViewLibrary;

    @FXML
    private GridPane VLGridPane;

    @FXML
    private BorderPane VLBorderPane;

    @FXML
    private Label VLLibraryLabel;

    @FXML
    private Rectangle VLLibraryWall;

    @FXML
    private Rectangle VLLibraryWall2;

    @FXML
    private MenuItem About;

    @FXML
    private MenuItem LoadLibrary;

    @FXML
    private MenuItem LoadWishlist;

    @FXML
    private MenuItem Quit;

    @FXML
    private MenuItem SaveAsLibrary;

    @FXML
    private MenuItem SaveAsWishlist;

    @FXML
    private Label LeftStatus;

    @FXML
    private Label RightStatus;


    FileChooser fileChooser = new FileChooser();


    //onAction

    //ADD A BOOK
    /**
     *onActionAddBook assigns the variables title, author, genre, rating, and year to an empty string and if the inputs
     * for title, author, genre, rating and year are valid and NOT a default option then a new book object is created.
     * It also sets the visibility of each pane apart from the add book panes to false so that they are the only ones
     * showing on the gui when the option is picked
     * @param actionEvent happens when the 'Add Book' option is chosen
     */
    @FXML
    void onActionAddBook(ActionEvent actionEvent) {
        ResetTextFields();
        title = "";
        author = "";
        genre = "";
        rating = -1;
        year = -1;
        validAuthor = false;
        validGenre = false;
        validTitle = false;
        validRating = false;
        validYear = false;
        ResetVisibility();

        //AddBook
        ABPane.setVisible(true);
        ABAddBook.setVisible(true);
        AWAddBook.setVisible(false);

    }

    /**
     * onActionAddBook calls the addABook method and stores the return value into a variable and depending on what the
     * method returned, it delivers a message on the bottom Left Status or bottom Right status and shows the info in the
     * info pane if valid. It assigns the variables title, author, genre, rating, and year to an empty string and if the inputs
     * for title, author, genre, rating and year are valid and NOT a default option then a new book object is created.
     * It also sets the visibility of each pane apart from the add book panes to false so that they are the only ones
     * showing on the gui when the option is picked
     * @param event happens when the 'Add Book' option is chosen
     */
    @FXML
    void onActionABAddBook(ActionEvent event) {
        //if any of the book details are a default value, then give an error.
        if (validAuthor && validGenre && validTitle && validRating && validYear) {
            currentBook = new BookData(title, author, genre, rating, year);
            int result = currentBook.addABook(); // Call the method once and store its result
            if (result == 1) {
                LeftStatus.setText("Successfully added " + title + " to the Library!");
                InfoArea.setText("Recently added: " + title);
            } else if(result == 2) {
                LeftStatus.setText("This book already exists in the library!");
                InfoArea.setText("");
            } else {
                RightStatus.setText("Something went wrong while trying to add your book!");
            }
        } else {
            LeftStatus.setText("Error adding book, not all book details are valid!");
        }
    }

    /**
     * onActionABAuthor gets the string value inputted by the user in the ABAuthor text field and if the string has
     * invalid numbers or special characters then they are removed from the string and what is left is the valid string.
     * Then the Left Status is updated whether the author was successfully set or not then sets the boolean validAuthor
     * to true if the author was successfully added or to false if it wasn't and allows the user to enter a valid option.
     * @param event happens when 'enter' is pressed
     */
    @FXML
    void onActionABAuthor(ActionEvent event) {
        //If the text entered is blank, where any special characters and numbers are removed
        if (!ABAuthor.getText().replaceAll("[^a-zA-Z ]","").isBlank()) {
            author = ABAuthor.getText().replaceAll("[^a-zA-Z ]","");
            LeftStatus.setText("Successfully set author to: " + author);
            ABAuthor.setText(author);
            validAuthor = true;
        } else {
            LeftStatus.setText("Author must have a valid name.");
            validAuthor = false;
        }
    }

    /**
     * onActionABGenre gets the string value inputted by the user in the ABGenre text field and if the string has
     * invalid numbers or special characters then they are removed from the string and what is left is the valid string.
     * Then the Left Status is updated whether the genre was successfully set or not then sets the boolean validGenre
     * to true if the genre was successfully added or to false if it wasn't and allows the user to enter a valid option.
     * @param event happens when 'enter' is pressed
     */
    @FXML
    void onActionABGenre(ActionEvent event) {
        //If the text entered is blank, where any special characters and numbers are removed
        if (!ABGenre.getText().replaceAll("[^a-zA-Z ]", "").isBlank()) {
            genre = ABGenre.getText().replaceAll("[^a-zA-Z ]", "");
            LeftStatus.setText("Successfully set genre to: " + genre);
            ABGenre.setText(genre);
            validGenre = true;
        } else {
            LeftStatus.setText("Genre must be valid.");
            validGenre = false;
        }
    }

    /**
     * onActionABRating gets the string value inputted by the user in the ABRating text field and parses the string to
     * a double. The double is then checked to be between the values of 1 and 5 and if an invalid number is entered then
     * the Left Status shows an error message and user can input a valid rating and sets rating to the valid input,
     * then sets the boolean validRating to true if the rating was successfully added or to false if it wasn't and
     * allows the user to enter a valid option.
     * @param event happens when 'enter' is pressed
     */
    @FXML
    void onActionABRating(ActionEvent event) {
        String ratingString = ABRating.getText().trim();
        try {
            //Attempt to parse the String to a double
            double ratingInitial = Double.parseDouble(ratingString);
            if (ratingInitial < 1 || ratingInitial > 5) {
                LeftStatus.setText("Error: Rating entry must be between 1-5");
                validRating = false;
            } else {
                //If the parsed double is valid, then set rating to it.
                rating = ratingInitial;
                LeftStatus.setText("Rating set to: " + rating);
                ABRating.setText(String.valueOf(rating));
                validRating = true;
            }
        } catch (NumberFormatException e) {
            LeftStatus.setText("Error: Rating must be a number!");
            validRating = false;
        }
    }

    /**
     * onActionABTitle gets the string value inputted by the user in the ABTitle text field.
     * The Left Status is updated whether the title was successfully set or not then sets the boolean validTitle
     * to true if the title was successfully added or to false if it wasn't and allows the user to enter a valid option.
     * @param event happens when 'enter' is pressed
     */
    @FXML
    void onActionABTitle(ActionEvent event) {
        // Check if the title is blank
        if (!ABTitle.getText().isBlank()) {
            title = ABTitle.getText().trim();
            LeftStatus.setText("Title set to: " + title);
            ABTitle.setText(title);
            validTitle = true;
        } else {
            LeftStatus.setText("Error: Title cannot be empty.");
            validTitle = false;
        }
    }

    /**
     * onActionABYear gets the string value inputted by the user in the ABYear text field and parses the string to
     * an integer. The integer is then checked to be between or the years of 0 and 2024 and if an invalid year is
     * entered then the Left Status shows an error message and user can input a valid year and sets year to the valid
     * input, then sets the boolean validYear to true if the year was successfully added or to false if it wasn't and
     * allows the user to enter a valid option.
     * @param event happens when 'enter' is pressed
     */
    @FXML
    void onActionABYear(ActionEvent event) {
        String yearString = ABYear.getText().trim();
        try {
            // Attempt to parse the string to an int
            int yearInitial = Integer.parseInt(yearString);
            if (yearInitial < 0 || yearInitial > 2024) {
                LeftStatus.setText("Error: Year must be between 0 and 2024");
                validYear = false;
            } else {
                // If parsed successfully, set year to it
                year = yearInitial;
                LeftStatus.setText("Year published has been set to " + year);
                ABYear.setText(String.valueOf(year));
                validYear = true;
            }
        } catch (NumberFormatException e) {
            LeftStatus.setText("Error: Year must be an Integer!");
            validYear = false;
        }
    }

    //REMOVE A BOOK
    /**
     * onActionRemoveABook assigns the variable title to an empty string sets the visibility of each pane and text field
     * apart from the remove book panes and text fields to false so that they are the only ones showing on the gui when
     * the option is picked
     * @param actionEvent happens when the 'Remove Book' option is chosen
     */
    @FXML
    void onActionRemoveABook(ActionEvent actionEvent) {
        ResetTextFields();
        title = "";
        ResetVisibility();
        //Remove Book
        RBPane.setVisible(true);
        RBRemoveABook.setVisible(true);
        RBRemoveAll.setVisible(true);
        RWRemoveAll.setVisible(false);
        RWRemoveBook.setVisible(false);
    }

    /**
     * onActionRBRemoveABook calls the removeABook method and stores the return value into a variable and depending on
     * what the method returned based on if the book exists or the library is empty, it delivers a message on the bottom
     * Left Status or bottom Right status. If the book is valid, the removed book is then displayed in the info area.
     * @param event happens when the 'Remove a Book' option is chosen
     */
    @FXML
    void onActionRBRemoveABook(ActionEvent event) {
        int result = currentBook.removeABook(title);
        if (result == 1) {
            LeftStatus.setText("The book '" + title + "' has been removed from the library!");
            InfoArea.setText("Recently removed: " + title);
        } else if (result == 2) {
            LeftStatus.setText("Book title not found. Please try again.");
            InfoArea.setText("");
        } else if (result == 3) {
            LeftStatus.setText("Your library is empty!");
            InfoArea.setText("");
        } else {
            RightStatus.setText("Something went wrong while trying to remove a book!");
        }
    }

    /**
     * onActionRBRemoveAll calls the removeAllBooks method and stores the return value into a variable and depending on
     * what the method returned based on if all books have been removed or if the library is empty, it delivers a message
     * on the bottom Left Status or bottom Right status.
     * @param event happens when the 'Remove All Books' option is chosen
     */
    @FXML
    void onActionRBRemoveAll(ActionEvent event) {
        boolean result = currentBook.removeAllBooks();
        if (result) {
            LeftStatus.setText("All books have been removed from the library!");
            InfoArea.setText("");
        } else {
            LeftStatus.setText("Your library is already empty!");
        }
    }

    /**
     * onActionRBRemoveTitle gets text from Remove Book text field and displays the title about to be removed in Left Status
     * @param event happens when the Title text field is typed in
     */
    @FXML
    void onActionRBTitle(ActionEvent event) {
        if (!RBTitle.getText().trim().isBlank()) {
            title = RBTitle.getText().trim();
            LeftStatus.setText("Title set to: " + title);
            RBTitle.setText(title);
        } else {
            LeftStatus.setText("Error: Title cannot be empty.");
        }

    }

    //SEARCH
    /**
     * sets all variables to empty strings or to -1 for ints/doubles
     * @param actionEvent: when the search button is clicked
     *
     */
    @FXML
    void onActionSearchMenu(ActionEvent actionEvent) {
        title = "";
        author = "";
        genre = "";
        rating = -1;
        year = -1;
    }

    /**
     * sets text fields and visibility of all panes except the title pane to false
     * @param event when the 'Search By Title' option is selected
     */
    @FXML
    void onActionSearchTitle(ActionEvent event) {
        ResetTextFields();
        ResetVisibility();
        STitlePane.setVisible(true);
        SearchMenu.setText("Search by title");
    }

    /**
     * onActionsTitle gets text from search title text field and displays the title about to be searched in Left Status
     * @param event happens when the Title text field is typed in
     */
    @FXML
    void onActionSTitle(ActionEvent event) {
        if (!STitle.getText().isBlank()) {
            title = STitle.getText().trim();
            LeftStatus.setText("Search set to: " + title);
            STitle.setText(title);
        } else {
            LeftStatus.setText("Error: Title cannot be empty.");
        }
    }

    /**
     * onActionSTitleSearch calls the searchByTitle method and stores the return value into a variable and depending on
     * what the method returned based on if the book title exists or the library is empty, it delivers a message on the bottom
     * Left Status or bottom Right status. If the book is valid, the details of the book is then displayed in the info area.
     * @param event happens when the 'Search by Title' option is chosen
     */
    @FXML
    void onActionSTitleSearch(ActionEvent event) {
        String search = currentBook.searchByTitle(title);
        if (search.equals("")) {
            LeftStatus.setText("Your Library is empty!");
            InfoArea.setText("");
        } else if (search.equalsIgnoreCase("Invalid")) {
            LeftStatus.setText("Invalid entry. Title does not exist.");
            InfoArea.setText("");
        } else if (search.equalsIgnoreCase("Error")) {
            RightStatus.setText("Something went wrong while trying to search for a title!");
            InfoArea.setText("");
        } else {
            LeftStatus.setText("Book title: " + title + " has been found!");
            InfoArea.setText(search);
        }
    }

    /**
     * sets text fields and visibility of all panes except the title pane to false
     * @param event when the 'Search By Author' option is selected
     */
    @FXML
    void onActionSearchAuthor(ActionEvent event) {
        ResetTextFields();

        ResetVisibility();

        SPane.setVisible(true);

        SearchMenu.setText("Search by author");

    }

    /**
     * onActionSAuthor gets the string value inputted by the user in the SAuthor text field and if the string has
     * invalid numbers or special characters then they are removed from the string and what is left is the valid string.
     * Then the Left Status is updated to the author that is going to be searched whether the search was successfully set or not.
     * @param event happens when 'enter' is pressed
     */
    @FXML
    void onActionSAuthor(ActionEvent event) {
        if (!SAuthor.getText().replaceAll("[^a-zA-Z ]","").isBlank()) {
            author = SAuthor.getText().replaceAll("[^a-zA-Z ]","");
            LeftStatus.setText("Search set to: " + author);
            SAuthor.setText(author);
        } else {
            LeftStatus.setText("Author must have a valid name.");
        }
    }

    /**
     * onActionSSearch calls the searchByAuthor method and stores the return value into a variable and depending on
     * what the method returned based on if the author exists or the library is empty, it delivers a message on the bottom
     * Left Status or bottom Right status. If the author is valid, the books with that author is displayed in the info area.
     * @param event happens when the 'Search by Author' option is chosen
     */
    @FXML
    void onActionSSearch(ActionEvent event) {
        ResetTextFields();
        String search = currentBook.searchByAuthor(author);
        if(search.equals("")){
            LeftStatus.setText("Your Library is empty!");
            InfoArea.setText("");
        } else if (search.equalsIgnoreCase("Invalid")) {
            LeftStatus.setText("Invalid entry. Author does not exist.");
            InfoArea.setText("");
        } else {
            LeftStatus.setText("Book by " + author + " has been found!");
            InfoArea.setText(search);
        }
    }

    /**
     * sets text fields and visibility of all panes except the title pane to false
     * @param event when the 'Search By Genre' option is selected
     */
    @FXML
    void onActionSearchGenre(ActionEvent event) {
        ResetTextFields();

        ResetVisibility();

        SGenrePane.setVisible(true);

        SearchMenu.setText("Search by genre");
    }

    /**
     * onActionSGenre gets text from search genre text field and displays the genre about to be searched in Left Status
     * @param event happens when the Genre text field is typed in
     */
    @FXML
    void onActionSGenre(ActionEvent event) {
        if (!SGenre.getText().replaceAll("[^a-zA-Z ]","").isBlank()) {
            genre = SGenre.getText().replaceAll("[^a-zA-Z ]","");
            LeftStatus.setText("Search set to: " + genre);
            SGenre.setText(genre);
        } else {
            LeftStatus.setText("Genre must be valid.");
        }
    }

    /**
     * onActionSGenreSearch calls the searchByGenre method and stores the return value into a variable and depending on
     * what the method returned based on if the genre exists or the library is empty, it delivers a message on the bottom
     * Left Status or bottom Right status. If the genre is valid, the books with that genre is displayed in the info area.
     * @param event happens when the 'Search by Genre' option is chosen
     */
    @FXML
    void onActionSGenreSearch(ActionEvent event) {
        String search = currentBook.searchByGenre(genre);
        if(search.equals("")){
            LeftStatus.setText("Your Library is empty!");
            InfoArea.setText("");
        } else if(search.equalsIgnoreCase("Invalid")){
            LeftStatus.setText("Invalid entry. Book with " + genre + " genre does not exist");
            InfoArea.setText("");
        } else{
            LeftStatus.setText("Book with " + genre + " genre has been found!");
            InfoArea.setText(search);
        }
    }

    /**
     * sets text fields and visibility of all panes except the title pane to false
     * @param event when the 'Search By Rating' option is selected
     */
    @FXML
    void onActionSearchRating(ActionEvent event) {
        ResetTextFields();
        ResetVisibility();

        SRatingPane.setVisible(true);

        SearchMenu.setText("Search by rating");
    }

    /**
     * onActionSRating gets the string value inputted by the user in the SRating text field and parses the string to
     * a double. The double is then checked to be between the values of 1 and 5 and if an invalid number is entered then
     * the Left Status shows an error message and user can input a valid rating and sets rating to the valid input.
     * @param event happens when 'enter' is pressed
     */
    @FXML
    void onActionSRating(ActionEvent event) {
        String ratingString = SRating.getText().trim();
        try {
            //Attempt to parse the String to a double
            double ratingInitial = Double.parseDouble(ratingString);
            if (ratingInitial < 1 || ratingInitial > 5) {
                LeftStatus.setText("Error: Rating entry must be between 1-5");
            } else {
                //If the parsed double is valid, then set rating to it.
                rating = ratingInitial;
                LeftStatus.setText("Search set to: " + rating);
                SRating.setText(String.valueOf(rating));
            }
        } catch (NumberFormatException e) {
            LeftStatus.setText("Error: Rating must be a number!");
        }
    }

    /**
     * onActionSRatingSearch calls the searchByRating method and stores the return value into a variable and depending on
     * what the method returned based on if the rating exists or the library is empty, it delivers a message on the bottom
     * Left Status or bottom Right status. If the rating is valid, the books with that rating is displayed in the info area.
     * @param event happens when the 'Search by Rating' option is chosen
     */
    @FXML
    void onActionSRatingSearch(ActionEvent event) {
        String search = currentBook.searchByRating(rating);
        if(search.equals("")){
            LeftStatus.setText("Your Library is Empty!");
            InfoArea.setText("");
        } else if (search.equalsIgnoreCase("Invalid")) {
            LeftStatus.setText("Invalid entry. Book with a " + rating + " rating does not exist");
            InfoArea.setText("");
        }else {
            LeftStatus.setText("Books with a " + rating + " have been found!");
            InfoArea.setText(search);
        }

    }

    //OPTIONS

    //EDIT
    /**
     *
     * @param event: when the edit button is clicked
     * resets all the text fields by calling ResetTextFields();
     * to prevent it from storing previous entries, set the variables to empty strings
     */
    @FXML
    void onActionEdit(ActionEvent event) {
        ResetTextFields();
        title = "";
        author = "";
        genre = "";
        rating = -1;
        year = -1;
        ResetVisibility();
        EPane.setVisible(true);
        EditDropDown.setVisible(true);
        SPane.setVisible(false);
        STitlePane.setVisible(false);
        SRatingPane.setVisible(false);
        SGenrePane.setVisible(false);
        OptionsMenu.setText("Edit");
    }

    /**
     *
     * @param event: called when the user inputs text into the editAuthor text field
     * gets the text in the text field
     */
    @FXML
    void onActionEAuthor(ActionEvent event) {
        if (!editAuthor.getText().replaceAll("[^a-zA-Z ]","").isBlank()) {
            author = editAuthor.getText().replaceAll("[^a-zA-Z ]","");
            LeftStatus.setText("Set author to: " + author);
            editAuthor.setText(author);
            editauthor = true;
        } else {
            LeftStatus.setText("Author must have a valid name.");
            editauthor = false;
        }
    }

    /**
     *
     * @param event: called when the user inputs text into the editTitle text field
     * gets the text in the text field
     */
    @FXML
    void onActionETitle(ActionEvent event) {
        if (!editTitle.getText().isBlank()) {
            title = editTitle.getText().trim();
            LeftStatus.setText("Book to edit: " + title);
            editTitle.setText(title);
        } else {
            LeftStatus.setText("Error: Title cannot be empty.");
        }
    }

    /**
     *
     * @param event: called when the user inputs text into the editGenre text field
     * gets the text in the text field
     */
    @FXML
    void onActionEGenre(ActionEvent event) {
        if (!editGenre.getText().replaceAll("[^a-zA-Z ]","").isBlank()) {
            genre = editGenre.getText().replaceAll("[^a-zA-Z ]","");
            LeftStatus.setText("Set genre to: " + genre);
            editGenre.setText(genre);
            editgenre = true;
        } else {
            LeftStatus.setText("Genre must be valid.");
            editgenre = false;
        }
    }

    /**
     *
     * @param event: called when the user inputs text into the editYear text field
     * gets the text in the text field
     */
    @FXML
    void onActionEYear(ActionEvent event) {
        String yearString = editYear.getText().trim();
        try {
            // Attempt to parse the string to an int
            int yearInitial = Integer.parseInt(yearString);
            if (yearInitial < 0 || yearInitial > 2024) {
                LeftStatus.setText("Error: Year must be between 0 and 2024");
                edityear = false;
            } else {
                // If parsed successfully, set year to it
                year = yearInitial;
                LeftStatus.setText("Year published has been set to " + year);
                editYear.setText(String.valueOf(year));
                edityear = true;
            }
        } catch (NumberFormatException e) {
            LeftStatus.setText("Error: Year must be an Integer!");
            edityear = false;
        }
    }

    /**
     *
     * @param event: called when the user inputs text into the editRating text field
     * gets the text in the text field
     */
    @FXML
    void onActionERating(ActionEvent event) {
        String ratingString = editRating.getText().trim();
        try {
            //Attempt to parse the String to a double
            double ratingInitial = Double.parseDouble(ratingString);
            if (ratingInitial < 1 || ratingInitial > 5) {
                LeftStatus.setText("Error: Rating entry must be between 1-5");
                editrating = false;
            } else {
                //If the parsed double is valid, then set rating to it.
                rating = ratingInitial;
                LeftStatus.setText("Rating set to: " + rating);
                editRating.setText(String.valueOf(rating));
                editrating = true;
            }
        } catch (NumberFormatException e) {
            LeftStatus.setText("Error: Rating must be a number!");
            editrating = false;
        }
    }

    /**
     * @param event: called when the edit author option is selected
     * resets author and title
     * makes the author and title text field visible to collect user input
     * passes the input as arguments to editBookAuthor
     * displays success message in left status
     */
    @FXML
    void onActionEditAuthor(ActionEvent event) {
        author = "";
        title = "";
        ResetTextFields();
        ResetVisibility();
        EPane.setVisible(true);
        EditPane.setVisible(true);
        EditDropDown.setText("Edit author");
        editrating = false;
        edityear = false;
        editgenre = false;
        editauthor = false;
        //EAuthorPane.setVisible(false);
        editAuthor.setVisible(true);


    }

    /**
     * @param event: called when the edit genre option is selected
     * resets genre
     * makes the genre and title text field visible to collect user input
     * passes the input as arguments to editBookGenre
     * displays success message in left status
     */
    @FXML
    void onActionEditGenre(ActionEvent event) {
        genre = "";
        ResetVisibility();
        ResetTextFields();
        EPane.setVisible(true);
        EditPane.setVisible(true);
        editrating = false;
        edityear = false;
        editgenre = false;
        editauthor = false;
        EditDropDown.setText("Edit genre");
        editGenre.setVisible(true);
    }

    /**
     * @param event: called when the edit rating option is selected
     * resets rating
     * makes the rating and title text field visible to collect user input
     * passes the input as arguments to editBookRating
     * displays success message in left status
     */
    @FXML
    void onActionEditRating(ActionEvent event) {
        rating = -1;
        ResetVisibility();
        ResetTextFields();
        editRating.setVisible(true);
        EditPane.setVisible(true);
        EPane.setVisible(true);
        editrating = false;
        edityear = false;
        editgenre = false;
        editauthor = false;
        EditDropDown.setText("Edit rating");
    }

    /**
     * @param event: called when the edit year option is selected
     * resets year
     * makes the year and title text field visible to collect user input
     * passes the input as arguments to editBookYear
     * displays success message in left status
     */
    @FXML
    void onActionEditYear(ActionEvent event) {
        year = -1;
        ResetVisibility();
        ResetTextFields();
        EPane.setVisible(true);
        EditPane.setVisible(true);
        editYear.setVisible(true);
        editrating = false;
        edityear = false;
        editgenre = false;
        editauthor = false;
        EditDropDown.setText("Edit year published");
    }

    /**
     * Handles the action event for editing book details.\
     * depends on the choice the user makes
     * updates the corresponding details of the current book.
     * Displays appropriate status messages based on the editing outcome.
     *
     * @param event clicking an option for the edit button
     */
    @FXML
    void onActionEditButton(ActionEvent event) {
        if (editauthor) {
            int result = currentBook.editBookAuthor(title, author);
            if (result == 1) {
                LeftStatus.setText("The book: " + title + ", has been assigned a new author, " + author + ".");
            } else if (result == 2) {
                LeftStatus.setText("Invalid entry. Book title does not exist.");
            } else if (result == 3) {
                LeftStatus.setText("Your library is empty!");
            }
        } else if (editrating) {
            int result = currentBook.editBookRating(title, rating);
            if (result == 1) {
                LeftStatus.setText("The book: " + title + ", has been assigned a new rating of: " + rating + "!");
            } else if (result == 2) {
                LeftStatus.setText("Invalid entry. Book title does not exist.");
            } else if (result == 3) {
                LeftStatus.setText("Your library is empty!");
            } else {
                RightStatus.setText("Something went wrong when trying to edit rating.");
            }
        } else if (editgenre) {
            int result = currentBook.editBookGenre(title, genre);
            if (result == 1) {
                LeftStatus.setText("The book: " + title + ", has been assigned a new genre, " + genre + ".");
            } else if (result == 2) {
                LeftStatus.setText("Invalid entry. Book title does not exist.");
            } else if (result == 3) {
                LeftStatus.setText("Your library is empty!");
            }
        } else if (edityear) {
            int result = currentBook.editBookYear(title, year);
            if (result == 1) {
                LeftStatus.setText("Successfully changed year");
            } else if (result == 2) {
                LeftStatus.setText("The book: " + title + ", has been assigned a new rating of: " + year + "!");
            } else if (result == 3) {
                LeftStatus.setText("Invalid entry. Book title does not exist.");
            } else {
                RightStatus.setText("Something went wrong when trying to edit year.");
            }
        } else {
            RightStatus.setText("An error has occurred.");
        }
    }

    /**
     *
     * @param event: when the add to wishlist button is clicked
     * resets all the text fields by calling ResetTextFields();
     * resets visibility to default by calling ResetVisibility();
     * to prevent it from storing previous entries, set the variables to empty strings
     */
    @FXML
    void onActionAddWishlist(ActionEvent event) {
        //uses same menu as addBook, but uses a different button to add to the wishlist instead.
        title = "";
        author = "";
        genre = "";
        rating = -1;
        year = -1;
        ResetTextFields();
        ResetVisibility();
        ABPane.setVisible(true);
        ABAddBook.setVisible(false);
        AWAddBook.setVisible(true);
        OptionsMenu.setText("Add to Wishlist");
    }

    /**
     *
     * @param event: when add a book is selected
     * if the user input is valid, a new book is created and added to the wishlist using addABook()
     * a success/ failure message is displayed in the status bar
     */
    @FXML
    void onActionAWAddBook(ActionEvent event) {
        if (validAuthor && validGenre && validTitle && validRating && validYear) {
            wish = new BookWishlist(title, author, genre, rating, year);
            int result = wish.addABook(); // Call the method once and store its result
            if (result == 1) {
                LeftStatus.setText("The book '" + title + "' has been added to your wishlist!");
                InfoArea.setText("Recently added to wishlist: " + title);
            } else if(result == 2) {
                LeftStatus.setText("You already have this book in your library!");
                InfoArea.setText("");
            } else if (result == 3) {
                LeftStatus.setText("'" + title + "' is already in your wishlist");
            } else {
                RightStatus.setText("Something went wrong while trying to add your book!");
            }
        } else {
            LeftStatus.setText("Error adding book, not all book details are valid!");
        }
    }

    /**
     *
     * @param event: called when the user presses remove from wishlist
     * the title is reset
     * the visibilities are reset
     * remove book pane is visible and youre able to remove a book from a wishlist or in general
     */
    @FXML
    void onActionRemoveWishlist(ActionEvent event) {
        ResetTextFields();
        ResetVisibility();
        title = "";
        RBPane.setVisible(true);
        RBRemoveABook.setVisible(false);
        RBRemoveAll.setVisible(false);
        RWRemoveAll.setVisible(true);
        RWRemoveBook.setVisible(true);
        OptionsMenu.setText("Remove from Wishlist");
    }

    /**
     *
     * @param event: when the left button is pressed
     * removes a book from the wishlist and checks if it was successful
     * a success/ failure message is displayed in the status bar
     */
    @FXML
    void onActionRWRemoveAll(ActionEvent event) {
        boolean result = wish.removeAllBooks();
        if (result) {
            LeftStatus.setText("All books have been removed from the wishlist!");
            InfoArea.setText("");
        } else {
            LeftStatus.setText("Your wishlist is already empty!");
        }
    }

    /**
     *
     * @param event: when the right button is pressed
     * clears all the data
     * a success/ failure message is displayed in the status bar
     */
    @FXML
    void onActionRWRemoveBook(ActionEvent event) {
        int result = wish.removeABook(title);
        if (result == 1) {
            LeftStatus.setText("The book '" + title + "' has been removed from your wishlist!");
            InfoArea.setText("Recently removed from wishlist: " + title);
        } else if (result == 2) {
            LeftStatus.setText("The book is not in your wishlist.");
            InfoArea.setText("");
        } else if (result == 3) {
            LeftStatus.setText("Your wishlist is empty!");
            InfoArea.setText("");
        } else {
            RightStatus.setText("Something went wrong while trying to remove a book!");
        }
    }

    /**
     * @param event: called when the move to library option is selected
     * resets text fields and visibility
     */
    @FXML
    void onActionMoveToLibraryOption(ActionEvent event) {
        ResetTextFields();
        ResetVisibility();
        title = "";
        WTLPane.setVisible(true);
    }

    /**
     *
     * @param event: when the user enters a title in the title text field
     * the user input is taken from the title text field
     * displays an error/success message in status bar
     */
    @FXML
    void onActionMTLTitle(ActionEvent event) {
        if (!MTLTitle.getText().isBlank()) {
            title = MTLTitle.getText().trim();
            LeftStatus.setText("Title set to: " + title);
            MTLTitle.setText(title);
            validTitle = true;
        } else {
            LeftStatus.setText("Error: Title cannot be empty.");
            validTitle = false;
        }
    }

    /**
     *
     * @param event: gets the title from user input
     * removes the book from the wishlist and adds it to the bookList
     * displays an error/success message in status bar
     */
    @FXML
    void onActionMoveToLibrary(ActionEvent event) {
        String newBook = MTLTitle.getText().trim();
        try {
            //If the book can be moved from the wishlist to the library
            currentBook = wish.WishlistToLibrary(newBook);
            //removes the book from the wishlist and adds it to the bookList
            wish.removeABook(newBook);
            currentBook.addABook();
            LeftStatus.setText("Successfully moved " + newBook + " from your wishlist to the library!");
            RightStatus.setText("");
        } catch (NullPointerException e) {
            RightStatus.setText("Something went wrong while attempting to move your book.");
        }
    }

    //SPECIAL OPTIONS
    /**
     *
     * @param event: if the user presses the highest rated option
     * displays an error/success message in status bar
     */
    @FXML
    void onActionHighestRated(ActionEvent event) {
        ResetVisibility();
        SpecialMenu.setText("Highest Rated");
        String result = currentBook.highestRated();
        if (!result.equalsIgnoreCase("Your library is Empty.")) {
            LeftStatus.setText("Found the highest rated Book(s)!");
            InfoArea.setText(result);
        } else {
            LeftStatus.setText("Your library is empty.");
            InfoArea.setText("");
        }
    }

    /**
     *
     * @param event: if the user clicks on recommendation
     * resets visibility
     * create a book data object from the recommendation
     * displays information about the book on the right
     * displays an error/success message in status bar
     */
    @FXML
    void onActionRecommendation(ActionEvent event) {
        ResetVisibility();
        SpecialMenu.setText("Recommendation");
        BookData result = currentBook.recommendation();
        if (result != null) {
            LeftStatus.setText("Found a recommendation!");
            InfoArea.setText("We recommend: " + result.getTitle() +"!");
        } else {
            LeftStatus.setText("There are no books in the library to recommend.");
            InfoArea.setText("");
        }
    }

    /**
     *
     * @param event: if the user presses the newest entry button
     * the title is stored in a variable called result
     * displays information about the book on the right
     * displays an error/success message in status bar
     */
    @FXML
    void onActionNewestEntry(ActionEvent event) {
        ResetVisibility();
        SpecialMenu.setText("Newest Entry");
        String result = currentBook.newestEntry();
        if (!result.equalsIgnoreCase("There are no recent entries.")) {
            InfoArea.setText(result);
            LeftStatus.setText("Found most recent entry!");
        } else {
            InfoArea.setText("");
            LeftStatus.setText("There are no recent entries.");
            //pls pls pls dont be gone
        }
    }

    /**
     *
     * @param event: if user presses view wishlist button
     * calls ViewWishlist
     */
    @FXML
    void onActionViewWishlist(ActionEvent event) {
        ViewWishlist();
    }

    /**
     * displays the wishlist items in gui
     * the gui has buttons for each title in the wishlist
     * Hovering over a button displays detailed information about the corresponding item.
     * Right-clicking on a button clears the detailed information.
     */
    void ViewWishlist() {
        // COPY OF VIEWLIBRARY BUT MADE FOR WISHLIST OBJECTS
        int itemCount = 0;
        if (!BookWishlist.getWishlist().isEmpty()) {
            ResetVisibility();
            List<BookWishlist> wishlistbooks = BookWishlist.getWishlist();
            // Clears any pre-existing boards
            VLGridPane.getChildren().clear();
            VLGridPane.getColumnConstraints().clear();
            VLGridPane.getRowConstraints().clear();
            for (int i = 0; i < wishlistbooks.size(); i++) {
                Button button = new Button(wishlistbooks.get(i).getTitle());
                BookWishlist book = wishlistbooks.get(i);
                itemCount++;
                // Ideal height/width
                button.setPrefWidth(283);
                button.setPrefHeight(40);
                // Make the pane visible and add the button to the pane
                VLBorderPane.setVisible(true);
                VLLibraryLabel.setText("Wishlist");
                VLLibraryLabel.setVisible(true);
                VLGridPane.add(button, 0, i);

                //Mouse interactions with button.

                //HOVERING OVER Button
                button.setOnMouseEntered(mouseEvent -> {
                    String bookInfo = "Title: " + book.getTitle() + "\n" +
                            "Author: " + book.getAuthor() + "\n" +
                            "Genre: " + book.getGenre() + "\n" +
                            "Rating: " + book.getRating() + "\n" +
                            "Year Published: " + book.getYearPublished();
                    InfoArea.setText(bookInfo);
                });
                button.setOnMouseClicked(mouseEvent -> {
                    if ((mouseEvent.getButton() == MouseButton.SECONDARY)) {
                        InfoArea.setText("");
                    }
                });
            }

            // Set GridPane dimensions
            VLGridPane.setPrefWidth(283);
            VLBorderPane.setPrefHeight(itemCount*40);
            VLLibraryWall.setHeight(itemCount * 40);
            VLLibraryWall2.setHeight(itemCount * 40);
            LeftStatus.setText("Displaying Wishlist!");
        } else {
            LeftStatus.setText("Your wishlist is empty");
        }
    }

    /**
     *
     * @param actionEvent: if user presses view library button
     * calls ViewLibrary
     */
    @FXML
    void onActionViewLibrary(ActionEvent actionEvent) {
        ViewLibrary();
    }

    // Method to be called in multiple onActions.
    /**
     * Displays the library items in the GUI.
     * This method populates the GUI with buttons representing each item in the library.
     * Hovering over a button displays detailed information about the corresponding item.
     * Right-clicking on a button clears the detailed information.
     */
    void ViewLibrary() {
        int itemCount = 0;
        if (!BookData.getBookList().isEmpty()) {
            ResetVisibility();
            List<BookData> books = BookData.getBookList();
            // Clears any pre-existing boards
            VLGridPane.getChildren().clear();
            VLGridPane.getColumnConstraints().clear();
            VLGridPane.getRowConstraints().clear();
            for (int i = 0; i < books.size(); i++) {
                Button button = new Button(books.get(i).getTitle());
                BookData book = books.get(i);
                itemCount++;
                // Ideal height/width
                button.setPrefWidth(283);
                button.setPrefHeight(40);
                // Make the pane visible and add the button to the pane
                VLBorderPane.setVisible(true);
                VLLibraryLabel.setText("Library");
                VLLibraryLabel.setVisible(true);
                VLGridPane.add(button, 0, i);

                //Mouse interactions with button.

                //HOVERING OVER Button
                button.setOnMouseEntered(mouseEvent -> {
                            String bookInfo = "Title: " + book.getTitle() + "\n" +
                                    "Author: " + book.getAuthor() + "\n" +
                                    "Genre: " + book.getGenre() + "\n" +
                                    "Rating: " + book.getRating() + "\n" +
                                    "Year Published: " + book.getYearPublished();
                            InfoArea.setText(bookInfo);
                        });
                button.setOnMouseClicked(mouseEvent -> {
                    if ((mouseEvent.getButton() == MouseButton.SECONDARY)) {
                        InfoArea.setText("");
                    }
                });
            }

            // Set GridPane dimensions
            VLGridPane.setPrefWidth(283);
            VLBorderPane.setPrefHeight(itemCount*40);
            VLLibraryWall.setHeight(itemCount * 40);
            VLLibraryWall2.setHeight(itemCount * 40);
            LeftStatus.setText("Displaying Library!");
        } else {
            LeftStatus.setText("Your library is empty");
        }
    }

    /**
     *
     * @param event: when the about button is clicked
     * Displays an alert box with information about the authors, emails, version, and purpose of the project.
     */
    @FXML
    void onActionAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setContentText("""
                Authors: Halima Ahmed, Zainab Majid, Isaac Mogensen \s
                Emails:  \s
                isaac.mogensen@ucalgary.ca \s
                halima.ahmed1@ucalgary.ca \s
                zainab.majid@ucalgary.ca \s
                Version: v1.0 \s
                This is a Library Editor for Books and Wishlist items \s
                """);
        alert.showAndWait();
    }

    /**
     *
     * @param event: when the user presses the load button
     * loads a library from a saved csv file
     * uses file chooser to choose the file
     * reads the file and updates data
     * displays contents of file in gui
     * displays an error/success message in status bar
     */
    @FXML
    void onActionLoadLibrary(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        // send user to home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        File fileLoad = fileChooser.showOpenDialog(new Stage());
        if (fileLoad != null) {
            try {
                BookData.getBookList().clear();
                for (int i = 0; i < FileOperations.readFromFileData(fileLoad).size(); i++)
                    FileOperations.readFromFileData(fileLoad).get(i).addABook();
                ViewLibrary();
            } catch (Exception e) {
                  RightStatus.setText("There was a problem loading the file.");
            }
        } else {
            RightStatus.setText("Cancelled");
        }
    }

    // Same as library but for the wishlist
    /**
     *
     * @param event: when the user presses the load button
     * loads a wishlist from a saved csv file
     * uses file chooser to choose the file
     * reads the file and updates data
     * displays contents of file in gui
     * displays an error/success message in status bar
     */
    @FXML
    void onActionLoadWishlist(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        // send user to home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        File fileLoad = fileChooser.showOpenDialog(new Stage());
        if (fileLoad != null) {
            try {
                BookWishlist.getWishlist().clear();
                for (int i = 0; i < FileOperations.readFromFileWishlist(fileLoad).size(); i++)
                    FileOperations.readFromFileWishlist(fileLoad).get(i).addABook();
                ViewWishlist();
            } catch (Exception e) {
                RightStatus.setText("There was a problem loading the file.");
            }
        } else {
            RightStatus.setText("Cancelled");
        }
    }

    /**
     * saves the updated library to a csv file
     * uses fle chooser to allow user to pick where to save the file
     * Saves the current library data to the selected file in CSV format.
     * If an error occurs during saving, displays an error message.
     *
     * @param event when the user presses the save as library button.
     */
    @FXML
    void onActionSaveAsLibrary(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV Files", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                FileOperations.saveToFileData(file, BookData.getBookList());
            } catch (Exception e) {
                RightStatus.setText("There was a problem saving the file.");
            }
        } else {
            LeftStatus.setText("Cancelled");
        }
    }

     // Same as library but for the wishlist
    /**
     * saves the updated wishlist to a csv file
     * uses fle chooser to allow user to pick where to save the file
     * Saves the current library data to the selected file in CSV format.
     * If an error occurs during saving, displays an error message.
     *
     * @param event when the user presses the save as wishlist button.
     */
    @FXML
    void onActionSaveAsWishlist(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV Files", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                FileOperations.saveToFileWishlist(file, BookWishlist.getWishlist());
            } catch (Exception e) {
                RightStatus.setText("There was a problem saving the file.");
            }
        } else {
            LeftStatus.setText("Cancelled");
        }
    }

    /**
     *
     * @param event: pressing the quit button
     * quits the program when clicked
     */
    @FXML
    void onActionQuit(ActionEvent event) {
        LeftStatus.setText("Exiting Program!");
        System.exit(1);
    }

    /**
     * resets visibility of every text field after the action has been done
     */
    void ResetTextFields() {
        ABAuthor.setText("");
        ABGenre.setText("");
        ABRating.setText("");
        ABTitle.setText("");
        ABYear.setText("");
        RBTitle.setText("");
        STitle.setText("");
        SAuthor.setText("");
        SRating.setText("");
        SGenre.setText("");
        MTLTitle.setText("");
        editTitle.setText("");
        editAuthor.setText("");
        editYear.setText("");
        editGenre.setText("");
        editRating.setText("");
    }

    /**
     * Sets the visibility of each pane to false
     */
    void ResetVisibility() {
        ABPane.setVisible(false);
        RBPane.setVisible(false);
        EPane.setVisible(false);
        WTLPane.setVisible(false);
        VLBorderPane.setVisible(false);
        VLLibraryLabel.setVisible(false);
        editAuthor.setVisible(false);
        editRating.setVisible(false);
        editYear.setVisible(false);
        editGenre.setVisible(false);
        EditPane.setVisible(false);
        SPane.setVisible(false);
        SRatingPane.setVisible(false);
        SGenrePane.setVisible(false);
        STitlePane.setVisible(false);
    }
}