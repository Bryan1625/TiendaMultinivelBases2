package co.edu.uniquindio.tiendamultinivelbases2.dao;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.Venta;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.DataBaseConnection;  // Cambiar el nombre correcto de la clase aquí

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    // Método para obtener todas las ventas
    public List<Venta> obtenerTodasLasVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta";  // Consulta SELECT
        try (Connection conn = DataBaseConnection.getConnection();  // Cambiar a DataBaseConnection
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ventaId = rs.getInt("venta_id");
                Date fecha = rs.getDate("fecha");
                String estado = rs.getString("estado");
                double total = rs.getDouble("total");

                ventas.add(new Venta(ventaId, fecha, estado, total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    // Método para obtener una venta por su ID
    public Venta obtenerVentaPorId(int ventaId) {
        String sql = "SELECT * FROM Venta WHERE venta_id = ?";
        try (Connection conn = DataBaseConnection.getConnection();  // Cambiar a DataBaseConnection
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ventaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Date fecha = rs.getDate("fecha");
                String estado = rs.getString("estado");
                double total = rs.getDouble("total");

                return new Venta(ventaId, fecha, estado, total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
