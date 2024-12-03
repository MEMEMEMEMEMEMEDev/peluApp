package peluapp.controller;

import peluapp.view.GastosView;
import peluapp.view.MainMenuView;
import peluapp.view.CitasView;


public class AppController {
    private final MainMenuView mainMenu;

    public AppController() {
        mainMenu = new MainMenuView(this);
    }

    public void start() {
        mainMenu.setVisible(true);
    }

    public void showCitasView() {
        mainMenu.setContent(new CitasView());
    }

    public void showGastosView() {
        mainMenu.setContent(new GastosView());
    }
}
