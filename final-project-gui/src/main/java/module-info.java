module ca.ucalgary.finalprojectguit {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.ucalgary.finalprojectgui to javafx.fxml;
    exports ca.ucalgary.finalprojectgui;
}