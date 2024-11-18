package co.edu.uniquindio.tiendamultinivelbases2.dao;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.Pedido;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.DataBaseConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoDAO {

    // Método para insertar un pedido
    public boolean insertarPedido(Pedido pedido) {
        String sql = "INSERT INTO Pedido (pedido_id, fecha_pedido, fecha_entrega, proveedor_id, estado, total) " +
                "VALUES (seq_pedido_id.NEXTVAL, ?, ?, ?, ?, ?)";  // Se utiliza la secuencia para generar el ID del pedido

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Obtener la fecha de hoy para fecha_pedido
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaPedido = dateFormat.format(new Date());  // Fecha actual

            // Establecer los parámetros para el INSERT
            stmt.setString(1, fechaPedido);  // Fecha del pedido (hoy)
            stmt.setString(2, pedido.getFechaEntrega());  // Fecha de entrega que se pasa como parámetro
            stmt.setInt(3, pedido.getProveedorId());  // ID del proveedor
            stmt.setString(4, "Pendiente");  // Estado inicial del pedido
            stmt.setDouble(5, pedido.getTotal());  // Total del pedido

            // Ejecutar la inserción
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para obtener un pedido por ID
    public Pedido obtenerPedidoPorId(int pedidoId) {
        String sql = "SELECT * FROM Pedido WHERE pedido_id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedidoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String fechaPedido = rs.getString("fecha_pedido");
                String fechaEntrega = rs.getString("fecha_entrega");
                int proveedorId = rs.getInt("proveedor_id");
                String estado = rs.getString("estado");
                double total = rs.getDouble("total");

                return new Pedido(pedidoId, fechaPedido, fechaEntrega, proveedorId, estado, total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para actualizar el estado de un pedido
    public boolean actualizarEstadoPedido(int pedidoId, String nuevoEstado) {
        String sql = "UPDATE Pedido SET estado = ? WHERE pedido_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, pedidoId);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para obtener todos los pedidos pendientes
    public List<Pedido> obtenerPedidosPendientes() {
        List<Pedido> pedidosPendientes = new ArrayList<>();
        String sql = "SELECT * FROM Pedido WHERE estado = 'Pendiente'";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int pedidoId = rs.getInt("pedido_id");
                String fechaPedido = rs.getString("fecha_pedido");
                String fechaEntrega = rs.getString("fecha_entrega");
                int proveedorId = rs.getInt("proveedor_id");
                String estado = rs.getString("estado");
                double total = rs.getDouble("total");

                pedidosPendientes.add(new Pedido(pedidoId, fechaPedido, fechaEntrega, proveedorId, estado, total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidosPendientes;
    }
}
