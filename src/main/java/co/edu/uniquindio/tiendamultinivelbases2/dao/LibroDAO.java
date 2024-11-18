package co.edu.uniquindio.tiendamultinivelbases2.dao;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.Libro;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    // Método para obtener todos los libros
    public List<Libro> obtenerTodosLosLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libro";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int libroId = rs.getInt("libro_id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                double precioVenta = rs.getDouble("precioventa");
                String categoria = rs.getString("categoria");
                double precioCompra = rs.getDouble("preciocompra");

                libros.add(new Libro(libroId, titulo, autor, precioVenta, categoria, precioCompra));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    // Método para obtener un libro por su ID
    public Libro obtenerLibroPorId(int libroId) {
        String sql = "SELECT * FROM libro WHERE libro_id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, libroId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                double precioVenta = rs.getDouble("precioventa");
                String categoria = rs.getString("categoria");
                double precioCompra = rs.getDouble("preciocompra");

                return new Libro(libroId, titulo, autor, precioVenta, categoria, precioCompra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para insertar un libro
    public boolean insertarLibro(Libro libro) {
        String sql = "{CALL insertar_libro(?, ?, ?, ?, ?, ?)}";  // Llamada al procedimiento almacenado

        try (Connection conn = DataBaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Establecer los valores para los parámetros del procedimiento
            stmt.setInt(1, libro.getLibroId());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getAutor());
            stmt.setDouble(4, libro.getPrecioVenta());
            stmt.setString(5, libro.getCategoria());
            stmt.setDouble(6, libro.getPrecioCompra());

            // Ejecutar el procedimiento
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para actualizar un libro
    public boolean actualizarLibro(Libro libro) {
        String sql = "UPDATE libro SET titulo = ?, autor = ?, precioventa = ?, categoria = ?, preciocompra = ? WHERE libro_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setDouble(3, libro.getPrecioVenta());
            stmt.setString(4, libro.getCategoria());
            stmt.setDouble(5, libro.getPrecioCompra());
            stmt.setInt(6, libro.getLibroId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para eliminar un libro
    public boolean eliminarLibro(int libroId) {
        String sql = "DELETE FROM libro WHERE libro_id = ?";

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
