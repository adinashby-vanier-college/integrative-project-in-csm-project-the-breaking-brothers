package com.example.chemistryapp.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenMainDashboard {

    public static void openMainDashboard() {
        try {
            // Load the main dashboard view
            FXMLLoader loader = new FXMLLoader(OpenMainDashboard.class.getResource("/FXMLViews/MainDashboard.fxml"));
            Parent root = loader.load();

            // Get the controller for MainDashboard
            MainDashboardController mainController = loader.getController();

            // Create a new Stage and Scene
            Stage stage = new Stage();
            Scene scene = new Scene(root);

           if (scene == null) {
               System.out.println("Scene is null!!!");
           }
            mainController.setScene(scene);

            // Configure the stage
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
