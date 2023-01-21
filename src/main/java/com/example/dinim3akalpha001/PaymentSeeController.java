package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.dinim3akalpha001.SignupController2.getuJob;

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
    private Button Delete;
    private Pane n;
    private Rectangle oldrectangle;
    private Rectangle newrectangle;
    @FXML
    protected void ScrollAnimate() {
        switch (AnchorPane.getChildren().size()) {
            case 2:
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
    private void DeleteOption(MouseEvent e) {
        Delete.setDisable(!Delete.isDisabled());
        n= (Pane) e.getSource();
        newrectangle = (Rectangle) n.getChildren().get(0);
        newrectangle.setStrokeWidth(4);
        if(oldrectangle!=null) {
            oldrectangle.setStrokeWidth(newrectangle.getStrokeWidth()==4?0:4);
        }
        oldrectangle=newrectangle;
    }
    @FXML
    private void handleDelete() {
        n.setVisible(false);
        Delete.setDisable(true);
    }
    @FXML
    private void testAdd() {
        Pane pane = new Pane();
        pane.setLayoutX(32);
        pane.setPrefHeight(158.16);
        pane.setPrefWidth(272.54);

        Rectangle rectangle = new Rectangle(272.54, 158.16);
        rectangle.setArcHeight(15);
        rectangle.setArcWidth(15);
        rectangle.setStrokeType(StrokeType.INSIDE);

        LinearGradient fillGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#624f82FF")), new Stop(1, Color.web("#624f82FF")), new Stop(0.5, Color.web("#ff8ca4FF")));
        rectangle.setFill(fillGradient);

        LinearGradient strokeGradient = new LinearGradient(0.27014218009478674, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#624f82")), new Stop(0.006711409395973154, Color.web("#624f82")), new Stop(0.5067114093959731, Color.web("#ff8ca4")), new Stop(1, Color.web("#ff8ca4")));
        rectangle.setStroke(strokeGradient);

        Text mainText1 = new Text("Nathan Drake");
        mainText1.setFill(Color.web("#ffffff99"));
        mainText1.setLayoutX(22);
        mainText1.setLayoutY(109);
        mainText1.setStrokeType(StrokeType.OUTSIDE);
        mainText1.setFont(new Font(14));

        Text mainText2 = new Text("Bank");
        mainText2.setFill(Color.web("#ffffff99"));
        mainText2.setLayoutX(-41);
        mainText2.setLayoutY(30);
        mainText2.setStrokeType(StrokeType.OUTSIDE);
        mainText2.setX(55);
        mainText2.setFont(new Font(16));

        Text mainText3 = new Text("XXXX - XXXX - XXXX - 5334");
        mainText3.setFill(Color.web("#ffffff99"));
        mainText3.setLayoutX(22);
        mainText3.setLayoutY(128);
        mainText3.setStrokeType(StrokeType.OUTSIDE);
        mainText3.setFont(new Font(14));

        Text cpny1 = new Text("CPNY");
        cpny1.setFill(Color.web("#ffffff99"));
        cpny1.setLayoutX(198);
        cpny1.setLayoutY(35);
        cpny1.setStrokeType(StrokeType.OUTSIDE);
        cpny1.setFont(new Font(26));

        pane.getChildren().addAll(rectangle, mainText1, mainText2, mainText3, cpny1);
        AnchorPane.getChildren().add(pane);

    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        circle1.setOpacity(AnchorPane.getChildren().size()>=1?1:0.3);
        circle2.setOpacity(AnchorPane.getChildren().size()>=2?1:0.3);
        circle3.setOpacity(AnchorPane.getChildren().size()>=3?1:0.3);
        circle4.setOpacity(AnchorPane.getChildren().size()>=4?1:0.3);
    }

    @FXML
    private void handleArrow() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"ProfileDriver.fxml":"ProfileRider.fxml",circle1);
    }
    @FXML
    private void handleVehicle() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"Car.fxml":"Placeholder.fxl",circle1);
    }
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"HomeDriver.fxml":"HomeRider.fxml",circle1);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentAdd.fxml",circle1);
    }
}
