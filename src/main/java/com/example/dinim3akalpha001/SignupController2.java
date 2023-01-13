package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import org.bson.Document;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.dinim3akalpha001.MongoController.db;


public class SignupController2 {
    @FXML
    private TextField fullName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Button finishRegist;
    @FXML
    private void finishRegistration() throws Exception {

        System.out.println(fullName.getText());
        System.out.println(phoneNumber.getText());

        MongoCollection<Document> collection = db.getCollection("users");

        // Create a new document with the name and phone number

        Document data = new Document("fullName", fullName.getText())
                .append("phoneNumber", phoneNumber.getText());

        InsertOneResult result = collection.insertOne(data);

        if (result.getInsertedId() != null) {
            System.out.println("Data submitted successfully!");
            // new DiniController().handleScenes("ProfileRider.fxml",finishRegister);
        } else {
            System.out.println("Data submission failed.");
        }
    }
    }

