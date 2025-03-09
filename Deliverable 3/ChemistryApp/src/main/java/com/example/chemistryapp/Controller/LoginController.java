package com.example.chemistryapp.Controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

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
            // Authenticate the user using Firebase
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
            System.out.println("Login successful! User UID: " + userRecord.getUid());

            // Show success message
            showAlert("Success", "Login successful!");
        } catch (FirebaseAuthException e) {
            // Handle login failure
            System.err.println("Login failed: " + e.getMessage());
            showAlert("Error", "Invalid email or password.");
        }
    }

    @FXML
    protected void handleSignUp() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Email and password cannot be empty.");
            return;
        }

        try {
            // Create a new user in Firebase
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("Sign up successful! User UID: " + userRecord.getUid());

            // Show success message
            showAlert("Success", "Sign up successful!");
        } catch (FirebaseAuthException e) {
            // Handle sign-up failure
            System.err.println("Sign up failed: " + e.getMessage());
            showAlert("Error", "Failed to create user. Please try again.");
        }
    }

    // Helper method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}