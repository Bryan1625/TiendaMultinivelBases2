package co.edu.uniquindio.tiendamultinivelbases2.servicios;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.Pedido;

import java.util.List;

public interface PedidoService {

    // Crear un nuevo pedido
    boolean crearPedido(Pedido pedido);

    // Actualizar el estado de un pedido
    boolean actualizarEstado(int pedidoId, String nuevoEstado);

    // Obtener un pedido por su ID
    Pedido obtenerPedidoPorId(int pedidoId);

    // Obtener todos los pedidos pendientes
    List<Pedido> obtenerPedidosPendientes();

    // Obtener todos los pedidos
    List<Pedido> obtenerTodosLosPedidos();
}
