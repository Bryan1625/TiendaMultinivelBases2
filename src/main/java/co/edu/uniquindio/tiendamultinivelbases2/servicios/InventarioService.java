package co.edu.uniquindio.tiendamultinivelbases2.servicios;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.Inventario;
import java.util.List;

public interface InventarioService {

    // Método para insertar un nuevo registro en el inventario
    boolean insertarInventario(int libroId, int cantidad);

    // Método para actualizar la cantidad de un libro en el inventario
    boolean actualizarCantidadInventario(int libroId, int cantidad);

    // Método para obtener todos los registros de inventario
    List<Inventario> obtenerTodosLosInventarios();

    // Método para obtener el inventario de un libro por su ID
    Inventario obtenerInventarioPorId(int libroId);

    // Método para eliminar un registro de inventario (en lugar de eliminar, se actualizaría la cantidad a 0)
    boolean eliminarInventario(int libroId);
}
