import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class BookTest {
    BookData book1 = new BookData("","","",0,0);
    BookData book2 = new BookData("","","",0,0);
    BookData book3 = new BookData("","","",0,0);
    BookWishlist wishlist = new BookWishlist("","","",0,0);
    BookWishlist example = new BookWishlist("","","",0,0);
    //Clears every data structure before each test
    @BeforeEach
    public void prepareToTest(){
        BookData.getBookList().clear();
        BookWishlist.getWishlist().clear();
    }
    @Test
    public void addBookSuccess(){
        book1 = new BookData("Harry Potter", "Rowling","Fantasy",5.0, 1997);
        book1.addABook();
        List<String> example = new ArrayList<>();
        example.add("Harry Potter");
        assertEquals(example.getFirst(), BookData.getBookList().getFirst().getTitle());
    }
    @Test
    public void addBookReverse(){
        book1 = new BookData("Rowling","Harry Potter","Fantasy",5.0, 1997);
        book1.addABook();
        List<String> example = new ArrayList<>();
        example.add("Harry Potter");
        assertNotEquals(example.getFirst(), BookData.getBookList().getFirst().getTitle());
    }
    @Test
    public void searchByAuthorSuccess(){
        book1 = new BookData("The Giver", "Lowry", "x", 3.4, 2014);
        book1.addABook();
        book2 = new BookData("Divergent", "Roth", "x", 4.5,1998);
        book2.addABook();
        book3 = new BookData("The Book Thief", "Zusak", "x", 2.1, 1898);
        book3.addABook();
        assertTrue(book2.searchByAuthor("Roth"));

    }
    @Test
    public void searchByAuthorFail(){
        book1 = new BookData("The Giver", "Lowry", "x", 3.4, 2014);
        book1.addABook();
        book2 = new BookData("Divergent", "Roth", "x", 4.5,1998);
        book2.addABook();
        assertFalse(book2.searchByAuthor("Cena"));
    }
    @Test
    public void searchByTitleSuccess(){
        book1 = new BookData("Jimbos Great Adventure","x","x",1,1);
        book1.addABook();
        assertTrue(book1.searchByTitle("Jimbos Great Adventure"));
    }
    @Test
    public void searchByTitleFail() {
        book1 = new BookData("Jimbos Great Adventure","x","x",1,1);
        book1.addABook();
        assertFalse(book1.searchByTitle("The Giver"));
    }
    @Test
    public void searchByGenreSuccess(){
        book1 = new BookData("The Lion and The Others", "Narnia", "Fantasy", 3.4, 2005);
        book1.addABook();
        book2 = new BookData("Hunger Games", "Lawrence", "Dystopian", 2.1,2009);
        book2.addABook();
        assertTrue(book1.searchByGenre("Fantasy"));
    }
    @Test
    public void searchByGenreFail(){
        book1 = new BookData("The Lion and The Others", "Narnia", "Fantasy", 3.4, 2005);
        book1.addABook();
        book2 = new BookData("Hunger Games", "Lawrence", "Dystopian", 2.1,2009);
        book2.addABook();
        assertFalse(book2.searchByGenre("DisSopian"));
    }
    @Test
    public void searchByRatingSuccess(){
        book1 = new BookData("Black Swan", "Portman", "Drama", 3.4, 2023);
        book1.addABook();
        book2 = new BookData("Mice of Men", "Stuart", "Children", 5.0,1984);
        book2.addABook();
        assertTrue(book1.searchByRating(3.4));
    }
    @Test
    public void searchByRatingFail(){
        book1 = new BookData("Slack Bwan", "Hortman", "Dramama", 3.4, 2023);
        book1.addABook();
        book2 = new BookData("Black Swan", "Portman", "Drama", 3.4, 2023);
        book2.addABook();
        assertFalse(book1.searchByRating(5.9));
    }
    @Test
    public void removeBookSuccess(){
        book1 = new BookData("The DaVinci Code", "Brown","x",5,1);
        book1.addABook();
        book1.removeABook("The DaVinci Code");
        List<BookData> example = new ArrayList<>();
        assertEquals(example, BookData.getBookList());
    }

    @Test
    public void removeBookFail(){
        book1 = new BookData("The DaVinci Code", "Brown","x",5,1);
        book1.addABook();
        book1 = new BookData("The Giver","Lowry","x", 1,1);
        book1.addABook();
        book1.removeABook("The DaVinci Code");
        List<BookData> example = new ArrayList<>();
        assertNotEquals(example, BookData.getBookList());
    }
    @Test
    public void removeAllBookSuccess(){
        book1 = new BookData("To Kill A Mockingbird", "Le","x",5, 1857);
        book1.addABook();
        book1 = new BookData("Romeo and Juliet", "Shakespeare","x",4, 1700 );
        book1.addABook();
        book1 = new BookData("Diary of a Wimpy Kid", "Jeff","x",3, 1435);
        book1.addABook();
        book1.removeAllBooks();
        List<BookData> example = new ArrayList<>();
        assertEquals(example, BookData.getBookList());
    }
    @Test
    public void removeAllBookThenAddBook(){
        book1.removeAllBooks();
        book1 = new BookData("To Kill A Mockingbird", "Le","x",5, 1857);
        book1.addABook();
        List<BookData> example = new ArrayList<>();
        assertNotEquals(example, BookData.getBookList());
    }
    @Test
    public void editBookAuthorSuccess(){
        book1 = new BookData("To Kill A Mockingbird", "Le","x",5, 1857);
        book1.addABook();
        book2 = new BookData("Romeo and Juliet", "Shakespeare","x",4, 1700 );
        book2.addABook();
        book1.editBookAuthor("To Kill A Mockingbird","Leen");
        String expected = "Leen";
        assertEquals(expected, book1.getAuthor());
    }
    @Test
    public void editBookAuthorNull(){
        book1 = new BookData("Romeo and Juliet", "Shakespeare","x",4, 1700 );
        book1.addABook();
        book1.editBookAuthor("Romeo and Juliet",null);
        assertNull(book1.getAuthor());
    }
    @Test
    public void editBookAuthorEditingNonExistentBook(){
        book1 = new BookData("Chicka Chicka Boom Boom","Jones","x",5.0,1);
        book1.addABook();
        book1.editBookAuthor(null,null);
        String expected = "Jones";
        assertEquals(expected, book1.getAuthor());
    }
    @Test
    public void editBookGenreDoesNotExist(){
        book1 = new BookData("The Cat in the Hat", "Seuss","Horror",3.2,1977);
        book1.addABook();
        book1.editBookGenre("The Cat in the Hat", "Comedy");
        assertNotEquals("Horror",book1.getGenre());
    }
    @Test
    public void editBookGenrePreviousValueWritten() {
        book1 = new BookData("Texas Chainsaw: Lullabyebye", "Kruger","Kids",5.0,1999);
        book1.addABook();
        book1.editBookGenre("Texas Chainsaw: Lullabyebye","Family");
        assertNotEquals("Kids",book1.getGenre());
    }
    @Test
    public void editBookRatingSuccessfulChange(){
        book1 = new BookData("Geronimo Stilton", "Lowry", "Grate-est", 4.7, 2006);
        book1.addABook();
        book1.editBookRating("Geronimo Stilton",5.0);
        assertEquals(5.0, book1.getRating());
    }
    @Test
    public void editBookRatingDoesNotExist(){
        book1 = new BookData("Timothy Goes To School", "Franklin", "Melancholy", 3.4, 1967);
        book1.addABook();
        book1.editBookRating("Timmy goes to work",1.3);
        assertNotEquals(1.3, book1.getRating());
    }
    @Test
    public void editBookYearSameForDifferentBooks(){
        book1 = new BookData("Goosebumps", "Stein","Horror",2.4,2011);
        book1.addABook();
        book2 = new BookData("Among the Hidden", "Luke", "Dystopia", 3.4, 2001);
        book2.addABook();
        book2.editBookYear("Among the Hidden",2011);
        assertEquals(book1.getYearPublished(), book2.getYearPublished());
    }
    @Test
    public void editBookYearNotSameYear(){
        book1 = new BookData("Goosebumps", "Stein","Horror",2.4,2011);
        book1.addABook();
        book2 = new BookData("Among the Hidden", "Luke", "Dystopia", 3.4, 2001);
        book2.addABook();
        book2.editBookYear("Among the Hidden",2019);
        assertNotEquals(book1.getYearPublished(), book2.getYearPublished());
    }

    @Test
    public void addToWishlistSuccess(){
        wishlist = new BookWishlist("Humpty Dumpty", "x", "y", 2.0, 1954);
        wishlist.addABook();
        assertEquals("Humpty Dumpty", wishlist.getTitle());
    }
    @Test
    public void addToWishlistAlreadyAdded(){
        wishlist = new BookWishlist("Percy Jackson", "x", "y", 2.0, 1954);
        wishlist.addABook();
        wishlist = new BookWishlist("Percy Jackson", "x", "y", 2.0, 1954);
        wishlist.addABook();
        List<BookWishlist> expected = new ArrayList<>();
        expected.add(wishlist);
        assertEquals(expected, BookWishlist.getWishlist());
    }
    @Test
    public void removeFromWishlistSuccess(){
        BookWishlist.getWishlist().clear();
        wishlist = new BookWishlist("Captain Underpants", "x", "y", 2.0, 1954);
        wishlist.addABook();
        wishlist.removeABook("Captain Underpants");
        List<BookWishlist> example = new ArrayList<>();
        assertEquals(example, BookWishlist.getWishlist());
    }
    @Test
    public void removeFromWishlistEmpty(){
        wishlist.removeABook("Something");
        List<BookWishlist> expected = new ArrayList<>();
        assertEquals(expected, BookWishlist.getWishlist());
    }
    @Test
    public void removeFromWishlistNotInWishlist(){
        wishlist = new BookWishlist("Captain Underpants", "x", "y", 2.0, 1954);
        wishlist.addABook();
        wishlist.removeABook("Book Y");
        List<BookWishlist> expected = new ArrayList<>();
        expected.add(wishlist);
        assertEquals(expected, BookWishlist.getWishlist());
    }
    @Test
    public void WishlistToLibrarySuccessfulConversion() {
        wishlist = new BookWishlist("Captain Underpants", "x", "y", 2.0, 1954);
        wishlist.addABook();
        assertEquals(new BookData("Captain Underpants", "x", "y", 2.0, 1954), wishlist.WishlistToLibrary("Captain Underpants"));
    }
    @Test
    public void WishlistToLibraryWrongEntry() {
        wishlist = new BookWishlist("Captain Underpants", "x", "y", 2.0, 1954);
        wishlist.addABook();
        assertNull(wishlist.WishlistToLibrary("Someone"));
    }
    @Test
    public void toStringSuccess() {
        book1 = new BookData("Hello", "me","me",1, 0);
        assertEquals("\nTitle: " + "Hello" +
                "\nAuthor: " + "me" +
                "\nGenre: " + "me" +
                "\nRating: " + "1.0" +
                "\nYear Published: " + "0" + "\n", book1.toString());
    }
    @Test
    public void extendEqualsSuccess() {
        book1 = new BookData("Hello", "me","me",1, 0);
        book2 = new BookData("Hello", "me","me",1, 0);
        assertTrue(book1.equals(book2));
    }
    @Test
    public void extendEqualsFail() {
        book1 = new BookData("","","",1,0);
        book2 = new BookData("Hello", "me","me",1, 0);
        assertFalse(book1.equals(book2));
    }
}
