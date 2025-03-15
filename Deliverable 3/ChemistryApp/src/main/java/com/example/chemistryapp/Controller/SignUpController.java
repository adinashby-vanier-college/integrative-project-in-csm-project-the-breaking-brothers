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
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleSignUp() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Email and password cannot be empty.");
            return;
        }

        if (!isValidEmail(email)) {
            errorLabel.setText("Invalid email format.");
            return;
        }

        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password);

            // Create user in firebase
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            System.out.println("User successfully created: " + userRecord.getUid());
            GlobalData.isLoggedIn = true;
            OpenMainDashboard.openMainDashboard();

            emailField.clear();
            passwordField.clear();
        } catch (Exception e) {
            // in case sign up fails.
            System.err.println("Sign up failed: " + e.getMessage());
            errorLabel.setText("Failed to create user: " + e.getMessage());
        }
    }


    @FXML
    private void handleLogin() {
        try {
            // load fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();

            // get stage scene
            Stage stage = (Stage) emailField.getScene().getWindow();
            Scene scene = emailField.getScene();

            scene.setRoot(root);

            // keep full screen
            stage.setFullScreen(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        // check if email is valid
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
