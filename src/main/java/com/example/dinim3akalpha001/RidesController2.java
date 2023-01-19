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

public class RidesController2 {
        // Request Ride
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

        // add list of vehicle type in the combobox
        @FXML
        private void initialize(){
                vehicule_input.setValue("Van");
                vehicule_input.setItems(vehiculeList);
        }
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
