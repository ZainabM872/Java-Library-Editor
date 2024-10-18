import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.*;

public class Demo1DataTest {
    //Clears every data structure before each test
    @BeforeEach
    public void prepareToTest(){
        Demo1Data.removeAllBooks();
        Demo1Data.clearWishlist();
    }
    @Test
    public void addBookSuccess(){
        Demo1Data.addABook("Harry Potter", 5.0, 1997, "Rowling", "Fantasy");
        HashMap<String, String> example = new HashMap<>();
        example.put("Harry Potter", "Rowling");
        assertEquals(example, Demo1Data.getAuthorMap());
    }
    @Test
    public void addBookReverse(){
        Demo1Data.addABook("Harry Potter", 5.0, 1996, "Rowling", "Fantasy");
        HashMap<String, String> example = new HashMap<>();
        example.put("Rowling","Harry Potter");
        assertNotEquals(example, Demo1Data.getAuthorMap());
    }

    @Test
    public void removeBookSuccess(){
        Demo1Data.addABook("The DaVinci Code", 5,1,"Brown", "x");
        Demo1Data.removeABook("The DaVinci Code");
        HashMap<String, String> example = new HashMap<>();
        assertEquals(example, Demo1Data.getAuthorMap());
    }

    @Test
    public void removeBookFail(){
        Demo1Data.addABook("The DaVinci Code", 1,1,"Brown", "x");
        Demo1Data.addABook("The Giver",1,1,"Lowry", "x");
        Demo1Data.removeABook("The DaVinci Code");
        HashMap<String, String> example = new HashMap<>();
        assertNotEquals(example, Demo1Data.getAuthorMap());
    }

