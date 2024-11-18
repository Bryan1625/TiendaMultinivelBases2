package co.edu.uniquindio.tiendamultinivelbases2.servicios;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.DetalleVenta;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.Venta;

import java.util.List;

public interface VentaService {

    // Crear una nueva venta
    boolean crearVenta(Venta venta);

    // Obtener todos los detalles de una venta por su ID
    List<DetalleVenta> obtenerDetallesVentaPorId(int ventaId);

    // Obtener todas las ventas realizadas
    List<Venta> obtenerTodasLasVentas();

    // Actualizar el estado de una venta (Ej. "pendiente", "completada", etc.)
    boolean actualizarEstadoVenta(int ventaId, String estado);

    // Obtener las ventas pendientes
    List<Venta> obtenerVentasPendientes();

    // Obtener el total de ventas por un vendedor específico
    double obtenerTotalVentasPorVendedor(int vendedorId);

}
