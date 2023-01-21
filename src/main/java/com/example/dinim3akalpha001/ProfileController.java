package com.example.dinim3akalpha001;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSDownloadOptions;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.getuEmail;
import static com.example.dinim3akalpha001.SignupController2.getuJob;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ProfileController implements Initializable {
    @FXML
    private Circle Photo;
    @FXML
    private TextField Username;
    @FXML
    private Button Vehicle;
    @FXML
    private ImageView StarsIcons;
    @FXML
    private Text Stars;
    static GridFSBucket gridBucket = GridFSBuckets.create(db);
    @FXML
    private void Upload() throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            mongoupload(file.getPath(),file.getName());
            //Image image = new Image(file.toURI().toString());
            //Photo.setFill(new ImagePattern(image));
            saveToFileSystem(file.getName());

        }
    }
    @FXML
    private void handleVehicle() throws IOException {
        new DiniController().handleScenes("Car.fxml",Vehicle);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentSee.fxml",Vehicle);
    }
    @FXML
    private void handleMore() throws IOException {
        //new DiniController().handleScenes("PaymentAdd.fxml",Vehicle);
        //UNFINISHED :(
    }
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"HomeDriver.fxml":"HomeRider.fxml",Vehicle);
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        UnaryOperator<TextFormatter.Change> modifyChange = c -> {
            if (c.isContentChange()) {
                int newLength = c.getControlNewText().length();
                if (newLength > 15) {
                    String tail = c.getControlNewText().substring(newLength - 15, newLength);
                    c.setText(tail);
                    int oldLength = c.getControlText().length();
                    c.setRange(0, oldLength);
                }
            }
            return c;
        };
        Document user = db.getCollection("users").find(eq("email",getuEmail())).first();
        Username.setTextFormatter(new TextFormatter(modifyChange));
        Username.setText(user.getString("fullname"));
        Double stars = user.getDouble("stars");
        if (stars == 0.0) {
            StarsIcons.setImage(new Image("com/Images/dinim3akalpha001/Stars0.png"));
        } else if (stars == 1.0) {
            StarsIcons.setImage(new Image("com/Images/dinim3akalpha001/Stars1.png"));
        } else if (stars == 2.0) {
            StarsIcons.setImage(new Image("com/Images/dinim3akalpha001/Stars2.png"));
        } else if (stars == 3.0) {
            StarsIcons.setImage(new Image("com/Images/dinim3akalpha001/Stars3.png"));
        } else if (stars == 4.0) {
            StarsIcons.setImage(new Image("com/Images/dinim3akalpha001/Stars4.png"));
        } else if (stars == 5.0) {
            StarsIcons.setImage(new Image("com/Images/dinim3akalpha001/Stars5.png"));
        }
        Stars.setText(user.getDouble("stars")+" Stars");
       /* try {
            saveToFileSystem(db.getCollection("fs.files").find(eq("_id",user.getObjectId("image"))).first().getString("filename"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        */
    }
    public ObjectId mongoupload(String filePath, String fileName) {
        ObjectId fileId = null;
        try {
            InputStream inStream = new FileInputStream(new File(filePath));
            GridFSUploadOptions uploadOptions = new GridFSUploadOptions().chunkSizeBytes(1024).metadata(new Document("type", "image").append("content_type", "image/png"));
            fileId = gridBucket.uploadFromStream(fileName, inStream, uploadOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.getCollection("users").updateOne(eq("email", getuEmail()), set("image",fileId));
        return fileId;
    }

    private void saveToFileSystem(String fileName) throws IOException {
        GridFSDownloadOptions downloadOptions = new GridFSDownloadOptions().revision(0);
        File file = new File("c:/DiniM3ak/"+fileName);
        file.getParentFile().mkdirs(); // Will create parent directories if not exists
        file.createNewFile();
        try (FileOutputStream streamToDownloadTo = new FileOutputStream(file)) {
            gridBucket.downloadToStream(fileName, streamToDownloadTo, downloadOptions);
            streamToDownloadTo.flush();
        }
        Image image = new Image(file.toURI().toString());
        Photo.setFill(new ImagePattern(image));
    }

}
