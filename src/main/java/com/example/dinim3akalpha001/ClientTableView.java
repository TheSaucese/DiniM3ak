package com.example.dinim3akalpha001;

import com.mongodb.client.model.Updates;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.bson.types.ObjectId;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.*;
import static com.mongodb.client.model.Filters.eq;

public class ClientTableView extends Pane {
    public static StringProperty ReturnDesc = new SimpleStringProperty();

    public static StringProperty Child = new SimpleStringProperty();

    public ClientTableView(String locationText, String ClientText, String DestinationText, boolean seconditem, String notes, String price, String timetext, ObjectId id, VBox vbox, String userid) {

        setPrefHeight(87);
        setPrefWidth(276);
        setStyle("-fx-background-color: #3F3B6C;");

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
        dollar.setImage(new Image(ClientTableView.class.getResourceAsStream("/com/Images/dinim3akalpha001/dollar.png")));

        ImageView time = new ImageView();
        time.setFitHeight(12.7);
        time.setFitWidth(12.7);
        time.setLayoutX(1);
        time.setLayoutY(43);
        time.setPickOnBounds(true);
        time.setPreserveRatio(true);
        time.setImage(new Image(ClientTableView.class.getResourceAsStream("/com/Images/dinim3akalpha001/time.png")));

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

        Text timeValue = new Text(timetext);
        timeValue.setId("time");
        timeValue.setFill(Color.WHITE);
        timeValue.setLayoutX(25);
        timeValue.setLayoutY(54);
        timeValue.setStrokeType(StrokeType.OUTSIDE);
        timeValue.setStrokeWidth(0);
        timeValue.setText(timetext);

        Text moneyValue = new Text("300 Dh");
        moneyValue.setId("money");
        moneyValue.setFill(Color.WHITE);
        moneyValue.setLayoutX(25);
        moneyValue.setLayoutY(71);
        moneyValue.setStrokeType(StrokeType.OUTSIDE);
        moneyValue.setStrokeWidth(0);
        moneyValue.setText(price);

        Button notifyButton = new Button();
        notifyButton.setId("notify");
        notifyButton.setLayoutX(240);
        notifyButton.setLayoutY(18);
        notifyButton.setMnemonicParsing(false);
        notifyButton.setStyle("-fx-background-color: transparent;");
        notifyButton.setText("Notify");
        notifyButton.setTextFill(Color.web("#3f3b6c"));
        notifyButton.setVisible(false);

        Button callButton = new Button();
        callButton.setId("call");
        callButton.setLayoutX(240);
        callButton.setLayoutY(40);
        callButton.setMnemonicParsing(false);
        callButton.setPrefHeight(25);
        callButton.setPrefWidth(49);
        callButton.setStyle("-fx-background-color: transparent;");
        callButton.setText("Call");
        callButton.setTextAlignment(TextAlignment.CENTER);
        callButton.setTextFill(Color.web("#3F3B6C"));
        callButton.setVisible(false);

        Circle circle = new Circle();
        circle.setLayoutX(265);
        circle.setLayoutY(40);
        circle.setRadius(25);
        circle.setFill(Color.WHITE);
        circle.setVisible(false);

        Rectangle line = new Rectangle();
        line.setLayoutX(242);
        line.setLayoutY(39);
        line.setFill(Color.web("#3f3b6c"));
        line.setHeight(2);
        line.setWidth(46);
        line.setVisible(false);

        Button note = new Button();
        note.setId("note");
        note.setLayoutX(190.0);
        note.setLayoutY(20);
        note.setMnemonicParsing(false);
        note.setPrefHeight(38);
        note.setPrefWidth(38);
        Image img1 = new Image("/com/Images/dinim3akalpha001/NotesIncluded.png");
        ImageView view1 = new ImageView(img1);
        note.setGraphic(view1);
        note.setVisible(notes.length()==0?false:true);

        note.setOnAction(event -> {
            ReturnDesc.set(notes);
        });

        btn.setOnAction(event -> {
            // Set the visibility of the buttons to true
            notifyButton.setVisible(true);
            callButton.setVisible(true);
            circle.setVisible(true);
            line.setVisible(true);
            btn.setVisible(false);
        });

        notifyButton.setOnAction(e -> {
            db.getCollection("users").updateOne(eq("_id", userid), Updates.push("notification",getuID()));
            db.getCollection(getuJob()=="Driver"?"requests":"rides").deleteOne(eq("_id",id));
            vbox.getChildren().remove(this);
        });

        vbox.setOnMouseClicked(event -> {
            notifyButton.setVisible(false);
            callButton.setVisible(false);
            circle.setVisible(false);
            btn.setVisible(true);
            line.setVisible(false);
        });

        setOnMouseClicked(event -> {
            notifyButton.setVisible(false);
            callButton.setVisible(false);
            circle.setVisible(false);
            btn.setVisible(true);
            line.setVisible(false);
        });

        getChildren().addAll(location,client,dest,dollar, time,note, timeValue, moneyValue,btn,circle,line,callButton,notifyButton);
        if(!seconditem){
            getChildren().add(rect);
        }

        getStylesheets().add(DiniApplication.class.getResource("style.css").toExternalForm());
    }
}
