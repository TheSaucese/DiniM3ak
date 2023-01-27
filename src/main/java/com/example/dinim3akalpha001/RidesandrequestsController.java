package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bson.Document;

import java.io.IOException;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.getuJob;

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
        private TextField description_input;
        @FXML
        private Button Apply;
        @FXML
        private void HandleHomeDriver() throws IOException {
                new DiniController().handleScenes("HomeDriver.fxml",at_input);
        }

        @FXML
        private void handleVehicle() throws IOException {
                new DiniController().handleScenes("Car.fxml",at_input);
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
                System.out.println(from_input.getText());
                System.out.println(to_input.getText());
                System.out.println(amount_input.getText());
                System.out.println(at_input.getText());
                System.out.println(description_input.getText());



                MongoCollection<Document> collection = db.getCollection(getuJob()=="Driver"?"rides":"requests");
                Document data = new Document("source", from_input.getText())
                        .append("destination", to_input.getText())
                        .append("price", amount_input.getText())
                        .append("time", at_input.getText())
                        .append("description", description_input.getText());

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
        }
}
