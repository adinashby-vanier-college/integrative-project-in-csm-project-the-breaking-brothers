package com.example.chemistryapp.Controller;

import com.example.chemistryapp.Model.GlobalData;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;


public class SettingsController {
    @FXML
    private ToggleButton darkModeToggle;

    private static final String LIGHT_THEME = "/CSSFiles/light-theme.css";
    private static final String DARK_THEME = "/CSSFiles/dark-theme.css";
    private Scene scene;
    @FXML
    private Slider fontSizeSlider;
    private static final String COMBOBOX_DARK = "-fx-background-color: #424242; -fx-text-fill: white;";
    private static final String COMBOBOX_LIGHT = "-fx-background-color: white; -fx-text-fill: black;";

    @FXML
    private Label fontSizeValue;
    private FirebaseAuth firebaseAuth;

    public void initialize() {
        fontSizeSlider.setSnapToTicks(true);
        fontSizeSlider.setMajorTickUnit(1);
        fontSizeSlider.setMinorTickCount(0);
        fontSizeSlider.setBlockIncrement(1);
        fontSizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int fontSize = newValue.intValue();
                updateFontSize(fontSize);
            }
        });
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            firebaseAuth = FirebaseAuth.getInstance();
        } catch (Exception e) {
            System.out.println("failed to initialize firebase");
        }


        fontSizeValue.setText(String.valueOf((int)fontSizeSlider.getValue()));
    }

    public void setScene(Scene scene) {
        this.scene = scene;

        if (scene == null) {
            System.err.println("Scene is null");
            return;
        }

        darkModeToggle.setOnAction(event -> {
            boolean darkMode = darkModeToggle.isSelected();
            applyTheme(darkMode);
            updateComboBoxStyles(darkMode);
        });
    }

    private void applyTheme(boolean darkMode) {
        if (scene == null) {
            System.err.println("Scene is null");
            return;
        }

        scene.getStylesheets().clear();
        String theme = darkMode ? DARK_THEME : LIGHT_THEME;
        scene.getStylesheets().add(getClass().getResource(theme).toExternalForm());
    }
    private void updateComboBoxStyles(boolean darkMode) {
        if (scene == null) return;

        // combo box styles
        scene.getRoot().lookupAll(".combo-box").forEach(node -> {
            ComboBox<?> comboBox = (ComboBox<?>) node;
            if (darkMode) {
                comboBox.setStyle(COMBOBOX_DARK);
                comboBox.lookup(".list-cell").setStyle("-fx-text-fill: white;");
            } else {
                comboBox.setStyle(COMBOBOX_LIGHT);
                comboBox.lookup(".list-cell").setStyle("-fx-text-fill: black;");
            }
        });
    }

    private void updateFontSize(int fontSize) {
        if (scene == null) {
            System.err.println("Scene is null!!");
            return;
        }

        // update font size
        fontSizeValue.setText(String.valueOf(fontSize));
        String fontSizeStyle = String.format("-fx-font-size: %dpx;", fontSize);
        scene.getRoot().setStyle(fontSizeStyle);

        fontSizeValue.setFont(Font.font(fontSize));
    }

    public void handleResetPassword(ActionEvent actionEvent) {

    }

    public void handleDeleteAccount(ActionEvent actionEvent) {
        try {

            UserRecord userRecord = firebaseAuth.getUserByEmail(GlobalData.email);


            String resetLink = firebaseAuth.generatePasswordResetLink(GlobalData.email);


            System.out.println("Password reset link: " + resetLink);
        } catch (FirebaseAuthException e) {
            if (e.getErrorCode().equals("auth/user-not-found")) {
                System.out.println("No user found with this email address");
            } else {
                System.out.println("Failed to generate link");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
