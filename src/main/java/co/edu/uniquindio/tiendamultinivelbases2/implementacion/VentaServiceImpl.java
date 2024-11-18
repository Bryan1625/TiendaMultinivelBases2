package co.edu.uniquindio.tiendamultinivelbases2.implementacion;

import co.edu.uniquindio.tiendamultinivelbases2.dao.DetalleVentaDAO;
import co.edu.uniquindio.tiendamultinivelbases2.dao.VentaDAO;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.DetalleVenta;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.Venta;
import co.edu.uniquindio.tiendamultinivelbases2.servicios.VentaService;

import java.util.List;

public class VentaServiceImpl implements VentaService {

    private VentaDAO ventaDAO;
    private DetalleVentaDAO dvDAO;
    public VentaServiceImpl() {
        this.ventaDAO = new VentaDAO();
    }

    @Override
    public boolean crearVenta(Venta venta) {
        return ventaDAO.insertarVenta(venta);
    }

    @Override
    public List<DetalleVenta> obtenerDetallesVentaPorId(int ventaId) {
        return dvDAO.obtenerDetallesVentaPorId(ventaId);
    }

    @Override
    public List<Venta> obtenerTodasLasVentas() {
        return ventaDAO.obtenerTodasLasVentas();
    }

    @Override
    public boolean actualizarEstadoVenta(int ventaId, String estado) {
        return ventaDAO.actualizarEstadoVenta(ventaId, estado);
    }

    @Override
    public List<Venta> obtenerVentasPendientes() {
        return ventaDAO.obtenerVentasPendientes();
    }

    @Override
    public double obtenerTotalVentasPorVendedor(int vendedorId) {
        return ventaDAO.obtenerTotalVentasPorVendedor(vendedorId);
    }

}