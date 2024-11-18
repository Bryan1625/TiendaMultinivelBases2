package co.edu.uniquindio.tiendamultinivelbases2.dao;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.Inventario;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    // Método para obtener todos los registros del inventario
    public List<Inventario> obtenerTodosLosInventarios() {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM inventario";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int libroId = rs.getInt("libro_id");
                int cantidad = rs.getInt("cantidad");

                inventarios.add(new Inventario(libroId, cantidad));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    // Método para obtener el inventario de un libro específico por su ID
    public Inventario obtenerInventarioPorId(int libroId) {
        String sql = "SELECT * FROM inventario WHERE libro_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, libroId);  // ID del libro
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int existencia = rs.getInt("existencia");
                return new Inventario(libroId, existencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para insertar un nuevo registro en el inventario
    public boolean insertarInventario(int libroId, int cantidad) {
        String sql = "INSERT INTO inventario (libro_id, cantidad) VALUES (?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, libroId);
            stmt.setInt(2, cantidad);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para actualizar la cantidad de un libro en el inventario
    public boolean actualizarInventario(Inventario inventario) {
        String sql = "UPDATE inventario SET cantidad = ? WHERE libro_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inventario.getExistencia());
            stmt.setInt(2, inventario.getLibroId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para eliminar un registro de inventario
    public boolean eliminarInventario(int libroId) {
        String sql = "DELETE FROM inventario WHERE libro_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, libroId);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
