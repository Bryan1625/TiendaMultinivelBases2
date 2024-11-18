package co.edu.uniquindio.tiendamultinivelbases2.implementacion;


import co.edu.uniquindio.tiendamultinivelbases2.dao.VendedorDAO;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.Vendedor;
import co.edu.uniquindio.tiendamultinivelbases2.servicios.VendedorService;

import java.util.List;

public class VendedorServiceImpl implements VendedorService {

    private VendedorDAO vendedorDAO;

    public VendedorServiceImpl() {
        this.vendedorDAO = new VendedorDAO();
    }

    @Override
    public boolean crearAfiliado(Vendedor vendedor) {
        return vendedorDAO.insertarAfiliado(vendedor.getVendedorId(),vendedor.getNombre(),vendedor.getFechaNacimiento(), vendedor.getJefeId(), vendedor.getEstado());
    }

    @Override
    public boolean actualizarEstadoVendedor(int vendedorId, String estado) {
        return vendedorDAO.actualizarEstadoVendedor(vendedorId, estado);
    }

    @Override
    public Vendedor obtenerVendedorPorId(int vendedorId) {
        return vendedorDAO.obtenerVendedorPorId(vendedorId);
    }

    @Override
    public List<Vendedor> obtenerTodosLosVendedores() {
        return vendedorDAO.obtenerTodosLosVendedores();
    }

    @Override
    public boolean desvincularVendedor(int vendedorId) {
        return vendedorDAO.desvincularVendedor(vendedorId);
    }
}
