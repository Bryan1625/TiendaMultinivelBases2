package co.edu.uniquindio.tiendamultinivelbases2.dao;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.Cuenta;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {

    // Método para crear una nueva cuenta
    public boolean crearCuenta(Cuenta cuenta) {
        String sql = "INSERT INTO Cuenta (usuario, contrasenia, fecha_creacion, persona_id, estado, tipo_usuario) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cuenta.getUsuario());
            stmt.setString(2, cuenta.getContrasenia());
            stmt.setString(3, cuenta.getFechaCreacion());
            stmt.setInt(4, cuenta.getPersonaId());
            stmt.setString(5, cuenta.getEstado());
            stmt.setString(6, cuenta.getTipoUsuario());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para actualizar el estado de una cuenta
    public boolean actualizarEstado(int cuentaId, String nuevoEstado) {
        String sql = "UPDATE Cuenta SET estado = ? WHERE cuenta_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, cuentaId);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para realizar el login
    public Cuenta login(String usuario, String contrasenia) {
        Cuenta cuenta = new Cuenta();
        String sql = "SELECT tipo_usuario, persona_id FROM Cuenta WHERE usuario = ? AND contrasenia = ? AND estado = 'activo'";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasenia);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cuenta.setTipoUsuario(rs.getString("tipo_usuario"));
                cuenta.setPersonaId(Integer.parseInt(rs.getString("persona_id")));
                return cuenta;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no encuentra usuario o hay un error
    }

    // Método para obtener el tipo de usuario por cuenta_id
    public String obtenerTipoUsuario(int cuentaId) {
        String sql = "SELECT tipo_usuario FROM Cuenta WHERE cuenta_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cuentaId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("tipo_usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no encuentra el tipo de usuario
    }

    // Método para obtener una cuenta por ID
    public Cuenta obtenerCuentaPorId(int cuentaId) {
        String sql = "SELECT * FROM Cuenta WHERE cuenta_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cuentaId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String usuario = rs.getString("usuario");
                String contrasenia = rs.getString("contrasenia");
                String fechaCreacion = rs.getString("fecha_creacion");
                int personaId = rs.getInt("persona_id");
                String estado = rs.getString("estado");
                String tipoUsuario = rs.getString("tipo_usuario");

                return new Cuenta(usuario, contrasenia, fechaCreacion, personaId, estado, tipoUsuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para obtener todas las cuentas
    public List<Cuenta> obtenerTodasLasCuentas() {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM Cuenta";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int cuentaId = rs.getInt("cuenta_id");
                String usuario = rs.getString("usuario");
                String contrasenia = rs.getString("contrasenia");
                String fechaCreacion = rs.getString("fecha_creacion");
                int personaId = rs.getInt("persona_id");
                String estado = rs.getString("estado");
                String tipoUsuario = rs.getString("tipo_usuario");

                cuentas.add(new Cuenta(usuario, contrasenia, fechaCreacion, personaId, estado, tipoUsuario));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }
}
