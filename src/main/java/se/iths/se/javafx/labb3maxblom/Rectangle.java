package se.iths.se.javafx.labb3maxblom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Rectangle extends Shape {
    private double size = this.getSize();

    public Rectangle(Color color, double x, double y, double size) {
        super(color, x, y, size);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        size = this.getSize();
        double halfSize = size * 0.5;
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillRect(getX() - halfSize, getY() - halfSize, size, size);
    }

    @Override
    public boolean isInside(double x, double y) {
        size = this.getSize();
        double dx = x - getX();
        double dy = y - getY();

        double distanceFromRectangleCenterSquared =  dx * dx + dy * dy;

        return distanceFromRectangleCenterSquared < size * size;
    }
}

