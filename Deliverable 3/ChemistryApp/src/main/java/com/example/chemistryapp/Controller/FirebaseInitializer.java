package com.example.chemistryapp.Controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Initialize firebase to be able to log in
 */
public class FirebaseInitializer {

    /**
     * initializes the method to read the key file and connect to the database
     */
    public static void initialize() {
        try {
            FileDecoder.decodeFile("src/main/resources/encoded.txt", "src/main/resources/chemistryapp-d1220-firebase-adminsdk-fbsvc-27539cff24.json");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (FileInputStream serviceAccount =
                     new FileInputStream("src/main/resources/chemistryapp-d1220-firebase-adminsdk-fbsvc-27539cff24.json")) {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://console.firebase.google.com/u/0/project/chemistryapp-d1220/database/chemistryapp-d1220-default-rtdb/data/~2F")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }
}