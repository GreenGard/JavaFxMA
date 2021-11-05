package se.iths.se.javafx.labb3maxblom;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    public List<Shape> shapes = new ArrayList<>();

    @Test
    void getColor() {
        shapes.add(Shapes.circleOf(5.0,5.0,50, Color.BLACK));
        shapes.stream()
                .filter(shape -> shape.isInside(5, 5))
                .findFirst().ifPresent(shape -> {
                    assertTrue(shape.getColor() == Color.BLACK);
                });
    }

    @Test
    void setColor() {
        shapes.add(Shapes.circleOf(5.0,5.0,50, Color.BLACK));
        shapes.stream()
                .filter(shape -> shape.isInside(5, 5))
                .findFirst().ifPresent(shape -> {
                    shape.setColor(Color.WHITE);
                    assertTrue(shape.getColor() == Color.WHITE);
                });
    }

    @Test
    void getX() {
        shapes.add(Shapes.circleOf(5.0,5.0,50, Color.BLACK));
        shapes.stream()
                .filter(shape -> shape.isInside(5, 5))
                .findFirst().ifPresent(shape -> {
                    assertTrue(shape.getX() == 5);
                });
    }

    @Test
    void setX() {
        shapes.add(Shapes.circleOf(5.0,5.0,50, Color.BLACK));
        shapes.stream()
                .filter(shape -> shape.isInside(5, 5))
                .findFirst().ifPresent(shape -> {
                    shape.setX(10);
                    assertTrue(shape.getX() == 10);
                });
    }

    @Test
    void getY() {
        shapes.add(Shapes.circleOf(5.0,5.0,50, Color.BLACK));
        shapes.stream()
                .filter(shape -> shape.isInside(5, 5))
                .findFirst().ifPresent(shape -> {
                    assertTrue(shape.getY() == 5);
                });
    }

    @Test
    void setY() {
        shapes.add(Shapes.circleOf(5.0,5.0,50, Color.BLACK));
        shapes.stream()
                .filter(shape -> shape.isInside(5, 5))
                .findFirst().ifPresent(shape -> {
                    shape.setY(10);
                    assertTrue(shape.getY() == 10);
                });
    }

    @Test
    void getSize() {
        shapes.add(Shapes.circleOf(5.0,5.0,50, Color.BLACK));
        shapes.stream()
                .filter(shape -> shape.isInside(5, 5))
                .findFirst().ifPresent(shape -> {
                    assertTrue(shape.getSize() == 50);
                });
    }

    @Test
    void setSize() {
        shapes.add(Shapes.circleOf(5.0,5.0,50, Color.BLACK));
        shapes.stream()
                .filter(shape -> shape.isInside(5, 5))
                .findFirst().ifPresent(shape -> {
                    shape.setSize(100);
                    assertTrue(shape.getSize() == 100);
                });
    }
}