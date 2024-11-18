package co.edu.uniquindio.tiendamultinivelbases2.dao;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.DetalleVenta;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {

    // Método para insertar un nuevo detalle de venta
    public boolean insertarDetalleVenta(DetalleVenta detalleVenta) {
        String sql = "INSERT INTO detalle_venta (venta_id, libro_id, cantidad, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detalleVenta.getVentaId());     // ID de la venta
            stmt.setInt(2, detalleVenta.getLibroId());     // ID del libro
            stmt.setInt(3, detalleVenta.getCantidad());    // Cantidad de libros vendidos
            stmt.setDouble(4, detalleVenta.getSubtotal()); // Subtotal de la venta

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para actualizar un detalle de venta
    public boolean actualizarDetalleVenta(DetalleVenta detalleVenta) {
        String sql = "UPDATE detalle_venta SET cantidad = ?, subtotal = ? WHERE venta_id = ? AND libro_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detalleVenta.getCantidad());    // Nueva cantidad de libros
            stmt.setDouble(2, detalleVenta.getSubtotal()); // Nuevo subtotal
            stmt.setInt(3, detalleVenta.getVentaId());     // ID de la venta
            stmt.setInt(4, detalleVenta.getLibroId());     // ID del libro

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para eliminar un detalle de venta
    public boolean eliminarDetalleVenta(int ventaId, int libroId) {
        String sql = "DELETE FROM detalle_venta WHERE venta_id = ? AND libro_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ventaId);  // ID de la venta
            stmt.setInt(2, libroId);  // ID del libro

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para obtener todos los detalles de una venta
    public List<DetalleVenta> obtenerDetallesVentaPorId(int ventaId) {
        List<DetalleVenta> detallesVenta = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta WHERE venta_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ventaId); // ID de la venta
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int libroId = rs.getInt("libro_id");
                int clienteId = rs.getInt("cliente_id");  // Agregar clienteId
                int vendedorId = rs.getInt("vendedor_id");  // Agregar vendedorId
                int cantidad = rs.getInt("cantidad");
                double subtotal = rs.getDouble("subtotal");

                detallesVenta.add(new DetalleVenta(ventaId, clienteId, vendedorId, libroId, cantidad, subtotal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallesVenta;
    }

    // Método para obtener todos los detalles de venta (para reportes generales)
    public List<DetalleVenta> obtenerTodosLosDetallesVenta() {
        List<DetalleVenta> detallesVenta = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ventaId = rs.getInt("venta_id");
                int libroId = rs.getInt("libro_id");
                int clienteId = rs.getInt("cliente_id");  // Agregar clienteId
                int vendedorId = rs.getInt("vendedor_id");  // Agregar vendedorId
                int cantidad = rs.getInt("cantidad");
                double subtotal = rs.getDouble("subtotal");

                detallesVenta.add(new DetalleVenta(ventaId, clienteId, vendedorId, libroId, cantidad, subtotal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallesVenta;
    }

}