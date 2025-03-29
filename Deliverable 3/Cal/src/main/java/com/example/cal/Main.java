package com.example.cal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showMainMenu();
    }

    // Show the Main Menu
    public static void showMainMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/cal/main_menu.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Calculator Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Show the Ideal Gas Calculator
    public static void showIdealGasCalculator() throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/cal/ideal_gas.fxml"));
        GridPane root = loader.load();
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Ideal Gas Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Show the Real Gas Calculator
    public static void showRealGasCalculator() throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/cal/real_gas.fxml"));
        GridPane root = loader.load();
        Scene scene = new Scene(root, 400, 350);

        primaryStage.setTitle("Real Gas Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showAcidBaseCalculator() throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/cal/acid_base.fxml"));
        GridPane root = loader.load();
        Scene scene = new Scene(root, 400, 400);

        primaryStage.setTitle("Acid-Base Neutralization Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showGibbsFreeEnergyCalculator() throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/cal/gibbs_free_energy.fxml"));
        GridPane root = loader.load();
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Gibbs Free Energy Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
