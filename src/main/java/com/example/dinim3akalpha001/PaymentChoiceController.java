package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentChoiceController implements Initializable {
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
                    circle2.setFill(Color.web("#3f3b6c"));
                }
                else if (ScrollPane.getHvalue()<=0.2) {
                    circle2.setFill(Color.WHITE);
                    circle1.setFill(Color.web("#3f3b6c"));
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
}
