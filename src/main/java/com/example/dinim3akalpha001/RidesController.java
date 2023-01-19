package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

import static com.example.dinim3akalpha001.MongoController.db;

public class RidesController {
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
        private void addRide(ActionEvent event){
                System.out.println(from_input.getText());
                System.out.println(to_input.getText());
                System.out.println(amount_input.getText());
                System.out.println(at_input.getText());
                System.out.println(description_input.getText());



                MongoCollection<Document> collection = db.getCollection("rides");
                Document data = new Document("source", from_input.getText())
                        .append("destination", to_input.getText())
                        .append("ride price", amount_input.getText())
                        .append("ride time", at_input.getText())
                        .append("ride description", description_input.getText());

                InsertOneResult result = collection.insertOne(data);

                // Check if the data is submitted successfully
                if (result.getInsertedId() != null) {
                        System.out.println("Data submitted successfully!");
                } else {
                        System.out.println("Data submission failed.");
                }
        }
}
