package com.example.dinim3akalpha001;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;

public class DiniApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String uri = "mongodb+srv://TheSaucese:i9V9H9FCGnfNfnF@cluster0.bsuoxuu.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            try {
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Connected successfully to server.");
            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(DiniApplication.class.getResource("Welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 375, 812);
        scene.getStylesheets().add(DiniApplication.class.getResource("style.css").toExternalForm());
        stage.setTitle("DINIM3AK3000");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setX(360);
        stage.setY(-120);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}