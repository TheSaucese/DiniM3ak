package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static com.example.dinim3akalpha001.LoginController.isDriver;

public class PaymentController {
    @FXML
    private Button Arrow;
    @FXML
    private Button Vehicle;
    @FXML
    private void handleArrow() throws IOException {
        new DiniController().handleScenes("ProfileDriver.fxml",Arrow);
    }
    @FXML
    private void handleVehicle() throws IOException {
        new DiniController().handleScenes("Car.fxml",Vehicle);
    }
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(isDriver?"HomeDriver.fxml":"HomeRider.fxml",Vehicle);
    }
}
