package com.example.chemistryapp.Controller;

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
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Simulate validation (replace with your actual logic)
        if (!isValidEmail(email) || !isValidUsername(username) || !isValidPassword(password)) {
            errorLabel.setText("Invalid input. Please check your details.");
        } else {
            errorLabel.setText("");
            // Proceed with sign-up logic
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

    // Dummy validation methods (replace with your actual logic)
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    private boolean isValidUsername(String username) {
        return username != null && username.length() >= 3;
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
