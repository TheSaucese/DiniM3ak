package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClientTableController implements Initializable {
    @FXML
    private AnchorPane Pane;


    public static javafx.scene.layout.Pane createPane(String locationText,String ClientText,String DestinationText,boolean seconditem) {
        javafx.scene.layout.Pane pane = new javafx.scene.layout.Pane();
        pane.setPrefHeight(87);
        pane.setPrefWidth(276);
        pane.setStyle("-fx-background-color: #3F3B6C;");

        Text location = new Text("<Location here>");
        location.setFill(Color.WHITE);
        location.setId("Location");
        location.setLayoutY(22);
        location.setStyle("-fx-font-size: 20;");
        location.setText(locationText);

        Text client = new Text("Client : <client here>");
        client.setFill(Color.WHITE);
        client.setId("clientname");
        client.setLayoutX(71);
        client.setLayoutY(54);
        client.setText("Client : "+ClientText);

        Text dest = new Text("Destination : <dest here>");
        dest.setId("destinationname");
        dest.setFill(Color.WHITE);
        dest.setLayoutX(71);
        dest.setLayoutY(71);
        dest.setText("Destination : "+DestinationText);

        ImageView dollar = new ImageView();
        dollar.setFitHeight(12.7);
        dollar.setFitWidth(12.7);
        dollar.setLayoutX(3);
        dollar.setLayoutY(60);
        dollar.setPickOnBounds(true);
        dollar.setPreserveRatio(true);
        dollar.setImage(new Image(ClientTableController.class.getResourceAsStream("/com/Images/dinim3akalpha001/dollar.png")));

        ImageView time = new ImageView();
        time.setFitHeight(12.7);
        time.setFitWidth(12.7);
        time.setLayoutX(1);
        time.setLayoutY(43);
        time.setPickOnBounds(true);
        time.setPreserveRatio(true);
        time.setImage(new Image(ClientTableController.class.getResourceAsStream("/com/Images/dinim3akalpha001/time.png")));

        Button btn = new Button();
        btn.setLayoutX(230.0);
        btn.setLayoutY(12.0);
        btn.setMaxHeight(71.0);
        btn.setMaxWidth(70.0);
        btn.setMinHeight(71.0);
        btn.setMinWidth(70.0);
        btn.setMnemonicParsing(false);
        btn.setPrefHeight(71.0);
        btn.setPrefWidth(70.0);
        Image img = new Image("/com/Images/dinim3akalpha001/ArrowWhite.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(71);
        view.setPreserveRatio(true);
        btn.setGraphic(view);

        Rectangle rect = new Rectangle();
        rect.setArcHeight(5);
        rect.setArcWidth(5);
        rect.setFill(Color.DODGERBLUE);
        rect.setHeight(1);
        rect.setLayoutY(82);
        rect.setStroke(Color.web("#4b4b4b"));
        rect.setStrokeType(StrokeType.INSIDE);
        rect.setWidth(269);

        Text timeValue = new Text("12:30");
        timeValue.setId("time");
        timeValue.setFill(Color.WHITE);
        timeValue.setLayoutX(25);
        timeValue.setLayoutY(54);
        timeValue.setStrokeType(StrokeType.OUTSIDE);
        timeValue.setStrokeWidth(0);
        timeValue.setText("12:30");

        Text moneyValue = new Text("300 Dh");
        moneyValue.setId("money");
        moneyValue.setFill(Color.WHITE);
        moneyValue.setLayoutX(25);
        moneyValue.setLayoutY(71);
        moneyValue.setStrokeType(StrokeType.OUTSIDE);
        moneyValue.setStrokeWidth(0);
        moneyValue.setText("300 Dh");

        pane.getChildren().addAll(location,client,dest,dollar, time, timeValue, moneyValue,btn);
        if(!seconditem){
            pane.getChildren().add(rect);
        }

        pane.getStylesheets().add(DiniApplication.class.getResource("style.css").toExternalForm());

        return pane;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
