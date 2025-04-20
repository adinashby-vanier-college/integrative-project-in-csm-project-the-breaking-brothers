package com.example.chemistryapp.Controller;

import com.example.chemistryapp.Model.GlobalData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import javax.swing.*;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Login controller, controls the Login system
 */
public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    // Initialize Firebase

    private static final String FIREBASE_API_KEY = "AIzaSyBN05yC5khjN9dVQRjvmFIsi5jT-nKFyfY";

    @FXML
    protected void handleLogin() {

        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Email and password cannot be empty.");
            return;
        }

        String authUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + FIREBASE_API_KEY;

        try {
            URL url = new URL(authUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\",\"returnSecureToken\":true}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8)) {
                    String response = scanner.useDelimiter("\\A").next();
                    JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

                    System.out.println("Login successful");
                    GlobalData.email = email;
                    GlobalData.isLoggedIn = true;

                    OpenMainDashboard.openMainDashboard();
                }
            } else {
                try (Scanner scanner = new Scanner(conn.getErrorStream(), StandardCharsets.UTF_8)) {
                    String errorResponse = scanner.useDelimiter("\\A").next();
                    JsonObject jsonError = JsonParser.parseString(errorResponse).getAsJsonObject();
                    String errorMessage = jsonError.getAsJsonObject("error").get("message").getAsString();
                    errorLabel.setText("Login failed. Wrong Email/Password Combination");
                }
            }
        } catch (Exception e) {
            errorLabel.setText("An error occurred. Please try again.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignUp() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLViews/signup.fxml"));
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