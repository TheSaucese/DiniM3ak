package com.example.dinim3akalpha001;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class DiniController {
    protected void handleScenes(String fxml, Node node) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DiniApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 375, 812);
        scene.getStylesheets().add(DiniApplication.class.getResource("style.css").toExternalForm());
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
    }
    public static void showTooltip(Stage owner, Control control, String tooltipText,
                                   ImageView tooltipGraphic)
    {
        Point2D p = control.localToScene(0.0, 0.0);

        final Tooltip customTooltip = new Tooltip();
        customTooltip.setText(tooltipText);

        control.setTooltip(customTooltip);
        customTooltip.setAutoHide(true);

        customTooltip.show(owner, p.getX()
                + control.getScene().getX() + control.getScene().getWindow().getX()-35, p.getY()
                + control.getScene().getY() + control.getScene().getWindow().getY()-30);

    }
}
