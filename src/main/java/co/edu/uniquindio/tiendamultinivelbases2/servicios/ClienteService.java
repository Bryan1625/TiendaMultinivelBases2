package co.edu.uniquindio.tiendamultinivelbases2.servicios;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.Cliente;
import java.util.List;

public interface ClienteService {

    // Método para insertar un nuevo cliente
    boolean insertarCliente(Cliente cliente);
    boolean actualizarCliente(Cliente cliente);

    // Método para obtener todos los clientes
    List<Cliente> obtenerTodosLosClientes();

    // Método para obtener un cliente por su ID
    Cliente obtenerClientePorId(int clienteId);

    // Método para obtener todos los clientes con estado activo
    List<Cliente> obtenerClientesActivos();

    // Método para eliminar un cliente (en lugar de eliminar, se actualiza su estado)
    boolean eliminarCliente(int clienteId);
}
