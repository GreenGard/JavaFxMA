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

public class HelloController {
    Model model;
    @FXML
    public Canvas canvas;
    @FXML
    private TextField brushSize;
    @FXML
    private CheckBox eraser;
    @FXML
    public Button buttonCircle;
    @FXML
    public Button buttonRectangle;
    @FXML
    public Button buttonLine;
    @FXML
    public Button buttonPoint;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ListView<String> listView1;

    public HelloController() {
    }

    Stage mainStage;

    public HelloController(Model model) {
        this.model = model;
    }

    public void initialize() {
/*
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            if (eraser.isSelected()) {
                gc.clearRect(x, y, size, size);

            } else {
                gc.setFill(colorPicker.getValue());
                gc.fillRect(x, y, size, size);
            }
        });
*/

        model = new Model();
    }


    @FXML
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getX() + ":" + mouseEvent.getY());
        System.out.println(mouseEvent.getSceneX() + ":" + mouseEvent.getSceneY());
        System.out.println(mouseEvent.getScreenX() + ":" + mouseEvent.getScreenY());
    }


   /* @FXML
    public void onRectangleButtonClick(ActionEvent actionEvent) {
        Shapes.rectangleOf(5.0, 6.0, 10.0, Color.MAROON);
*/


    @FXML
    public void onLineButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onLinePointClick(ActionEvent actionEvent) {
        //  Shape();
    }

    @FXML
    private void draw() {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.shapes) {
            shape.draw(gc);
            //gc.setFill(shape.getColor());
            //gc.fillOval(shape.getX(),shape.getY(),25,25);
        }
    }


//    private void keyTyped(KeyEvent keyEvent) {
//        System.out.println(keyT);
//    }


//    public void keyTyped(KeyEvent keyEvent) {
//    }
//
//    public void onCheckBoxChecked(ActionEvent actionEvent) {

//    }


    public void canvasClicked(MouseEvent event) {

        if (event.getButton().name().equals("PRIMARY")) {
            model.shapes.add(Shapes.circleOf(event.getX(), event.getY(), 10.0, model.getColor()));
            model.shapes.add(Shapes.rectangleOf(event.getX(), event.getY(), 10.0, model.getColor()));
        } else if (event.getButton().name().equals("SECONDARY")) {
            model.shapes.stream()
                    .filter(shape -> shape.isInside(event.getX(), event.getY()))
                    .findFirst().ifPresent(shape -> shape.setColor(Color.RED));
        }

        //model.shapes.add(new Shape(model.getColor(), event.getX(), event.getY()));

        draw();
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
        model.setColor(Color.BLACK);

        model.setText("Button pressed");
    }

    public void onCircleButtonClick(ActionEvent actionEvent) {
    }

    public void onExit() {
        Platform.exit();
    }
}