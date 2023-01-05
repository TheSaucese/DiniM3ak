package com.example.dinim3akalpha001;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoController {
    static MongoDatabase db;
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
        //MongoCollection collection = db.getCollection("users");
    }

}
