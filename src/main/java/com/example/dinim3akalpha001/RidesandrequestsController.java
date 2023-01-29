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
/**
 * RidesandrequestsController is a class that handles the functionality for adding a ride or request a ride.
 */

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
        /**
         * HandleHomeDriver method is used to switch to the HomeDriver or HomeRider scene based on the user's job.
         * @throws IOException
         */
        @FXML
        private void HandleHomeDriver() throws IOException {
                new DiniController().handleScenes(getuJob()=="Driver"?"HomeDriver.fxml":"HomeRider.fxml",at_input);
        }

        /**
         * handleVehicle method is used to display a tooltip when the user is not a driver.
         * @throws IOException
         */
        @FXML
        private void handleVehicle() throws IOException {
                at_input.setTooltip(new Tooltip("Switch to Driver to access this feature."));
                showTooltip((Stage) at_input.getScene().getWindow(),at_input,"Switch to Driver to access this feature.",null);
        }
        /**
         * handlePayment method is used to switch to the PaymentSee scene.
         * @throws IOException
         */
        @FXML
        private void handlePayment() throws IOException {
                new DiniController().handleScenes("PaymentSee.fxml",at_input);
        }
        /**
         * handleProfile method is used to switch to the ProfileDriver scene.
         * @throws IOException
         */
        @FXML
        private void handleProfile() throws IOException {
                new DiniController().handleScenes("ProfileDriver.fxml",at_input);
        }
        /**
         * handleNoti method is used to switch to the Notification scene.
         * @throws IOException
         */
        @FXML
        private void handleNoti() throws IOException {
                new DiniController().handleScenes("Noti.fxml",at_input);
        }

        /**
         * This method is used to add a ride or a request to the database depending on the user's job.
         * @param event the event that triggers this method, usually a button click.
         */
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
