package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.dinim3akalpha001.LoginController.isDriver;

public class PaymentSeeController implements Initializable {
    @FXML
    private javafx.scene.control.ScrollPane ScrollPane;
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;
    @FXML
    private Circle circle4;
    @FXML
    protected void ScrollAnimate() {
        switch (AnchorPane.getChildren().size()) {
            case 10:
                if(ScrollPane.getHvalue()>=0.8) {
                    circle1.setFill(Color.WHITE);
                    circle2.setFill(Color.web("#9F73AB"));
                }
                else if (ScrollPane.getHvalue()<=0.2) {
                    circle2.setFill(Color.WHITE);
                    circle1.setFill(Color.web("#9F73AB"));
                }
        }
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        circle1.setOpacity(AnchorPane.getChildren().size()>=5?1:0.3);
        circle2.setOpacity(AnchorPane.getChildren().size()>=10?1:0.3);
        circle3.setOpacity(AnchorPane.getChildren().size()>=15?1:0.3);
        circle4.setOpacity(AnchorPane.getChildren().size()>=20?1:0.3);
    }

    @FXML
    private void handleArrow() throws IOException {
        new DiniController().handleScenes(isDriver?"ProfileDriver.fxml":"ProfileRider.fxml",circle1);
    }
    @FXML
    private void handleVehicle() throws IOException {
        new DiniController().handleScenes(isDriver?"Car.fxml":"Placeholder.fxl",circle1);
    }
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(isDriver?"HomeDriver.fxml":"HomeRider.fxml",circle1);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentAdd.fxml",circle1);
    }
}
