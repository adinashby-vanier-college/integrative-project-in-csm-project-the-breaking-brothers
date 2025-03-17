package com.example.chemistryapp.Controller;

import com.example.chemistryapp.View.PHView;
import com.example.chemistryapp.View.PeriodicTableView;
import com.example.chemistryapp.View.StoichiometryView;
import com.example.chemistryapp.View.UserGuideView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

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
    private Tab userGuideTab;

    @FXML
    public void initialize() {

        loadMoleculeViewerTab();


        loadPeriodicTableTab();

        loadStoichiometryTab();

        loadpHCalculator();

        loadUserGuide();


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
        PeriodicTableView periodicTableView = new PeriodicTableView();

        periodicTableTab.setContent(periodicTableView.InitializePeriodicTable());

    }

    private void loadStoichiometryTab() {
        StoichiometryView stoichiometryView = new StoichiometryView();

        stoichiometryTab.setContent(stoichiometryView.initializeStoichiometry());
    }

    private void loadpHCalculator() {
        PHView phView = new PHView();

        pHCalculatorTab.setContent(phView.pHCalcInitialize());
    }

    private void loadUserGuide() {
        UserGuideView userGuideView = new UserGuideView();

        userGuideTab.setContent(userGuideView.initializeUserGuide());
    }
}
