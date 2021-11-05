package se.iths.se.javafx.labb3maxblom;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import se.iths.se.javafx.labb3maxblom.Model;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Model model = new Model();

    @Test
    void getColor() {
        model.setColor("Black");
        assertTrue(model.getColor() == Color.BLACK);
    }

    @Test
    void setColor() {
        model.setColor("Black");
        assertTrue(model.getColor() == Color.BLACK);
    }
}