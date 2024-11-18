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

    public List<Venta> obtenerVentasCompletadas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta WHERE estado = 'Completado'";

        try (Connection conn = DataBaseConnection.getConnection();
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
    public List<Venta> obtenerVentasPendientes() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta WHERE estado = 'Pendiente'";  // Ventas con estado 'pendiente'

        try (Connection conn = DataBaseConnection.getConnection();
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

    public List<Venta> obtenerVentasUltimoMes() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta WHERE fecha >= ADD_MONTHS(SYSDATE, -1) and estado = 'Completado'";  // Ventas del último mes

        try (Connection conn = DataBaseConnection.getConnection();
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
    public boolean insertarVenta(Venta venta) {
        String sql = "INSERT INTO Venta (fecha, estado, total) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(2, venta.getFecha());
            stmt.setString(3, venta.getEstado());
            stmt.setDouble(4, venta.getTotal());

            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;  // Retorna true si se insertaron filas
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean actualizarEstadoVenta(int ventaId, String nuevoEstado) {
        String sql = "UPDATE Venta SET estado = ? WHERE venta_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, ventaId);

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;  // Retorna true si se actualizó alguna fila
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public double obtenerTotalVentasPorVendedor(int vendedorId) {
        double totalVentas = 0.0;

        String query = "SELECT SUM(v.precio_total) AS total FROM Venta v " +
                "WHERE v.vendedor_id = ? AND v.estado = 'Completada'"; // Asegúrate de usar el estado correcto

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vendedorId); // Establecemos el vendedor_id en la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalVentas = rs.getDouble("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Aquí debes manejar excepciones de forma adecuada
        }

        return totalVentas;
    }
}
