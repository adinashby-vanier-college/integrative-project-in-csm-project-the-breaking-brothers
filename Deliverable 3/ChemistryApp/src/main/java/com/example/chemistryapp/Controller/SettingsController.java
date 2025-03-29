package com.example.chemistryapp.Controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;

public class SettingsController {

    @FXML
    private ToggleButton darkModeToggle;

    private static final String LIGHT_THEME = "/CSSFiles/light-theme.css";
    private static final String DARK_THEME = "/CSSFiles/dark-theme.css";
    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;

        if (scene == null) {
            System.err.println("Scene is null");
            return;
        }

        darkModeToggle.setOnAction(event -> {
            boolean darkMode = darkModeToggle.isSelected();
            applyTheme(darkMode);
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
}
