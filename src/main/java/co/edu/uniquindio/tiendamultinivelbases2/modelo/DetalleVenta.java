package co.edu.uniquindio.tiendamultinivelbases2.modelo;

public class DetalleVenta {
    private int ventaId;
    private int clienteId;
    private int vendedorId;
    private int libroId;
    private int cantidad;
    private double subtotal;

    // Constructor vacío
    public DetalleVenta() {}

    public DetalleVenta(int ventaId, int clienteId, int vendedorId, int libroId, int cantidad, double subtotal) {
        this.ventaId = ventaId;
        this.clienteId = clienteId;
        this.vendedorId = vendedorId;
        this.libroId = libroId;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    // Getters y Setters
    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
