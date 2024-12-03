package peluapp.model;

import peluapp.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CitaDAO {

    public boolean agregarCita(String cliente, String fechaHora, String detalles) {
        String sql = "INSERT INTO citas (cliente, fecha_hora, detalles) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente);
            statement.setString(2, fechaHora);
            statement.setString(3, detalles);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar cita: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarCita(int id, String nuevoCliente, String nuevaFechaHora, String nuevosDetalles) {
        String sql = "UPDATE citas SET cliente = ?, fecha_hora = ?, detalles = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nuevoCliente);
            statement.setString(2, nuevaFechaHora);
            statement.setString(3, nuevosDetalles);
            statement.setInt(4, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al modificar cita: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCita(int id) {
        String sql = "DELETE FROM citas WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar cita: " + e.getMessage());
            return false;
        }
    }

    public List<String[]> obtenerTodasLasCitas() {
       List<String[]> citas = new ArrayList<>();
       String sql = "SELECT id, cliente, fecha_hora, detalles FROM citas";

       try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            String id = String.valueOf(resultSet.getInt("id"));
            String cliente = resultSet.getString("cliente");
            String fechaHora = resultSet.getString("fecha_hora");
            String detalles = resultSet.getString("detalles");

            citas.add(new String[]{id, cliente, fechaHora, detalles});
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener citas: " + e.getMessage());
        }

        return citas;
    }
}
