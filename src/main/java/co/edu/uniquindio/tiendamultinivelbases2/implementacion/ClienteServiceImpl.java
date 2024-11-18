package co.edu.uniquindio.tiendamultinivelbases2.implementacion;

import co.edu.uniquindio.tiendamultinivelbases2.dao.ClienteDAO;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.Cliente;
import co.edu.uniquindio.tiendamultinivelbases2.servicios.ClienteService;

import java.util.List;

public class ClienteServiceImpl implements ClienteService {

    private ClienteDAO clienteDAO;

    public ClienteServiceImpl() {
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public boolean insertarCliente(Cliente cliente) {
        return clienteDAO.insertarCliente(cliente);
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        return clienteDAO.actualizarCliente(cliente);
    }

    @Override
    public boolean eliminarCliente(int clienteId) {
        return clienteDAO.actualizarEstadoCliente(clienteId, "inactivo");
    }

    @Override
    public Cliente obtenerClientePorId(int clienteId) {
        return clienteDAO.obtenerClientePorId(clienteId);
    }

    @Override
    public List<Cliente> obtenerClientesActivos() {
        return null;
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteDAO.obtenerTodosLosClientes();
    }
}
