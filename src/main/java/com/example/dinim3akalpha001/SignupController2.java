package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.example.dinim3akalpha001.MongoController.db;


public class SignupController2 {
    @FXML
    private TextField fullName;
    @FXML
    private TextField phoneNumber;
    static private String uEmail;
    static private String uPass;
    static private String uJob;
    String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";

    public static void setuEmail(String uEmail) {
        SignupController2.uEmail = uEmail;
    }

    public static void setuPass(String uPass) {
        SignupController2.uPass = uPass;
    }

    public static void setuJob(String uJob) {
        SignupController2.uJob = uJob;
    }

    public static String getuEmail() {
        return uEmail;
    }

    public static String getuPass() {
        return uPass;
    }

    public static String getuJob() {
        return uJob;
    }

    @FXML
    private void finishRegistration() throws Exception {

        System.out.println(fullName.getText());
        System.out.println(phoneNumber.getText());

        MongoCollection<Document> collection = db.getCollection("users");

        // Create a new document with the name and phone number

        /*Document data = new Document("fullName", fullName.getText())
                .append("phoneNumber", phoneNumber.getText());

        InsertOneResult result = collection.insertOne(data);*/
        if(!phoneNumber.getText().matches(regexStr)){

        }
        else if(fullName.getText().length()<6) {

        }
        else {
            Document user = new Document("_id", new ObjectId().toString());
            user.append("email",uEmail)
                    .append("password",uPass)
                            .append("phonenumber", phoneNumber.getText())
                                    .append("fullname", fullName.getText())
                                            .append("job", uJob)
                                                    .append("stars","0");
            db.getCollection("users").insertOne(user);
            new DiniController().handleScenes(uJob.equals("Driver") ? "HomeDriver.fxml" : "HomeRider.fxml", fullName);
        }

        /*if (result.getInsertedId() != null) {
            System.out.println("Data submitted successfully!");
            // new DiniController().handleScenes("ProfileRider.fxml",finishRegister);
        } else {
            System.out.println("Data submission failed.");
        }*/
    }
    }

