package se.iths.se.javafx.labb3maxblom;

import javafx.scene.paint.Color;

import java.util.regex.Pattern;

public class Shapes {
    public static Circle circleOf(double x, double y, double radius, Color color) {
        return new Circle(color, x, y, radius);
    }

    public static Square rectangleOf(double x, double y, double size, Color color) {
        return new Square(color, x, y, size);
    }
    public static Shape convertSVGToShape(String line) {
        try {
            Pattern pattern = Pattern.compile("=");
            String[] svgStringArray = pattern.split(line);

            if (line.contains("rect")) {
                return getRectangle(svgStringArray);
            } else {
                return getCircle(svgStringArray);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException();
        }
    }
        private static Square getRectangle(String[] svgStringArray) {
            double size = Double.parseDouble(svgStringArray[3].substring(1, 5)) / 2;
            double x = Double.parseDouble(svgStringArray[1].substring(1, 5)) + size;
            double y = Double.parseDouble(svgStringArray[2].substring(1, 5)) + size;
            Color rectColor = Color.valueOf(svgStringArray[5].substring(1, 10));

            return rectangleOf( size, x, y, rectColor);
        }

        private static Circle getCircle(String[] svgStringArray) {
            double size = Double.parseDouble(svgStringArray[3].substring(1, 5));
            double x = Double.parseDouble(svgStringArray[1].substring(1, 5));
            double y = Double.parseDouble(svgStringArray[2].substring(1, 5));
            Color circleColor = Color.valueOf(svgStringArray[4].substring(1, 10));

            return circleOf( x, y, size, circleColor);
        }
}
