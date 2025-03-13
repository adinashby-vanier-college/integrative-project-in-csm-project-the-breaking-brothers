package com.example.chemistryapp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoaderController {

    @FXML
    private TextField moleculeInput;

    @FXML
    private ImageView moleculeImage;

    @FXML
    private void handleLoad2D() {
        String moleculeFormula = moleculeInput.getText();
        if (moleculeFormula != null && !moleculeFormula.isEmpty()) {
            loadMoleculeImage(moleculeFormula);
        } else {
            showAlert("Input Error", "Please enter a valid molecular formula.");
        }
    }

    @FXML
    private void handleLoad3D() {
        String moleculeFormula = moleculeInput.getText();
        if (moleculeFormula != null && !moleculeFormula.isEmpty()) {
            ThirdDimensionViewer viewer = new ThirdDimensionViewer();
            viewer.run(moleculeFormula, moleculeImage.getScene());
        } else {
            showAlert("Input Error", "Please enter a valid molecular formula.");
        }
    }

    private void loadMoleculeImage(String moleculeFormula) {
        // URL for getting the png image
        String urlString = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/" + moleculeFormula + "/PNG";

        // Start a thread
        new Thread(() -> {
            try {
                // Send the htp request
                HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
                connection.setRequestMethod("GET");

                // Read the response
                try (InputStream in = connection.getInputStream()) {
                    // Create an Image from the InputStream
                    Image moleculeImageData = new Image(in);
                    Platform.runLater(() -> {
                        moleculeImage.setImage(moleculeImageData); // Update the ImageView
                    });
                } catch (Exception e) {
                    Platform.runLater(() -> showAlert("Error", "Failed to load molecule image."));
                }
            } catch (Exception e) {
                Platform.runLater(() -> showAlert("Error", "Failed to connect to PubChem."));
            }
        }).start();
    }

    private void showAlert(String title, String message) {
        // Create the alert dialog
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Ensure the alert is owned by the main stage and appears above it
        alert.initOwner(moleculeImage.getScene().getWindow());

        // Show the alert without blocking the application
        alert.showAndWait();
    }
}

