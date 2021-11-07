package se.iths.se.javafx.labb3maxblom;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.List;

public class Model {
    private final StringProperty text;
    private final BooleanProperty inColor;
    private final ObjectProperty<Color> color;
    //private final BooleanProperty size;

    ObservableList<String> observableList =
            FXCollections.observableArrayList();

    public List<Shape> shapes = new ArrayList<>();

    public Model() {
        this.text = new SimpleStringProperty("");
        this.inColor = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        //this.size = new ObjectProperty<Size>();
        }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(Color.valueOf(color));
    }
    public void setSize(double radius ) {
       // this.color.set(Color.valueOf(color));
    }

    public boolean isInColor() {
        return inColor.get();
    }

    public BooleanProperty inColorProperty() {
        return inColor;
    }

    public void setInColor(boolean inColor) {
        this.inColor.set(inColor);
    }

    public String getText() {
        return text.getValue();
    }

    public void setText(String text) {
        this.text.setValue(text);
    }

    public StringProperty textProperty() {
        return text;
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
