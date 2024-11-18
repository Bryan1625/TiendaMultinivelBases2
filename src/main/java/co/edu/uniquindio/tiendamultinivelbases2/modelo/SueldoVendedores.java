package co.edu.uniquindio.tiendamultinivelbases2.modelo;

public class SueldoVendedores {
    private int sueldoId;
    private int vendedorId;
    private double sueldoBase;
    private double comisiones;
    private String fechaPago;
    private String nivel;
    private String posicionArbol;
    private String estado;

    // Constructor vacío
    public SueldoVendedores() {}

    // Getters y Setters
    public int getSueldoId() {
        return sueldoId;
    }

    public void setSueldoId(int sueldoId) {
        this.sueldoId = sueldoId;
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public double getComisiones() {
        return comisiones;
    }

    public void setComisiones(double comisiones) {
        this.comisiones = comisiones;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getPosicionArbol() {
        return posicionArbol;
    }

    public void setPosicionArbol(String posicionArbol) {
        this.posicionArbol = posicionArbol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
