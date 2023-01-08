package com.example.dinim3akalpha001;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bson.Document;

public class LoginController {
    @FXML
    private TextField Email;
    @FXML
    private PasswordField pass_hidden;

    static MongoDatabase db;

    @FXML
    private void Login(ActionEvent event){
        String email = Email.getText();
        String password = pass_hidden.getText();

        ConnectionString connectionString = new ConnectionString("mongodb+srv://TheSaucese:i9V9H9FCGnfNfnF@cluster0.bsuoxuu.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        db = mongoClient.getDatabase("DiniM3ak");
        //MongoCollection collection = db.getCollection("users");



        MongoCollection<Document> collection = db.getCollection("users");

        // Find a user with a matching username and password
        Document query = new Document("email", email).append("password", password);
        Document user = collection.find(query).first();

        if (user != null) {
            //Login successful
           System.out.println("Done");
        } else {
            //Login unsuccessful
            System.out.println("Not Done");
        }


        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Login Successfully");
        a.setContentText("Login Successfully");
        a.showAndWait();
    }

    }










