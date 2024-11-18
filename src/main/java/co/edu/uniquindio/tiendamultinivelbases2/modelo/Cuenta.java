package co.edu.uniquindio.tiendamultinivelbases2.modelo;

public class Cuenta {
    private String usuario;
    private String contrasenia;
    private String fechaCreacion;
    private int personaId;
    private String estado;
    private String tipoUsuario;


    public Cuenta() {
    }

    public Cuenta(String usuario, String contrasenia, String fechaCreacion, int personaId, String estado, String tipoUsuario) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.fechaCreacion = fechaCreacion;
        this.personaId = personaId;
        this.estado = estado;
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

