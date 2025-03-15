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

        String urlString = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/" + moleculeFormula + "/PNG";

        // cannot run in background for some reason, had to make a thread
        new Thread(() -> {
            try {
                // send request
                HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
                connection.setRequestMethod("GET");

                // read response
                try (InputStream in = connection.getInputStream()) {
                    // image to load the molecule image 2D
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
        // Alert dialog
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // if this line isnt added, the alert will remove the main window
        alert.initOwner(moleculeImage.getScene().getWindow());

        alert.showAndWait();
    }
}

