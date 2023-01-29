package com.example.dinim3akalpha001;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.mongodb.client.model.Filters.eq;

/**
 * SignupController is responsible for handling the signup process for the DiniM3ak application.
 * It includes methods for validating user input, sending a verification email, and navigating to the
 * appropriate scenes.
 */

public class SignupController {
    @FXML
    private TextField Email;
    @FXML
    private TextField pass_text;
    @FXML
    private CheckBox ShowPass;
    @FXML
    private TextField pass_hidden;
    @FXML
    private Button Signup;
    @FXML
    private ProgressIndicator Ind;

    /**
     * Login() is responsible for handling the login button click event. It navigates the user to the
     * login scene.
     */
    @FXML
    private void Login() throws IOException {
        new DiniController().handleScenes("Login.fxml",Signup);
    }

    /**
     * handleSignup() is responsible for handling the signup button click event. It checks if the email
     * and password entered by the user are valid and if the email is already in use. If all input is
     * valid, it sends a verification email and navigates to the verification scene.
     */
    @FXML
    private void handleSignup() throws IOException {
        if(db.getCollection("users").find(eq("email",Email.getText())).first()!=null) {
            System.out.println("Email already exists.");
        }
        else if(pass_hidden.getText().length()<6){
            System.out.println("Bad password.");
        }
        else if(!isValid(Email.getText())){
            System.out.println("Invalid Email.");
        }
        else {
            new LoadingScreen().start();
        }

    }

    /**
     * isValid() is a static method that takes in a string email as input and returns a boolean indicating
     * if the email is in a valid format.
     * @param email is a string email
     * @return a boolean indicating if the email is in a valid format.
     */
    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * LoadingScreen class is a thread class that extends the Thread class and overrides the run method.
     * The run method is responsible for handling the verification process and displaying the verification scene.
     */
    class LoadingScreen extends Thread{
        /**
         * This method is the entry point for the thread. It calls the handleVerif method to handle the verification process.
         * If an IOException is caught, it throws a new RuntimeException with the caught exception as its parameter.
         * After the verification process is done, it uses the Platform.runLater method to run the handleScenes method on the FX Application Thread,
         * to change the scene to the verification scene.
         */
        @Override
        public void run() {
           try {
                handleVerif();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(() -> {
                try {
                    new DiniController().handleScenes("Verification.fxml", Signup);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    /**
     * This method is used to handle the verification process.
     * It sends a verification code to the user's email, and stores the user's email and password in the SignupController2 class.
     * @throws IOException if there is an issue with sending the email.
     */
    private void handleVerif() throws IOException {
            Ind.setVisible(true);
            Signup.setDisable(true);
            final String username = "dinim3akservicedonotreply@gmail.com";
            final String password = "kgafplabwyghaaaz";
            String randomNum = String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99999 + 1));
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("dinim3akservicedonotreply@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(Email.getText())
                );
                message.setSubject("Dini M3ak");
                message.setText("Welcome to Dini M3ak :), Verification Code : " + randomNum);
                Transport.send(message);
                System.out.println("Done");
                VerificationController.setCode(randomNum);
                SignupController2.setuEmail(Email.getText());
                SignupController2.setuPass(pass_hidden.getText());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    /**
     * toggleVisiblePassword() toggles the visibility of the password field if the button is clicked .
     */
    @FXML
    protected void toggleVisiblePassword() {
        if (ShowPass.isSelected()) {
            pass_text.setVisible(true);
            pass_text.setText(pass_hidden.getText());
            pass_hidden.setVisible(false);
            return;
        }
        pass_hidden.setText(pass_text.getText());
        pass_hidden.setVisible(true);
        pass_text.setVisible(false);
    }

}
