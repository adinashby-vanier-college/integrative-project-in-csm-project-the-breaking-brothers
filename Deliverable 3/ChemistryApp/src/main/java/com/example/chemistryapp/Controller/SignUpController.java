package com.example.chemistryapp.Controller;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class SignUpController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleSignUp() {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Check if email and password are provided
        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Email and password cannot be empty.");
            return;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            errorLabel.setText("Invalid email format.");
            return;
        }

        try {
            // Create a new user in Firebase
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password);

            // Create the user
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            System.out.println("User successfully created: " + userRecord.getUid());
            GlobalData.isLoggedIn = true;
            OpenMainDashboard.openMainDashboard();

            emailField.clear();
            passwordField.clear();
        } catch (Exception e) {
            // Handle sign-up failure
            System.err.println("Sign up failed: " + e.getMessage());
            errorLabel.setText("Failed to create user: " + e.getMessage());
        }
    }


    @FXML
    private void handleLogin() {
        try {
            // Load the login FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();

            // Get the current stage and scene
            Stage stage = (Stage) emailField.getScene().getWindow();
            Scene scene = emailField.getScene(); // Reuse the existing scene

            // Replace the root node of the existing scene
            scene.setRoot(root);

            // Keep the stage in full screen mode
            stage.setFullScreen(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        // Simple email validation regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidUsername(String username) {
        return username != null && username.length() >= 3;
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
