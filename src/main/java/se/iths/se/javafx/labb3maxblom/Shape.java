package se.iths.se.javafx.labb3maxblom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract sealed class Shape permits Circle, Square {
    private Color color;
    private double x;
    private double y;
    private double size;

    public Shape(Color color, double x, double y, double size) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public abstract void draw(GraphicsContext graphicsContext);

    public abstract boolean isInside(double x, double y);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSize(){
        return size;
    }

    public void setSize(double size){
        this.size = size;
    }
    public abstract String drawSVG();
}