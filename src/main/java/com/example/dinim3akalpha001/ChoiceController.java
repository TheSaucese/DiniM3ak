package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.bson.Document;

import java.io.IOException;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ChoiceController {
    @FXML
    private Button Driving;

    @FXML
    private void handleDriving() throws IOException {
        //Document user = db.getCollection("users").find(eq("email",VerificationController.getuEmail())).first();
        //user.append("job","Driver");
        db.getCollection("users").updateOne(eq("email", VerificationController.getuEmail()), set("job","Driver"));
        new DiniController().handleScenes("HomeDriver.fxml",Driving);
        new LoginController().isDriver=true;
    }
    @FXML
    private void handleRiding() throws IOException {
        Document user = db.getCollection("users").find(eq("email",VerificationController.getuEmail())).first();
        user.append("job","Rider");
        new DiniController().handleScenes("HomeRider.fxml",Driving);
    }
}
