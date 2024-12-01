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
        System.out.println("Navegando a la vista de Citas");
        mainMenu.setContent(new CitasView());
    }

    public void showGastosView() {
        System.out.println("Navegando a la vista de Gastos");
        mainMenu.setContent(new GastosView());
    }
}
