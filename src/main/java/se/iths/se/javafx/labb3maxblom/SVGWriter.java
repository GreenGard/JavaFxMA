package se.iths.se.javafx.labb3maxblom;

import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static se.iths.se.javafx.labb3maxblom.CheckedSupplier.wrap;



public class SVGWriter {
    private static final FileChooser fileChooser = new FileChooser();

    public static void saveSVGFile(Model model) {
        setUpFileChooser();

        Path path = getPath();
        if (path == null)
            return;

        List<String> svgString = new ArrayList<>();
        buildSVGString(model, svgString);

        try {
            Files.write(path, svgString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setUpFileChooser() {
        fileChooser.setTitle("Save SVG File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("SVG Files","*.svg"));
    }

    private static Path getPath() {
        Path path;

        try {
            path = Path.of(wrap(() -> fileChooser.showSaveDialog(new Stage()).toURI()));
        } catch (RuntimeException e) {
            return null;
        }
        return path;
        }

    private static void buildSVGString(Model model, List<String> svgString) {
        svgString.add(startOfSVGString());
        model.getShapes().forEach(shape -> shapeSVGInfoToString(shape, svgString));
        svgString.add(endOfSVGString());
    }

    private static String startOfSVGString() {
        return String.join(" ",
                "<svg",
                "xmlns=\"http://www.w3.org/2000/svg\"",
                "version=\"1.1\"",
                "width=\"800.0\"",
                "height=\"800.0\">");
    }

    private static void shapeSVGInfoToString(Shape shape, List<String> strings) {
        strings.add(shape.drawSVG());
    }

    private static String endOfSVGString() {
        return "</svg>";
    }
}
