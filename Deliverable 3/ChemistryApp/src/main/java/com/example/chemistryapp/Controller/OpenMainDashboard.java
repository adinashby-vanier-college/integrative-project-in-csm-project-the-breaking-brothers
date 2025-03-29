package com.example.chemistryapp.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenMainDashboard {

    public static void openMainDashboard() {
        try {

            FXMLLoader loader = new FXMLLoader(OpenMainDashboard.class.getResource("/FXMLViews/MainDashboard.fxml"));
            Parent root = loader.load();


//            MainDashboardController controller = loader.getController();



            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root));
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}