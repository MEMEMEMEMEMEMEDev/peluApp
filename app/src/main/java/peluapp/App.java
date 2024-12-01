package peluapp;

import peluapp.controller.AppController;
import peluapp.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        // Probar conexión a la base de datos
        // Debemos separar la logica proximamente
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos!");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        // Iniciar el controlador principal
        AppController controller = new AppController();
        controller.start();
    }
}
