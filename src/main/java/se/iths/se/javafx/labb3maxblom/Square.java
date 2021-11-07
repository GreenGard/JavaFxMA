package se.iths.se.javafx.labb3maxblom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Square extends Shape {

    public Square(Color color, double x, double y, double size)
    {
        super(color, x, y, size);
    }

    public Square(Shape shape) {
        super(shape);

    }

    @Override
    public Shape copyOf() {
        return new Square(this);
    }

    public String drawSVG() {
        String convertedColor = "#" + getColor().toString().substring(2, 10);

        return "<rect x=\"" + (getX() - getSize()) + "\" " +
                "y=\"" + (getY() - getSize()) + "\" " +
                "width=\"" + (2 * getSize()) + "\" " +
                "height=\"" + (2 * getSize()) + "\" " +
                "fill=\"" + convertedColor + "\" />";
    }
    @Override
    public void draw(GraphicsContext graphicsContext) {

        double halfSize = getSize() * 0.5;
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillRect(getX() - halfSize, getY() - halfSize, getSize(), getSize());
    }

    @Override
    public boolean isInside(double x, double y) {

        double dx = x - getX();
        double dy = y - getY();

        double distanceFromRectangleCenterSquared =  dx * dx + dy * dy;

        return distanceFromRectangleCenterSquared < getSize() * getSize();
    }
}

