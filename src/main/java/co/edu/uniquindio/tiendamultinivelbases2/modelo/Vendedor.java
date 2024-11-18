package co.edu.uniquindio.tiendamultinivelbases2.modelo;

public class Vendedor {
    private int vendedorId;
    private String nombre;
    private String fechaNacimiento;
    private int jefeId;
    private String nivel;
    private String estado;

    public Vendedor() {
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getJefeId() {
        return jefeId;
    }

    public void setJefeId(int jefeId) {
        this.jefeId = jefeId;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

