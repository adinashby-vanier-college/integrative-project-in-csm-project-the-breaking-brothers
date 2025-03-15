package com.example.chemistryapp.Controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    // Initialize Firebase


    @FXML
    protected void handleLogin() {

        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Email and password cannot be empty.");
            return;
        }

        try {
            // firebase authentication
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
            System.out.println("Login successful! User UID: " + userRecord.getUid());

            GlobalData.isLoggedIn = true;
            OpenMainDashboard.openMainDashboard();
        } catch (FirebaseAuthException e) {
            errorLabel.setText("Invalid email or password. Please try again.");
        }
    }



    @FXML
    private void handleSignUp() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/signup.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) emailField.getScene().getWindow();
            Scene scene = emailField.getScene(); // Reuse the existing scene


            scene.setRoot(root);


            stage.setFullScreen(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // show alerts
    public void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.getDialogPane().getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        alert.getDialogPane().getStyleClass().add("alert");
        alert.getDialogPane().getStyleClass().add("alert-info");

        alert.showAndWait();
    }
}