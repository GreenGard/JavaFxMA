package se.iths.se.javafx.labb3maxblom;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import se.iths.java21.UndoManager;
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
    UndoManager undoManager;
    List<Shape> Undo = new ArrayList<>();

    public void initialize() {
        model = new Model();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //undoManager = UndoManager.getInstance();
    }

    public void canvasClicked(MouseEvent event) {
        if (checkBox1.isSelected()) {
            if (event.getButton().name().equals("PRIMARY")) {
                model.shapes.stream()
                        .filter(shape -> shape.isInside(event.getX(), event.getY()))
                        .findFirst().ifPresent(shape -> {
                            Undo.add(shape);
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
                        Undo.add(model.shapes.get(model.shapes.size()-1));
                        //undoManager.addToUndoStack(model.shapes);
                        break;
                    case "rectangle":
                        model.shapes.add(Shapes.rectangleOf(event.getX(), event.getY(), Integer.parseInt(shapeSize.getText()), colorPicker.getValue()));
                        Undo.add(model.shapes.get(model.shapes.size()-1));
                        break;
                }
            }
            draw();
        }
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
//        model.shapes.set(model.shapes.size() - 1 ,Undo.get(Undo.size() - 1));
//        Undo.remove(Undo.size() -1);
//        draw();

        if (model.shapes.size() > 0) {
            model.shapes.remove(model.shapes.size() - 1);
            draw();
        }
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

    }
}