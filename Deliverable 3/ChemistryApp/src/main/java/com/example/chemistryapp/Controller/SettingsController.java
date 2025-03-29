package com.example.chemistryapp.Controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private ToggleButton darkModeToggle;

    private static final String LIGHT_THEME = "/styles/light-theme.css";
    private static final String DARK_THEME = "/styles/dark-theme.css";

    @FXML
    public void initialize() {
        Scene scene = darkModeToggle.getScene();

        // Button is clicked
        darkModeToggle.setOnAction(event -> {
            boolean darkMode = darkModeToggle.isSelected();
            applyTheme(scene, darkMode);
        });
    }

    private void applyTheme(Scene scene, boolean darkMode) {
        scene.getStylesheets().clear();
        String theme = darkMode ? DARK_THEME : LIGHT_THEME;
        scene.getStylesheets().add(getClass().getResource(theme).toExternalForm());
    }
}
