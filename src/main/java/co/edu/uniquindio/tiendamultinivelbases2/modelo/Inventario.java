package co.edu.uniquindio.tiendamultinivelbases2.modelo;

public class Inventario {
    private int libroId;
    private int existencia;

    // Constructor vacío
    public Inventario() {}

    public Inventario(int libroId, int existencia) {
        this.libroId = libroId;
        this.existencia = existencia;
    }

    // Getters y Setters
    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
}

