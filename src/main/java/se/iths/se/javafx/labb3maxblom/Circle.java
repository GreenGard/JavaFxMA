package se.iths.se.javafx.labb3maxblom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Circle extends Shape {


    public Circle(Color color, double x, double y, double radius) {
        super(color, x, y, radius);
    }

    public Circle(Shape shape) {
        super(shape);
    }

    @Override
    public Shape copyOf() {
        return new Circle(this);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.getColor());

        graphicsContext.fillOval(getX() - getSize(), getY() - getSize(), 2 * getSize(), 2 * getSize());
    }

    @Override
    public boolean isInside(double x, double y) {

        double dx = x - getX();
        double dy = y - getY();

        double distanceFromCircleCenterSquared = dx * dx + dy * dy;

        return distanceFromCircleCenterSquared < getSize() * getSize();
    }

    @Override
    public String drawSVG() {
        String convertedColor = "#" + getColor().toString().substring(2, 10);

        return "<circle cx=\"" + getX() + "\" " +
                "cy=\"" + getY() + "\" " +
                "r=\"" + getSize() + "\" " +
                "fill=\"" + convertedColor + "\" />";
    }
}

