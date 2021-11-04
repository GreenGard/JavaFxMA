package se.iths.se.javafx.labb3maxblom;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class HelloController {
    Model model;
    @FXML
    public Canvas canvas;
    @FXML
    private CheckBox eraser;
    @FXML
    public Button buttonCircle;
    @FXML
    public Button buttonRectangle;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ListView<String> listView1;

    String shapeSelected = "circle";

    public HelloController() {
    }

    public HelloController(Model model) {
        this.model = model;
    }

    public void initialize() {
        model = new Model();

    }

    public void canvasClicked(MouseEvent event) {
        if (event.getButton().name().equals("PRIMARY")) {
            switch (shapeSelected){
                case "circle":
                    model.shapes.add(Shapes.circleOf(event.getX(), event.getY(), 10.0, colorPicker.getValue()));
                    break;
                case "rectangle":
                    model.shapes.add(Shapes.rectangleOf(event.getX(), event.getY(), 10.0, colorPicker.getValue()));
                    break;
            }
        } else if (event.getButton().name().equals("SECONDARY")) {
            model.shapes.stream()
                    .filter(shape -> shape.isInside(event.getX(), event.getY()))
                    .findFirst().ifPresent(shape -> shape.setColor(Color.RED));
        }
        draw();
    }

    @FXML
    private void draw() {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (var shape : model.shapes) {
            shape.draw(gc);
        }
    }

   @FXML
    public void onRectangleButtonClick(ActionEvent actionEvent) {
        shapeSelected = "rectangle";
   }

    @FXML
    public void onCircleButtonClick(ActionEvent actionEvent) {
        shapeSelected = "circle";
    }
}