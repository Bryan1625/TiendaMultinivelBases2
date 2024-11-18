package co.edu.uniquindio.tiendamultinivelbases2.modelo;

import java.sql.Date;

public class Venta {
    private int ventaId;
    private Date fecha;
    private String estado;
    private double total;

    // Constructor vacío
    public Venta() {}

    public Venta(int ventaId, Date fecha, String estado, double total) {
        this.ventaId = ventaId;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    // Getters y Setters
    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

