package co.edu.uniquindio.tiendamultinivelbases2.servicios;

import co.edu.uniquindio.tiendamultinivelbases2.modelo.Cuenta;
import java.util.List;

public interface CuentaService{
    // Método para crear una cuenta
    boolean crearCuenta(Cuenta cuenta);

    // Método para actualizar el estado de la cuenta
    boolean actualizarEstado(int cuentaId, String nuevoEstado);

    // Método de login: retorna el tipo de usuario si es válido
    String login(String usuario, String contrasenia);

    // Método para obtener el tipo de usuario
    String obtenerTipoUsuario(int cuentaId);

    // Método para obtener una cuenta por ID
    Cuenta obtenerCuentaPorId(int cuentaId);

    // Método para obtener todas las cuentas
    List<Cuenta> obtenerTodasLasCuentas();
    boolean eliminarCuenta(int cuentaId);
}