    @Test
    public void removeAllBookSuccess(){
        Demo1Data.addABook("To Kill A Mockingbird", 5, 1857, "Le", "x");
        Demo1Data.addABook("Romeo and Juliet", 4, 1700, "Shakespeare", "x");
        Demo1Data.addABook("Diary of a Wimpy Kid", 3, 1435, "Jeff", "x");
        Demo1Data.removeAllBooks();
        HashMap<String, String> example = new HashMap<>();
        assertEquals(example, Demo1Data.getAuthorMap());
    }
    @Test
    public void removeAllBookThenAddBook(){
        Demo1Data.removeAllBooks();
        Demo1Data.addABook("Chicka Chicka Boom Boom", 5, 1857, "Jones", "x");

        HashMap<String, String> example = new HashMap<>();
        assertNotEquals(example, Demo1Data.getAuthorMap());
    }
    @Test
    public void searchByAuthorSuccess(){
        Demo1Data.addABook("The Giver", 3.4, 2014, "Lowry", "x");
        Demo1Data.addABook("Divergent", 4.5, 1946, "Roth", "x");
        Demo1Data.addABook("The Book Thief", 2.4, 3453, "Zusak", "x");

        String result = Demo1Data.searchByAuthor("Roth");
        assertEquals("Books by Roth:\nTitle: Divergent", result);

    }
    @Test
    public void searchByAuthorFail(){
        Demo1Data.addABook("Romeo and Juliet",1,1,"Shakespeare","x");
        Demo1Data.addABook("Divergent",1,1,"Roth","x");
        String result = Demo1Data.searchByAuthor("Zusak");
        assertEquals("Invalid entry. Author does not exist.", result);
    }
    @Test
    public void searchByTitleSuccess(){
        Demo1Data.addABook("Jimbos Great Adventure",1,1,"x","x");
        assertEquals("Title: Jimbos Great Adventure" +
                "\nAuthor: x" +
                "\nGenre: x" +
                "\nRating: 1.0" +
                "\nYear Published: 1", Demo1Data.searchByTitle("Jimbos Great Adventure"));
    }
    @Test
    public void searchByTitleFail() {
        Demo1Data.addABook("Jimbos Great Adventure", 1, 1, "x", "x");
        assertEquals("Invalid entry. Title does not exist.", Demo1Data.searchByTitle("nothing"));
    }
    @Test
    public void searchByRatingSuccess(){
        Demo1Data.addABook("Romeo and Juliet",4.2,1,"Shakespeare","x");
        Demo1Data.addABook("The Book Thief",5.0,1,"x","x");
        String result = Demo1Data.searchByRating(5.0);
        assertEquals("Books with a 5.0 rating:\nTitle: The Book Thief", result);
    }
    @Test
    public void searchByRatingFail(){
        String result = Demo1Data.searchByRating(0);
        assertEquals("Invalid rating. Please enter a number between 1 and 5:", result);
    }
    @Test
    public void searchByRatingNull(){
        Demo1Data.addABook("To Kill a Mocking Bird",5.0,1,"x","x");
        String result = Demo1Data.searchByRating(1.1);
        assertNull(result);
    }
    @Test
    public void editBookAuthorSuccess(){
        Demo1Data.addABook("Chicka Chicka Boom Boom",5.0,1,"Jones","x");
        Demo1Data.editBookAuthor("Chicka Chicka Boom Boom","Archambault");
        HashMap<String, String> example = new HashMap<>();
        example.put("Chicka Chicka Boom Boom","Archambault");
        assertEquals(example, Demo1Data.getAuthorMap());
    }
    @Test
    public void editBookAuthorNotNull(){
        Demo1Data.addABook("Chicka Chicka Boom Boom",5.0,1,"Jones","x");
        Demo1Data.editBookAuthor(null,null);
        HashMap<String, String> example = new HashMap<>();
        assertNotNull(Demo1Data.getAuthorMap());
    }
    @Test
    public void editBookGenreDoesNotExist(){
        Demo1Data.addABook("The Cat in the Hat", 5,1,"x","Horror");
        Demo1Data.editBookGenre("The Cat in the Hat", "Comedy");
        Demo1Data.addABook("Fast and the Furious", 1,1,"x","Family");
        assertFalse(Demo1Data.getGenreMap().containsValue("Horror"));
    }
    @Test
    public void editBookGenrePreviousValueWritten() {
        //Data.genreMap.put("Texas Chainsaw", "Kids");
        //Data.genreMap.replace("Texas Chainsaw", "Comedy");
        Demo1Data.addABook("Texas Chainsaw", 5,1,"x","Kids");
        Demo1Data.addABook("Texas Chainsaw", 5,1,"x","Comedy");
        //Data.editBookGenre("The Cat in the Hat", "Comedy");
        assertFalse(Demo1Data.getGenreMap().containsValue("Kids"));
    }
    @Test
    public void editBookRatingSuccessfulChange(){
        Demo1Data.addABook("Geronimo Stilton", 4.8, 2014, "Lowry", "x");
        Demo1Data.editBookRating("Geronimo Stilton",4.8);
        assertEquals(4.8, Demo1Data.getRatingMap().get("Geronimo Stilton"));
    }
    @Test
    public void editBookRatingDoesNotExist(){
        Demo1Data.addABook("Timothy Goes To School", 3.1, 1943, "z", "x");
        Demo1Data.editBookRating("Timmy goes to work",1.3);
        assertNotEquals(3.1, Demo1Data.getRatingMap().get("Timmy goes to work"));
    }
    @Test
    public void editBookYearSameForDifferentBooks(){
        Demo1Data.addABook("Goosebumps", 3.0,2002,"x","x");
        Demo1Data.addABook("Among the Hidden", 1, 1987, "x", "x");
        Demo1Data.editBookYear("Among the Hidden",2002);
        assertTrue(Objects.equals(Demo1Data.getYearMap().get("Goosebumps"), Demo1Data.getYearMap().get("Among the Hidden")));
    }
    @Test
    public void editBookYearNotSameYear(){
        Demo1Data.addABook("Goosebumps", 3.0,2002,"x","x");
        Demo1Data.addABook("Among the Hidden", 2.5, 1987, "x", "x");
        Demo1Data.editBookYear("Among the Hidden",1989);
        assertFalse(Objects.equals(Demo1Data.getYearMap().get("Goosebumps"), Demo1Data.getYearMap().get("Among the Hidden")));
    }

    @Test
    public void addToWishlistSuccess(){
        Demo1Data.addToWishlist("Humpty Dumpty");
        List<String> example = new ArrayList<>();
        example.add("Humpty Dumpty");
        assertEquals(example, Demo1Data.getWishlist());
    }
    @Test
    public void addToWishlistAlreadyAdded(){
        Demo1Data.addToWishlist("Percy Jackson");
        Demo1Data.addToWishlist("Percy Jackson");
        List<String> example = new ArrayList<>();
        example.add("Percy Jackson");
        example.add("Percy Jackson");
        assertNotEquals(example, Demo1Data.getWishlist());
    }
    @Test
    public void removeFromWishlistSuccess(){
        Demo1Data.addToWishlist("Captain Underpants");
        Demo1Data.removeFromWishlist("Captain Underpants");
        List<String> example = new ArrayList<>();
        assertEquals(example, Demo1Data.getWishlist());
    }
    @Test
    public void removeFromWishlistEmpty(){
        Demo1Data.removeFromWishlist("Something");
        assertTrue(Demo1Data.getWishlist().isEmpty());
    }
    @Test
    public void removeFromWishlistNotInWishlist(){
        Demo1Data.addToWishlist("Book X");
        Demo1Data.removeFromWishlist("Book Y");
        assertTrue(Demo1Data.getWishlist().contains("Book X"));
    }
}
