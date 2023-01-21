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
    static private String uID;
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

    public static void setuID(String uID) { SignupController2.uID = uID;}

    public static String getuEmail() {
        return uEmail;
    }

    public static String getuPass() {
        return uPass;
    }

    public static String getuJob() {
        return uJob;
    }

    public static String getuID() { return uID;}

    @FXML
    private void finishRegistration() throws Exception {
        MongoCollection<Document> collection = db.getCollection("users");

        if(!phoneNumber.getText().matches(regexStr)){

        }
        else if(fullName.getText().length()<6) {

        }
        else {
            setuID(new ObjectId().toString());
            Document user = new Document("_id", getuID());
            user.append("email",uEmail)
                    .append("password",uPass)
                            .append("phonenumber", phoneNumber.getText())
                                    .append("fullname", fullName.getText())
                                            .append("job", uJob)
                                                    .append("stars",0.0)
                                                            .append("numcards",0);
            db.getCollection("users").insertOne(user);
            new DiniController().handleScenes(uJob.equals("Driver") ? "HomeDriver.fxml" : "HomeRider.fxml", fullName);
        }
    }
    }

