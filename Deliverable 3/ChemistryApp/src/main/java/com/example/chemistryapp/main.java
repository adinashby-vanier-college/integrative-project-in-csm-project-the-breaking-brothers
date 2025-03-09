package com.example.chemistryapp;

import com.example.chemistryapp.Controller.FirebaseInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FirebaseInitializer.initialize(); // Initialize Firebase

        primaryStage.setFullScreen(true); // Force full-screen mode
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));


        primaryStage.setTitle("Login");
        primaryStage.setFullScreenExitHint(""); // Remove the full screen hint help alert
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}