package com.example.cal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML
    private Button idealGasButton;

    @FXML
    private void openIdealGasCalculator() {
        try {
            Main.showIdealGasCalculator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button realGasButton;

    @FXML
    private void openRealGasCalculator() {
        try {
            Main.showRealGasCalculator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button acidBaseButton;

    @FXML
    private void openAcidBaseCalculator() {
        try {
            Main.showAcidBaseCalculator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button gibbsButton;

    @FXML
    private void openGibbsFreeEnergyCalculator() {
        try {
            Main.showGibbsFreeEnergyCalculator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}