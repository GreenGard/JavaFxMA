module se.iths.se.javafxlabb3maxblom {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires junit;
    requires org.testng;


    opens se.iths.se.javafx.labb3maxblom to javafx.fxml;
    exports se.iths.se.javafx.labb3maxblom;
}