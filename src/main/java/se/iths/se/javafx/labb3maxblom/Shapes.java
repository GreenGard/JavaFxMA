package se.iths.se.javafx.labb3maxblom;

import javafx.scene.paint.Color;



public class Shapes {
    public static Shape circleOf(double x, double y, double radius, Color color) {
        return new Circle(color, x, y, radius);
    }

    public static Shape rectangleOf(double x, double y, double size, Color color) {
        return new Rectangle(color, x, y, size);
    }
}
