package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.bson.Document;

import static com.example.dinim3akalpha001.MongoController.db;

/**
 * The RidesController2 class is responsible for handling the request ride feature in the application.
 * It allows the user to input information such as the source, destination, desired vehicle type,
 * ride time, and a description for the ride. The user can then submit the ride request, which is stored in
 * the "requestedRides" collection in the MongoDB database.
 */

public class RidesController2 {
        /**
         * A list of vehicle types that are available for the user to select from.
         */
        ObservableList<String> vehiculeList = FXCollections
                .observableArrayList("Van","Universal","Suv","Pickup","Campervan");
        @FXML
        private TextField from_input_passenger;
        @FXML
        private TextField to_input_passenger;
        @FXML
        private ComboBox vehicule_input;
        @FXML
        private TextField at_input_passenger;
        @FXML
        private TextField description_input_passenger;
        @FXML
        private Button Apply_passenger;

        /**
         * Initializes the combo box with the list of vehicle types.
         */
        @FXML
        private void initialize(){
                vehicule_input.setValue("Van");
                vehicule_input.setItems(vehiculeList);
        }
        /**
         * Handles the event of the user clicking the submit button. The inputted information
         * is stored in the "requestedRides" collection in the MongoDB database.
         * @param event The ActionEvent object for the submit button being clicked.
         */
        @FXML
        private void requestRide(ActionEvent event) {
                /*try {
                        MongoCollection<Document> collection = db.getCollection("requestedRides");
                } catch (Exception e) {
                        e.printStackTrace();
                }*/

                MongoCollection<Document> collection = db.getCollection("requestedRides");
                Document data = new Document("source", from_input_passenger.getText())
                        .append("destination", to_input_passenger.getText())
                        .append("vehicule type", vehicule_input.getValue())
                        .append("ride time", at_input_passenger.getText())
                        .append("ride description", description_input_passenger.getText());

                InsertOneResult result = collection.insertOne(data);

                // Check if the data is submitted successfully
                if (result.getInsertedId() != null) {
                        System.out.println("Data submitted successfully!");
                } else {
                        System.out.println("Data submission failed.");
                }
        }
}
