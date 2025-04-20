package com.example.chemistryapp.View;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 * Menu bar class
 */
public class CustomMenuBarView {
    public MenuBar initializeMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu settingMenu = new Menu("Settings");
        Menu helpMenu = new Menu("Help");

        /*fileMenu.setOnAction();
        settingMenu.setOnAction();
        helpMenu.setOnAction();*/

        menuBar.getMenus().addAll(fileMenu, settingMenu, helpMenu);
        return menuBar;
    }
}
