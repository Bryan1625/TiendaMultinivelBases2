package co.edu.uniquindio.tiendamultinivelbases2.modelo;

import java.sql.Date;

public class Cliente {
    private int clienteId;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private String telefono;
    private Date fechaNacimiento;
    private String estado;

    public Cliente() {
    }

    public Cliente(int clienteId, String nombre, String apellido, String direccion, String email, String telefono, Date fechaNacimiento, String estado) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public Cliente(int clienteId, String nombreCliente, String apellido, String email, String telefono) {
        this.clienteId = clienteId;
        this.nombre = nombreCliente;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

