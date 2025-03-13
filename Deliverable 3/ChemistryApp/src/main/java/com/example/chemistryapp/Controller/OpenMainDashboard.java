package com.example.chemistryapp.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenMainDashboard {

    public static void openMainDashboard() {
        try {
            // Load the MainDashboard.fxml
            FXMLLoader loader = new FXMLLoader(OpenMainDashboard.class.getResource("/MainDashboard.fxml"));
            Parent root = loader.load();

            // Get the controller for the MainDashboard (Optional)
            MainDashboardController controller = loader.getController();
            // Now, the initialize() method in MainDashboardController is automatically called

            // Create a new stage and show the scene with the loaded FXML
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root));
            stage.setFullScreen(true); // Optional: set full screen
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}