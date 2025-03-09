package com.example.chemistryapp;

import com.example.chemistryapp.Controller.FirebaseInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FirebaseInitializer.initialize(); // Initialize Firebase

        primaryStage.setFullScreen(true); // Force full-screen mode
        primaryStage.setFullScreenExitHint("Press ESC to exit full-screen mode"); // Optional hint
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}