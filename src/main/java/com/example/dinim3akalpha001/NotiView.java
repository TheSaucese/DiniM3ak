package com.example.dinim3akalpha001;

import com.mongodb.client.gridfs.model.GridFSDownloadOptions;
import com.mongodb.client.model.Updates;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import org.bson.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.ProfileController.gridBucket;
import static com.example.dinim3akalpha001.SignupController2.getuID;
import static com.mongodb.client.model.Filters.eq;
/**
 * Class that creates a notification view for a ride request.
 * It contains the username of the client who sent the request,
 * an accept button, and a decline button.
 */
public class NotiView extends Pane {
    NotiView(String username, String id, VBox vboxnoti) {
        setId("Pane");
        setPrefHeight(87);
        setPrefWidth(276);
        setStyle("-fx-background-color: #3F3B6C;");

        Text Client = new Text();
        Client.setId("ClientNoti");
        Client.setFill(Color.WHITE);
        Client.setLayoutX(70);
        Client.setLayoutY(34);
        Client.setStrokeType(StrokeType.OUTSIDE);
        Client.setStrokeWidth(0);
        Client.setStyle("-fx-font-size: 20;");
        Client.setText(username);

        Text NotiText = new Text();
        NotiText.setId("NotiText");
        NotiText.setFill(Color.WHITE);
        NotiText.setLayoutX(70);
        NotiText.setLayoutY(55);
        NotiText.setStrokeType(StrokeType.OUTSIDE);
        NotiText.setStrokeWidth(0);
        NotiText.setWrappingWidth(132.26953125);
        NotiText.setText("Has sent you a ride request");

        Rectangle rectangle = new Rectangle();
        rectangle.setArcHeight(5);
        rectangle.setArcWidth(5);
        rectangle.setFill(Color.DODGERBLUE);
        rectangle.setHeight(1);
        rectangle.setLayoutY(82);
        rectangle.setStroke(Color.web("#4b4b4b"));
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setWidth(269);

        Button acceptButton = new Button();
        acceptButton.setId("acceptreq");
        acceptButton.setLayoutX(204);
        acceptButton.setLayoutY(14);
        acceptButton.setMnemonicParsing(false);
        acceptButton.setPrefHeight(25);
        acceptButton.setPrefWidth(61);
        acceptButton.setStyle("-fx-background-color: White; -fx-background-radius: 6px; -fx-border-width: 2px;");
        acceptButton.setText("Accept");
        acceptButton.setTextFill(Color.web("#3f3b6c"));

        acceptButton.setOnAction(e -> {
            db.getCollection("users").updateOne(eq("_id", getuID()), Updates.pull("notification",id));
            db.getCollection("users").updateOne(eq("_id", getuID()), Updates.push("history",id));
            vboxnoti.getChildren().remove(this);
        } );

        Button declineButton = new Button();
        declineButton.setId("declinereq");
        declineButton.setLayoutX(205.0);
        declineButton.setLayoutY(46);
        declineButton.setMnemonicParsing(false);
        declineButton.setPrefHeight(25);
        declineButton.setPrefWidth(61);
        declineButton.setStyle("-fx-border-radius: 6px; -fx-border-color: White; -fx-border-width: 2px; -fx-background-color: transparent;");
        declineButton.setText("Decline");
        declineButton.setTextFill(Color.WHITE);

        declineButton.setOnAction(e -> {
            db.getCollection("users").updateOne(eq("_id", getuID()), Updates.pull("notification",id));
            vboxnoti.getChildren().remove(this);
        } );

        ImageView profileImg = new ImageView();
        profileImg.setFitHeight(62);
        profileImg.setFitWidth(63);
        profileImg.setLayoutX(1);
        profileImg.setLayoutY(14);
        profileImg.setPickOnBounds(true);
        profileImg.setPreserveRatio(true);
        Document user = db.getCollection("users").find(eq("_id",id)).first();
        if (db.getCollection("fs.files").find(eq("_id",user.getObjectId("image"))).first()!=null){
            try {
                GridFSDownloadOptions downloadOptions = new GridFSDownloadOptions().revision(0);
                File file = new File("c:/DiniM3ak/"+db.getCollection("fs.files").find(eq("_id",user.getObjectId("image"))).first().getString("filename"));
                file.getParentFile().mkdirs(); // Will create parent directories if not exists
                file.createNewFile();
                try (FileOutputStream streamToDownloadTo = new FileOutputStream(file)) {
                    gridBucket.downloadToStream(db.getCollection("fs.files").find(eq("_id",user.getObjectId("image"))).first().getString("filename"), streamToDownloadTo, downloadOptions);
                    streamToDownloadTo.flush();
                }
                Image image = new Image(file.toURI().toString());
                profileImg.setImage(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        getChildren().addAll(Client,NotiText,rectangle,acceptButton,declineButton,profileImg);
    }

}
