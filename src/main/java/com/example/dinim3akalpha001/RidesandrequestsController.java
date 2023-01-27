package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

import static com.example.dinim3akalpha001.DiniController.showTooltip;
import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.*;

public class RidesandrequestsController {
        // Add ride
        @FXML
        private TextField from_input;
        @FXML
        private TextField to_input;
        @FXML
        private TextField amount_input;
        @FXML
        private TextField at_input;
        @FXML
        private TextField description_input,from1_input,to1_input;
        @FXML
        private Button Apply;
        @FXML
        private void HandleHomeDriver() throws IOException {
                new DiniController().handleScenes(getuJob()=="Driver"?"HomeDriver.fxml":"HomeRider.fxml",at_input);
        }

        @FXML
        private void handleVehicle() throws IOException {
                at_input.setTooltip(new Tooltip("Switch to Driver to access this feature."));
                showTooltip((Stage) at_input.getScene().getWindow(),at_input,"Switch to Driver to access this feature.",null);
        }
        @FXML
        private void handlePayment() throws IOException {
                new DiniController().handleScenes("PaymentSee.fxml",at_input);
        }
        @FXML
        private void handleProfile() throws IOException {
                new DiniController().handleScenes("ProfileDriver.fxml",at_input);
        }
        @FXML
        private void handleNoti() throws IOException {
                new DiniController().handleScenes("Noti.fxml",at_input);
        }
        @FXML
        private void addRide(ActionEvent event){
                MongoCollection<Document> collection = db.getCollection(getuJob()=="Driver"?"rides":"requests");
                Document data = new Document("source", from_input.getText())
                        .append("destination", to_input.getText())
                        .append("price", amount_input.getText())
                        .append("time", at_input.getText())
                        .append("description", description_input.getText())
                        .append("user",getuName())
                        .append("userid",getuID());

                InsertOneResult result = collection.insertOne(data);

                // Check if the data is submitted successfully
                if (result.getInsertedId() != null) {
                        System.out.println("Data submitted successfully!");
                } else {
                        System.out.println("Data submission failed.");
                }
                from_input.setText("");
                to_input.setText("");
                amount_input.setText("");
                at_input.setText("");
                description_input.setText("");
                from1_input.setText("");
                to1_input.setText("");

        }
}
