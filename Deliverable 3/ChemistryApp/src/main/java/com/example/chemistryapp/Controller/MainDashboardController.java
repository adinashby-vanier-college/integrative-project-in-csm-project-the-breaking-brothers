package com.example.chemistryapp.Controller;

import com.example.chemistryapp.View.PHView;
import com.example.chemistryapp.View.PeriodicTableView;
import com.example.chemistryapp.View.StoichiometryView;
import com.example.chemistryapp.View.UserGuideView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Tab idealGasCalculatorTab;

    @FXML
    private Tab realGasCalculatorTab;

    @FXML
    private Tab acidBaseCalculatorTab;

    @FXML
    private Tab gibbsFreeEnergyCalculatorTab;

    @FXML
    private Tab settingsTab;

    private Scene scene;

    @FXML
    public void initialize() {

        loadMoleculeViewerTab();


        loadPeriodicTableTab();

        loadStoichiometryTab();

        loadpHCalculator();

        loadUserGuide();

        loadIdealGasCalculator();

        loadRealGasCalculator();

        loadAcidBaseCalculator();

        loadGibbsCalculator();

        loadSettingsTab();

    }

    public void setScene(Scene scene) {
        this.scene = scene;

        if (settingsTab.isSelected()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLViews/Settings.fxml"));
                Parent root = loader.load();
                SettingsController settingsController = loader.getController();
                settingsController.setScene(this.scene);
                settingsTab.setContent(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadSettingsTab() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLViews/Settings.fxml"));
            Parent root = loader.load();


            SettingsController settingsController = loader.getController();


            settingsTab.setOnSelectionChanged(event -> {
                if (settingsTab.isSelected() && settingsController != null) {

                    settingsController.setScene(settingsTab.getTabPane().getScene());
                }
            });

            settingsTab.setContent(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loadIdealGasCalculator() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLViews/ideal_gas.fxml"));
            Parent root = loader.load();
            idealGasCalculatorTab.setContent(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Show the Real Gas Calculator
    public void loadRealGasCalculator() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLViews/real_gas.fxml"));
            Parent root = loader.load();
            realGasCalculatorTab.setContent(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAcidBaseCalculator() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLViews/acid_base.fxml"));
            Parent root = loader.load();
            acidBaseCalculatorTab.setContent(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadGibbsCalculator() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLViews/gibbs_free_energy.fxml"));
            Parent root = loader.load();
            gibbsFreeEnergyCalculatorTab.setContent(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMoleculeViewerTab() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLViews/MoleculeViewer3D.fxml"));
            StackPane root = loader.load();
            moleculeViewerTab.setContent(root);
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
