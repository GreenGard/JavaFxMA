module se.iths.se.javafxlabb3maxblom {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.iths.se.javafx.labb3maxblom to javafx.fxml;
    exports se.iths.se.javafx.labb3maxblom;
}