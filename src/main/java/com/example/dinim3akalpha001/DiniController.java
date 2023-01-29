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
/**
 * The DiniController class is responsible for handling the different scenes in the application.
 *  It provides a handleScenes method for loading and switching between different FXML files,
 *  and a showTooltip method for displaying tooltips on controls.
 */
public class DiniController {
    /**
     * This method handle the different scenes of the application
     * @param fxml the fxml file name
     * @param node the node that will be used to get the window stage
     * @throws IOException if an I/O error occurs
     */
    protected void handleScenes(String fxml, Node node) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DiniApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 375, 812);
        scene.getStylesheets().add(DiniApplication.class.getResource("style.css").toExternalForm());
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
    }
    /**
     * This method show the tooltip of the control
     * @param owner the stage that owns the tooltip
     * @param control the control that the tooltip will be shown on
     * @param tooltipText the text of the tooltip
     * @param tooltipGraphic the graphic of the tooltip
     */
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
