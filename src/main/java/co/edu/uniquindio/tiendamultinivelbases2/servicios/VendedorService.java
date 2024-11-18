package co.edu.uniquindio.tiendamultinivelbases2.servicios;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.Vendedor;

import java.util.List;

public interface VendedorService {

    // Crear un nuevo afiliado (vendedor)
    boolean crearAfiliado(Vendedor vendedor);

    // Actualizar estado de un vendedor (activo/inactivo)
    boolean actualizarEstadoVendedor(int vendedorId, String estado);

    // Obtener vendedor por ID
    Vendedor obtenerVendedorPorId(int vendedorId);

    // Obtener todos los vendedores
    List<Vendedor> obtenerTodosLosVendedores();

    // Desvincular un vendedor (actualizar su estado y jefe_id)
    boolean desvincularVendedor(int vendedorId);

}
