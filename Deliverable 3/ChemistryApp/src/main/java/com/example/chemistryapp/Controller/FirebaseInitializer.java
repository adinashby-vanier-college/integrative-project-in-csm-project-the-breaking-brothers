package com.example.chemistryapp.Controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {
    public static void initialize() {
        try (FileInputStream serviceAccount =
                     new FileInputStream("src/main/resources/chemistry-d7769-firebase-adminsdk-fbsvc-f820a98144.json")) {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://console.firebase.google.com/u/0/project/chemistry-d7769/database/chemistry-d7769-default-rtdb/data/~2F")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }
}