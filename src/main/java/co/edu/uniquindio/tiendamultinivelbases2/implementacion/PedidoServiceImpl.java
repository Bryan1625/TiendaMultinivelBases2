package co.edu.uniquindio.tiendamultinivelbases2.implementacion;

import co.edu.uniquindio.tiendamultinivelbases2.dao.PedidoDAO;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.Pedido;
import co.edu.uniquindio.tiendamultinivelbases2.servicios.PedidoService;

import java.util.List;

public class PedidoServiceImpl implements PedidoService {

    private PedidoDAO pedidoDAO;

    public PedidoServiceImpl() {
        this.pedidoDAO = new PedidoDAO();
    }

    @Override
    public boolean crearPedido(Pedido pedido) {
        return pedidoDAO.insertarPedido(pedido);
    }

    @Override
    public boolean actualizarEstado(int pedidoId, String estado) {
        return pedidoDAO.actualizarEstadoPedido(pedidoId, estado);
    }

    @Override
    public Pedido obtenerPedidoPorId(int pedidoId) {
        return pedidoDAO.obtenerPedidoPorId(pedidoId);
    }

    @Override
    public List<Pedido> obtenerPedidosPendientes() {
        return pedidoDAO.obtenerPedidosPendientes();
    }

    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoDAO.obtenerTodosLosPedidos();
    }
}