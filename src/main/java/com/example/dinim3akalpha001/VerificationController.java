package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

import static com.example.dinim3akalpha001.MongoController.db;

public class VerificationController {

    public static void setuEmail(String uEmail) {
        VerificationController.uEmail = uEmail;
    }

    public static void setuPass(String uPass) {
        VerificationController.uPass = uPass;
    }

    public static String getuEmail() {
        return uEmail;
    }

    public static String getuPass() {
        return uPass;
    }

    static private String uEmail;

    static private String uPass;
    static private String code;
    @FXML
    private Button Confirm;
    @FXML
    private TextField inputCode;
    @FXML
    private void handleConfirm() throws IOException {
        if (code.equals(inputCode.getText())) {
            MongoCollection<Document>  collection = db.getCollection("users");
            Document user = new Document("_id", new ObjectId().toString());
            user.append("email",uEmail)
                    .append("password",uPass);
            collection.insertOne(user);
            new DiniController().handleScenes("Choice.fxml", Confirm);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Incorrect").show();

        }
    }
    static public void setCode(String c){
        code=c;
    }
}
