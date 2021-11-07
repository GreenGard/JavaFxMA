package se.iths.se.javafx.labb3maxblom;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import se.iths.java21.Command;
import se.iths.java21.UndoManager;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

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
        GraphicsContext gc = canvas.getGraphicsContext2D();
    }

    public void canvasClicked(MouseEvent event) {
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
            } else if (event.getButton().name().equals("SECONDARY")) {
                model.shapes.stream()
                        .filter(shape -> shape.isInside(event.getX(), event.getY()))
                        .findFirst().ifPresent(shape -> shape.setColor(Color.RED));
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

        if (model.shapes.size() > 0) {
            model.shapes.remove(model.shapes.size() - 1);
            draw();
        }
    }

    public void onSave(ActionEvent event) {

        FileChooser savefile = new FileChooser();
        savefile.setTitle("Save File");

        File file = savefile.showSaveDialog(HelloApplication.stage);
        System.out.println("is file null ? " + file);
        if (file != null) {
            try {
                WritableImage writableImage = new WritableImage(320, 240);
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error!");
            }
        }
    }


    public void onExit(ActionEvent actionEvent) {
    }


    public void GatherShapes() {
        Path path = Path.of("savedCanvas.txt");
        try {
            Stream<String> lines = Files.lines(path);
            {
                lines.forEach((shape -> {
                    var shapes = shape.split(" ");
                    // model.shapes.add(Integer.parseInt(shapes[0]), Integer.parseInt(shapes[1]), shapes[2], shapes[3]);
                    //model.shapes.add(Shapes.circleOf(event.getX(), event.getY(), 10.0, colorPicker.getValue()));
                }));
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public void CheckShapeFile(ActionEvent actionEvent) {
        try {
            File file = new File("savedCanvas.txt");

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                System.out.println(file.getAbsolutePath());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public void writeShapeesToFile(ActionEvent actionEvent) {
        File file = new File("savedCanvas.txt");
        if (file.delete()) {
            CheckShapeFile(actionEvent);
            try {
                FileWriter fileWriter = new FileWriter("savedCanvas.txt");
                for (var shape : model.shapes)
                    fileWriter.write(shape.getX() + " " + shape.getY() + " " + shape.getColor() + " " + shape.getClass() + "\n");
                fileWriter.close();
                System.out.println("successfully wrote to the file");
            } catch (IOException e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }

        }
    }
}