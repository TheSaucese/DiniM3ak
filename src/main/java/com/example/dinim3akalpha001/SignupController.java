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

public class SignupController {
    @FXML
    public static TextField Email;
    @FXML
    public static TextField pass_text;
    @FXML
    private CheckBox ShowPass;
    @FXML
    private TextField pass_hidden;
    @FXML
    private Button Signup;
    @FXML
    private ProgressIndicator Ind;
    @FXML
    private void handleSignup() throws IOException {
        new LoadingScreen().start();
    }

    class LoadingScreen extends Thread{
        @Override
        public void run() {
            try {
                handleVerif();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new DiniController().handleScenes("Verification.fxml",Signup);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

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
            message.setText("Welcome to Dini M3ak :), Verification Code : "+randomNum);
            Transport.send(message);
            System.out.println("Done");
            VerificationController.setCode(randomNum);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
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
