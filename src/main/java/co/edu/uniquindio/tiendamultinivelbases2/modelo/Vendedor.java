package co.edu.uniquindio.tiendamultinivelbases2.modelo;

import java.sql.Date;

public class Vendedor {
    private int vendedorId;
    private String nombre;
    private Date fechaNacimiento;
    private int jefeId;
    private String nivel;
    private String estado;

    public Vendedor() {
    }

    public Vendedor(int vendedorId, String nombre, Date fechaNacimiento, int jefeId, String nivel, String estado) {
        this.vendedorId = vendedorId;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.jefeId = jefeId;
        this.nivel = nivel;
        this.estado = estado;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
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

