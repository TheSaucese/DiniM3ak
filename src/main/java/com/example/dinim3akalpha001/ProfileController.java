package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ProfileController implements Initializable {
    @FXML
    private Circle Photo;
    @FXML
    private TextField Username;
    @FXML
    private Button Vehicle;
    @FXML
    private void Upload(){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            Photo.setFill(new ImagePattern(image));
        }
    }
    @FXML
    private void handleVehicle() throws IOException {
        new DiniController().handleScenes("Car.fxml",Vehicle);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("Payment.fxml",Vehicle);
    }
    @FXML
    private void handleMore() throws IOException {
        //new DiniController().handleScenes("Payment.fxml",Vehicle);
        //UNFINISHED :(
    }
    @FXML
    private void handleMenu() throws IOException {
        //new DiniController().handleScenes("Payment.fxml",Vehicle);
        //UNFINISHED
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        UnaryOperator<TextFormatter.Change> modifyChange = c -> {
            if (c.isContentChange()) {
                int newLength = c.getControlNewText().length();
                if (newLength > 15) {
                    // replace the input text with the last len chars
                    String tail = c.getControlNewText().substring(newLength - 15, newLength);
                    c.setText(tail);
                    // replace the range to complete text
                    // valid coordinates for range is in terms of old text
                    int oldLength = c.getControlText().length();
                    c.setRange(0, oldLength);
                }
            }
            return c;
        };
        Username.setTextFormatter(new TextFormatter(modifyChange));
    }


}
