package peluapp.model;

import peluapp.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GastoDAO {

    // Método para agregar un gasto
    public boolean agregarGasto(double monto, String tipo, String formaPago, String descripcion) {
        String sql = "INSERT INTO gastos (monto, tipo, forma_pago, descripcion) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, monto);
            statement.setString(2, tipo);
            statement.setString(3, formaPago);
            statement.setString(4, descripcion);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar gasto: " + e.getMessage());
            return false;
        }
    }

    // Método para modificar un gasto
    public boolean modificarGasto(int id, double monto, String tipo, String formaPago, String descripcion) {
        String sql = "UPDATE gastos SET monto = ?, tipo = ?, forma_pago = ?, descripcion = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, monto);
            statement.setString(2, tipo);
            statement.setString(3, formaPago);
            statement.setString(4, descripcion);
            statement.setInt(5, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al modificar gasto: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un gasto
    public boolean eliminarGasto(int id) {
        String sql = "DELETE FROM gastos WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar gasto: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener todos los gastos
    public List<String[]> obtenerTodosLosGastos() {
        List<String[]> gastos = new ArrayList<>();
        String sql = "SELECT id, monto, tipo, forma_pago, descripcion FROM gastos";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String id = String.valueOf(resultSet.getInt("id"));
                String monto = String.valueOf(resultSet.getDouble("monto"));
                String tipo = resultSet.getString("tipo");
                String formaPago = resultSet.getString("forma_pago");
                String descripcion = resultSet.getString("descripcion");

                gastos.add(new String[]{id, monto, tipo, formaPago, descripcion});
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los gastos: " + e.getMessage());
        }

        return gastos;
    }
}
