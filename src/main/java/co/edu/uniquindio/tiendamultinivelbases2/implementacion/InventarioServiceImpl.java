package co.edu.uniquindio.tiendamultinivelbases2.implementacion;

import co.edu.uniquindio.tiendamultinivelbases2.dao.InventarioDAO;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.Inventario;
import co.edu.uniquindio.tiendamultinivelbases2.servicios.InventarioService;

import java.util.List;

public class InventarioServiceImpl implements InventarioService {

    private InventarioDAO inventarioDAO;

    public InventarioServiceImpl() {
        this.inventarioDAO = new InventarioDAO();
    }

    @Override
    public boolean insertarInventario(int libroId, int cantidad) {
        return inventarioDAO.insertarInventario(libroId, cantidad);
    }

    @Override
    public boolean actualizarCantidadInventario(int libroId, int cantidad) {
        Inventario inventario = new Inventario(libroId,cantidad);
        return inventarioDAO.actualizarInventario(inventario);
    }

    @Override
    public Inventario obtenerInventarioPorId(int libroId) {
        return inventarioDAO.obtenerInventarioPorId(libroId);
    }

    @Override
    public boolean eliminarInventario(int libroId) {
        return inventarioDAO.eliminarInventario(libroId);
    }

    @Override
    public List<Inventario> obtenerTodosLosInventarios() {
        return inventarioDAO.obtenerTodosLosInventarios();
    }

}
