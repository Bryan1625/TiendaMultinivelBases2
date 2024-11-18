package co.edu.uniquindio.tiendamultinivelbases2.implementacion;

import co.edu.uniquindio.tiendamultinivelbases2.dao.CuentaDAO;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.Cuenta;
import co.edu.uniquindio.tiendamultinivelbases2.servicios.CuentaService;

import java.util.List;

public class CuentaServiceImpl implements CuentaService {

    private CuentaDAO cuentaDAO;

    public CuentaServiceImpl() {
        this.cuentaDAO = new CuentaDAO();
    }

    @Override
    public boolean crearCuenta(Cuenta cuenta) {
        return cuentaDAO.crearCuenta(cuenta);
    }

    @Override
    public boolean actualizarEstado(int cuentaId, String estado) {
        return cuentaDAO.actualizarEstado(cuentaId, estado);
    }

    @Override
    public boolean eliminarCuenta(int cuentaId) {
        return cuentaDAO.actualizarEstado(cuentaId, "inactivo");
    }

    @Override
    public Cuenta obtenerCuentaPorId(int cuentaId) {
        return cuentaDAO.obtenerCuentaPorId(cuentaId);
    }

    @Override
    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaDAO.obtenerTodasLasCuentas();
    }

    @Override
    public String login(String usuario, String contrasenia) {
        return cuentaDAO.login(usuario, contrasenia);
    }

    @Override
    public String obtenerTipoUsuario(int cuentaId) {
        return cuentaDAO.obtenerTipoUsuario(cuentaId);
    }


}