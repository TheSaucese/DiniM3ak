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

    static private String code;
    @FXML
    private Button Confirm;
    @FXML
    private TextField inputCode;
    @FXML
    private void handleConfirm() throws IOException {
        if (code.equals(inputCode.getText())) {
            MongoCollection<Document>  collection = db.getCollection("users");
            Document user = new Document("_id", new ObjectId());
            /*System.out.println(Email.getText());
            System.out.println(pass_text.getText());
            user.append("email",Email.getText())
                    .append("password",pass_text.getText());

            new DiniController().handleScenes("Choice.fxml", Confirm);*/
        }
        else {
            new Alert(Alert.AlertType.ERROR,"bruh").show();

        }
    }
    static public void setCode(String c){
        code=c;
    }
}
