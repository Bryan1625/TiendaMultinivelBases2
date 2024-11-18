package co.edu.uniquindio.tiendamultinivelbases2.modelo;

public class Nivel {
    private String nombre;
    private double comision;
    private double descuento;
    private int ventasMinimas;

    // Constructor vacío
    public Nivel() {}

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getVentasMinimas() {
        return ventasMinimas;
    }

    public void setVentasMinimas(int ventasMinimas) {
        this.ventasMinimas = ventasMinimas;
    }
}
