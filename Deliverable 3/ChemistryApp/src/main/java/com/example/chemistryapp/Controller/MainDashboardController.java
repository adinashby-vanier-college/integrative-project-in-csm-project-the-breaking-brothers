package com.example.chemistryapp.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainDashboardController {

    @FXML
    private Tab moleculeViewerTab;

    @FXML
    private Tab periodicTableTab;

    @FXML
    public void initialize() {
        // Loading the content for the "Molecule Viewer" tab (from FXML)
        loadMoleculeViewerTab();

        // Setting up the content for the "Button Tab" dynamically (no FXML)
        loadPeriodicTableTab();
    }

    private void loadMoleculeViewerTab() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MoleculeViewer3D.fxml"));
            AnchorPane moleculeViewerContent = loader.load();
            moleculeViewerTab.setContent(moleculeViewerContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPeriodicTableTab() {

    }
}
