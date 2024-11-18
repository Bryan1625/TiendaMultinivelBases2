package co.edu.uniquindio.tiendamultinivelbases2.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.DataBaseConnection;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.Vendedor;

public class VendedorDAO {

    // Insertar nuevo vendedor (afiliado)
    public boolean insertarAfiliado(int vendedorId, String nombre, Date fechaNacimiento, int jefeId, String estado) {
        String sql = "{call insertar_afiliado(?, ?, ?, ?, ?)}";  // Llamada al procedimiento almacenado

        try (Connection conn = DataBaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, vendedorId);
            stmt.setString(2, nombre);
            stmt.setDate(3, fechaNacimiento);
            stmt.setInt(4, jefeId);
            stmt.setString(5, estado);

            stmt.execute();  // Ejecutar el procedimiento
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Actualizar el estado de un vendedor
    public boolean actualizarEstadoVendedor(int vendedorId, String estado) {
        String sql = "UPDATE Vendedor SET estado = ? WHERE vendedor_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estado);
            stmt.setInt(2, vendedorId);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Obtener todos los vendedores
    public List<Vendedor> obtenerTodosLosVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM Vendedor";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int vendedorId = rs.getInt("vendedor_id");
                String nombre = rs.getString("nombre");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                int jefeId = rs.getInt("jefe_id");
                String nivel = rs.getString("nivel");
                String estado = rs.getString("estado");

                Vendedor vendedor = new Vendedor(vendedorId, nombre, fechaNacimiento, jefeId, nivel, estado);
                vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendedores;
    }

    // Obtener un vendedor por ID
    public Vendedor obtenerVendedorPorId(int vendedorId) {
        Vendedor vendedor = null;
        String sql = "SELECT * FROM Vendedor WHERE vendedor_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vendedorId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                int jefeId = rs.getInt("jefe_id");
                String nivel = rs.getString("nivel");
                String estado = rs.getString("estado");

                vendedor = new Vendedor(vendedorId, nombre, fechaNacimiento, jefeId, nivel, estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendedor;
    }

    // Desvincular un vendedor
    public boolean desvincularVendedor(int vendedorId) {
        String sql = "{call desvincular_vendedor(?)}";  // Llamada al procedimiento almacenado

        try (Connection conn = DataBaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, vendedorId);
            stmt.execute();  // Ejecutar el procedimiento
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
