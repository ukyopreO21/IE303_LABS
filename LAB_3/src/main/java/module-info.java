module com.shoestore {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.shoestore to javafx.fxml;
    exports com.shoestore;
}