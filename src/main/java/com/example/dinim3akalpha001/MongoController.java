package com.example.dinim3akalpha001;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

/**
 * The MongoController class is used to establish a connection to a MongoDB database.
 * It uses the MongoClient class to create a MongoClient object which is used to connect to the MongoDB server.
 * A MongoDatabase object is created to represent the DiniM3ak database.
 * @author Youssef & sami
 */

public class MongoController {
    static MongoDatabase db;
    /**
     * Constructor for MongoController class.
     * It uses ConnectionString class to create a connection string key unique to our project that is used to connect to the MongoDB server.
     * A MongoClientSettings object is created and its serverApi is set to V1.
     * The MongoClient object is created by passing the MongoClientSettings object to the MongoClients.create() method.
     * A MongoDatabase object is created by passing the name of the database to the MongoClient.getDatabase() method.
     */
    MongoController() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://TheSaucese:i9V9H9FCGnfNfnF@cluster0.bsuoxuu.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        db = mongoClient.getDatabase("DiniM3ak");
    }

}
