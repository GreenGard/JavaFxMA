module se.iths.se.javafxlabb3maxblom {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;


    opens se.iths.se.javafx.labb3maxblom to javafx.fxml;
    exports se.iths.se.javafx.labb3maxblom;
}