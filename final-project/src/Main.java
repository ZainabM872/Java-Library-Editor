import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                FileOperations.readFromFileData(args[0]);
                FileOperations.readFromFileWishlist(args[0]);
            } catch (IOException e) {
                System.out.println("Could not read from file: " + e.getMessage());
            }
        }
        Menu.menu();
    }
}
