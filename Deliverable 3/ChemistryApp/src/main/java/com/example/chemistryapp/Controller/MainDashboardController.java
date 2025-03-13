package com.example.chemistryapp.Controller;

import com.example.chemistryapp.View.PHView;
import com.example.chemistryapp.View.StoichiometryView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainDashboardController {

    @FXML
    private Tab moleculeViewerTab;

    @FXML
    private Tab periodicTableTab;

    @FXML
    private Tab stoichiometryTab;

    @FXML
    private Tab pHCalculatorTab;

    @FXML
    public void initialize() {
        // Loading the content for the "Molecule Viewer" tab (from FXML)
        loadMoleculeViewerTab();

        // Setting up the content for the "Button Tab" dynamically (no FXML)
        loadPeriodicTableTab();

        loadStoichiometryTab();

        loadpHCalculator();
    }

    private void loadMoleculeViewerTab() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MoleculeViewer3D.fxml"));
            StackPane moleculeViewerContent = loader.load();
            moleculeViewerTab.setContent(moleculeViewerContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPeriodicTableTab() {

    }

    private void loadStoichiometryTab() {
        StoichiometryView stoichiometryView = new StoichiometryView();

        stoichiometryTab.setContent(stoichiometryView.initializeStoichiometry());
    }

    private void loadpHCalculator() {
        PHView phView = new PHView();

        pHCalculatorTab.setContent(phView.pHCalcInitialize());
    }
}
