package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bson.Document;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.mongodb.client.model.Filters.eq;

public class LoginController {
    @FXML
    private TextField Email;
    @FXML
    private PasswordField pass_hidden;

    @FXML
    private void Login(ActionEvent event){
        String email = Email.getText();
        System.out.println(email);
        String password = pass_hidden.getText();
        Document query = new Document("email", email).append("password", password);
        MongoCollection<Document> collection = db.getCollection("users");
        Document user = collection.find(eq("email", email)).first();
        System.out.println(user);

        if (user != null) {
            System.out.println("Done");
        } else {
            System.out.println("Not Done");
        }


        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Login Successfully");
        a.setContentText("Login Successfully");
        a.showAndWait();
    }

}









