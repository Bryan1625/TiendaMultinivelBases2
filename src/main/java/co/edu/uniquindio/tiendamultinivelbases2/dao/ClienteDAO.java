package co.edu.uniquindio.tiendamultinivelbases2.dao;


import co.edu.uniquindio.tiendamultinivelbases2.modelo.Cliente;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Método para obtener todos los clientes
    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int clienteId = rs.getInt("cliente_id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                String estado = rs.getString("estado");

                clientes.add(new Cliente(clienteId, nombre, apellido, direccion, email, telefono, fechaNacimiento, estado));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<Cliente> obtenerClientesActivos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente where estado = 'activo'";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int clienteId = rs.getInt("cliente_id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                String estado = rs.getString("estado");

                clientes.add(new Cliente(clienteId, nombre, apellido, direccion, email, telefono, fechaNacimiento, estado));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Método para obtener un cliente por su ID
    public Cliente obtenerClientePorId(int clienteId) {
        String sql = "SELECT * FROM Cliente WHERE cliente_id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                String estado = rs.getString("estado");

                return new Cliente(clienteId, nombre, apellido, direccion, email, telefono, fechaNacimiento, estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para insertar un nuevo cliente
    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (cliente_id, nombre, apellido, direccion, email, telefono, fecha_nacimiento, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cliente.getClienteId());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getTelefono());
            stmt.setDate(7, cliente.getFechaNacimiento());
            stmt.setString(8, cliente.getEstado());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para actualizar los datos de un cliente
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, apellido = ?, direccion = ?, email = ?, telefono = ?, fecha_nacimiento = ?, estado = ? WHERE cliente_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefono());
            stmt.setDate(6, cliente.getFechaNacimiento());
            stmt.setString(7, cliente.getEstado());
            stmt.setInt(8, cliente.getClienteId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarEstadoCliente(int clienteId, String nuevoEstado) {
        String sql = "UPDATE Cliente SET estado = ? WHERE cliente_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, clienteId);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int obtenerCantidadVentasPorCliente(int clienteId) {
        String sql = "SELECT COUNT(DISTINCT venta_id) AS cantidad_ventas FROM vista_ventas WHERE cliente_id = ?";
        int cantidadVentas = 0;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cantidadVentas = rs.getInt("cantidad_ventas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cantidadVentas;
    }

    public List<Cliente> buscarClientesPorNombre(String nombre) {
        List<Cliente> clientesEncontrados = new ArrayList<>();
        String sql = "SELECT cliente_id, nombre, apellido, email, telefono FROM cliente WHERE nombre like ? and estado = 'activo'";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Usar '%' para buscar por coincidencias parciales
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int clienteId = rs.getInt("cliente_id");
                String nombreCliente = rs.getString("nombre");
                String apellidoCliente = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");

                Cliente cliente = new Cliente(clienteId, nombreCliente, apellidoCliente,  email, telefono);
                clientesEncontrados.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientesEncontrados;
    }
}
