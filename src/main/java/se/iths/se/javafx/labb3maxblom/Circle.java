package se.iths.se.javafx.labb3maxblom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Circle extends Shape {
    private double radius = this.getSize();

    public Circle(Color color, double x, double y, double radius) {
        super(color, x, y, radius);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.getColor());
        radius = this.getSize();
        graphicsContext.fillOval(getX() - radius, getY() - radius, 2 * radius, 2 * radius);
    }

    @Override
    public boolean isInside(double x, double y) {
        radius = this.getSize();
        double dx = x - getX();
        double dy = y - getY();

        double distanceFromCircleCenterSquared = dx * dx + dy * dy;

        return distanceFromCircleCenterSquared < radius * radius;
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

