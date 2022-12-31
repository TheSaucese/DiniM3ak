package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class CarController {
    @FXML
    private Button Car;
    @FXML
    private Button Arrow;

    @FXML
    protected void Upload(){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            ImageView img = new ImageView(image);
            img.fitWidthProperty().bind(Car.widthProperty());
            img.fitHeightProperty().bind(Car.heightProperty());
            Car.setGraphic(img);
        }
    }
    @FXML
    private void handleArrow() throws IOException {
        new DiniController().handleScenes("ProfileDriver.fxml",Arrow);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("Payment.fxml",Arrow);
    }
}
