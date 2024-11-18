package co.edu.uniquindio.tiendamultinivelbases2.dao;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.DetallePedido;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO {

    // Método para insertar un nuevo detalle de pedido
    public boolean insertarDetallePedido(DetallePedido detallePedido) {
        String sql = "INSERT INTO detalle_pedido (pedido_id, libro_id, cantidad, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detallePedido.getPedidoId());  // Pedido ID
            stmt.setInt(2, detallePedido.getLibroId());   // Libro ID
            stmt.setInt(3, detallePedido.getCantidad());  // Cantidad
            stmt.setDouble(4, detallePedido.getSubtotal());  // Subtotal

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para actualizar un detalle de pedido
    public boolean actualizarDetallePedido(DetallePedido detallePedido) {
        String sql = "UPDATE detalle_pedido SET cantidad = ?, subtotal = ? WHERE pedido_id = ? AND libro_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detallePedido.getCantidad());    // Nueva cantidad
            stmt.setDouble(2, detallePedido.getSubtotal()); // Nuevo subtotal
            stmt.setInt(3, detallePedido.getPedidoId());    // Pedido ID
            stmt.setInt(4, detallePedido.getLibroId());     // Libro ID

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para obtener todos los detalles de los pedidos
    public List<DetallePedido> obtenerTodosLosDetallesPedidos() {
        List<DetallePedido> detallesPedidos = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedido";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int pedidoId = rs.getInt("pedido_id");
                int libroId = rs.getInt("libro_id");
                int cantidad = rs.getInt("cantidad");
                double subtotal = rs.getDouble("subtotal");

                detallesPedidos.add(new DetallePedido(pedidoId, libroId, cantidad, subtotal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallesPedidos;
    }
}
