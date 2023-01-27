package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.dinim3akalpha001.SignupController2.getuJob;

public class NotiDriverController implements Initializable {
    @FXML
    private Button Vehicle;
    @FXML
    private ImageView imgrider;
    @FXML
    private VBox vboxnoti;
    @FXML
    private void handleVehicle() throws IOException {
        new DiniController().handleScenes("Car.fxml",Vehicle);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentSee.fxml",Vehicle);
    }
    @FXML
    private void handleProfile() throws IOException {
        new DiniController().handleScenes("ProfileDriver.fxml",Vehicle);
    }
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"HomeDriver.fxml":"HomeRider.fxml",Vehicle);
    }
    @FXML
    private void handleNoti() throws IOException {
        new DiniController().handleScenes("Noti.fxml",Vehicle);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //imgrider.setImage(new Image("@../../Images/dinim3akalpha001/Check.png"));
        vboxnoti.getChildren().add(new NotiView("boi"));
    }
}
