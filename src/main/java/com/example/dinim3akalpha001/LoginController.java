package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bson.Document;

import java.io.IOException;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.*;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class LoginController {
    @FXML
    private TextField Email;
    @FXML
    private PasswordField pass_hidden;

    @FXML
    private void Login(ActionEvent event) throws IOException {
        String email = Email.getText();
        System.out.println(email);
        String password = pass_hidden.getText();
        MongoCollection<Document> collection = db.getCollection("users");
        Document user = collection.find(and(eq("email",email),eq("password",password))).first();
        if (user.getString("job").equals("Driver")) {
            new DiniController().handleScenes("HomeDriver.fxml",Email);
        }
        else {
            new DiniController().handleScenes("HomeRider.fxml",Email);
        }
        setuJob(user.getString("job"));
        setuEmail(email);
        setuPass(password);
    }
    @FXML
    private void Signup(ActionEvent event) throws IOException {
        new DiniController().handleScenes("Signup.fxml",Email);
    }

    @FXML
    private void Forgot(ActionEvent event) throws IOException {
        new DiniController().handleScenes("Signup.fxml",Email);
    }

}









