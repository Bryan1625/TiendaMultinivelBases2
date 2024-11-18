package co.edu.uniquindio.tiendamultinivelbases2.modelo;

public class DetallePedido {
    private int pedidoId;
    private int libroId;
    private int cantidad;
    private double subtotal;

    // Constructor vacío
    public DetallePedido() {}

    public DetallePedido(int pedidoId, int libroId, int cantidad, double subtotal) {
        this.pedidoId = pedidoId;
        this.libroId = libroId;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    // Getters y Setters
    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
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
