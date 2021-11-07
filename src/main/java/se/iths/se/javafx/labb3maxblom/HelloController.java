package se.iths.se.javafx.labb3maxblom;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    public Model model;
    @FXML
    public Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField shapeSize;
    @FXML
    public CheckBox checkBox1;

    String shapeSelected = "circle";

    public void initialize() {
        model = new Model();


    }

    public void canvasClicked(MouseEvent event) {
        List<Shape> tempList = getTempList();
        model.undo.addLast(tempList);

        if (checkBox1.isSelected()) {
            if (event.getButton().name().equals("PRIMARY")) {
                model.shapes.stream()
                        .filter(shape -> shape.isInside(event.getX(), event.getY()))
                        .findFirst().ifPresent(shape -> {
                            shape.setColor(colorPicker.getValue());
                            shape.setSize(Integer.parseInt(shapeSize.getText()));
                        });
                draw();
            }
        } else {


            if (event.getButton().name().equals("PRIMARY")) {
                switch (shapeSelected) {
                    case "circle":
                        model.shapes.add(Shapes.circleOf(event.getX(), event.getY(), Integer.parseInt(shapeSize.getText()), colorPicker.getValue()));

                        break;
                    case "rectangle":
                        model.shapes.add(Shapes.rectangleOf(event.getX(), event.getY(), Integer.parseInt(shapeSize.getText()), colorPicker.getValue()));

                        break;
                }
            }
            draw();
        }
    }

    private List<Shape> getTempList() {

        List<Shape> tempList = new ArrayList<>();

        for (Shape shape : model.shapes) {
            tempList.add(shape.copyOf());
        }
        return tempList;
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

    public void onUndoButtonClick() {
        if (model.undo.isEmpty())
            return;

        model.shapes.clear();
        model.shapes.addAll(model.undo.removeLast());

        draw();
    }


    public void onSave(ActionEvent event) {

        SVGWriter.saveSVGFile(model);

        try {
            WritableImage snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("paint.png"));
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }


    }


    public void onExit(ActionEvent actionEvent) {
        System.exit(0);

    }
}