package co.edu.uniquindio.tiendamultinivelbases2;

import co.edu.uniquindio.tiendamultinivelbases2.dao.*;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class VendedorController {
    private Cuenta cuenta;
    @FXML
    public TextField txtIdVenta;
    // Tab Ventas
    @FXML
    private TableView<DetalleVenta> tblVentas;
    @FXML
    private TableColumn<?, ?> colIdVenta;
    @FXML
    private TableColumn<?, ?> colIdClienteVenta;
    @FXML
    private TableColumn<?, ?> colIdLibroVenta;
    @FXML
    private TableColumn<?, ?> colCantidadVenta;
    @FXML
    private TableColumn<?, ?> colTotalVenta;
    @FXML
    private TextField txtLibroVenta;
    @FXML
    private TextField txtCantidadVenta;
    @FXML
    private TextField txtClienteVenta;
    @FXML
    private Button btnCrearVenta;

    // Tab Clientes
    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<?, ?> colIdCliente;
    @FXML
    private TableColumn<?, ?> colNombreCliente;
    @FXML
    private TableColumn<?, ?> colApellidoCliente;
    @FXML
    private TableColumn<?, ?> colTelefonoCliente;
    @FXML
    private TextField txtIdCliente;
    @FXML
    private TextField txtNombreCiente;
    @FXML
    private TextField txtCantidadCompras;
    @FXML
    private Button btnBuscarClienteNombre;
    @FXML
    private Button btnBuscarClienteId;

    // Tab Inventario
    @FXML
    private TableView<Inventario> tblInventario;
    @FXML
    private TableColumn<?, ?> colIdLibroInventario;
    @FXML
    private TableColumn<?, ?> colExistenciaInventario;

    // Tab Afiliados
    @FXML
    private TableView<Vendedor> tblAfiliados;
    @FXML
    private TableColumn<?, ?> colIdVendedorAfiliado;
    @FXML
    private TableColumn<?, ?> colNombreVendedorAfiliado;
    @FXML
    private TableColumn<?, ?> colFechaAfiliado;
    @FXML
    private TableColumn<?, ?> colJefeAfiliado;
    @FXML
    private TextField txtIdVendedorAfiliados;
    @FXML
    private TextField txtNombreVendedorAfiliados;
    @FXML
    private DatePicker dpFechaNacimientoAfiliados;
    @FXML
    private Button btnCrearAfilidado;

    // Tab Cuenta
    @FXML
    private TextField txtIdVendedor;
    @FXML
    private TextField txtNombreVendedor;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private Button btnActualizarDatos;
    @FXML
    private Button btnDarseDeBaja;

    private ClienteDAO clienteDAO;
    private VendedorDAO vendedorDAO;
    private InventarioDAO inventarioDAO;
    private LibroDAO libroDAO;
    private VentaDAO ventaDAO;
    private DetalleVentaDAO detalleVentaDAO;

    // Listas de datos
    private List<DetalleVenta> ventas;
    private List<Cliente> clientes;
    private List<Inventario> inventarios;
    private List<Vendedor> vendedores;

    // Listas observables para las tablas
    private ObservableList<DetalleVenta> observableVentas;
    private ObservableList<Cliente> observableClientes;
    private ObservableList<Inventario> observableInventarios;
    private ObservableList<Vendedor> observableVendedores;
    private Vendedor vendedor;


    public void initialize() {
        // Configuración para la tabla de Ventas
        colIdVenta.setCellValueFactory(new PropertyValueFactory<>("ventaId"));
        colIdClienteVenta.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        colIdLibroVenta.setCellValueFactory(new PropertyValueFactory<>("libroId"));
        colCantidadVenta.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colTotalVenta.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        // Configuración para la tabla de Clientes
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoCliente.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTelefonoCliente.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        // Configuración para la tabla de Inventario
        colIdLibroInventario.setCellValueFactory(new PropertyValueFactory<>("libroId"));
        colExistenciaInventario.setCellValueFactory(new PropertyValueFactory<>("existencia"));

        // Configuración para la tabla de Afiliados (Vendedores)
        colIdVendedorAfiliado.setCellValueFactory(new PropertyValueFactory<>("vendedorId"));
        colNombreVendedorAfiliado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFechaAfiliado.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colJefeAfiliado.setCellValueFactory(new PropertyValueFactory<>("jefeId"));

        inicializarDatos();

        // Enlazar las listas de datos a las tablas
        tblVentas.setItems(observableVentas);
        tblClientes.setItems(observableClientes);
        tblInventario.setItems(observableInventarios);
        tblAfiliados.setItems(observableVendedores);

        clienteDAO = new ClienteDAO();
        vendedorDAO = new VendedorDAO();
        inventarioDAO = new InventarioDAO();
        ventaDAO = new VentaDAO();
        detalleVentaDAO = new DetalleVentaDAO();
        libroDAO = new LibroDAO();

    }

    public void establecerVendedor(){
        vendedor = vendedorDAO.obtenerVendedorPorId(cuenta.getPersonaId());
        inicializarCuenta();
    }

    public void inicializarCuenta(){
        txtIdVendedor.setText(""+vendedor.getVendedorId());
        txtNombreVendedor.setText(vendedor.getNombre());
        dpFechaNacimiento.setValue(vendedor.getFechaNacimiento().toLocalDate());
    }

    private void inicializarDatos() {
        // Limpiar las listas observables antes de agregar nuevos datos
        if(observableClientes != null) {
            observableVentas.clear();
            observableClientes.clear();
            observableInventarios.clear();
            observableVendedores.clear();
        }else{
            observableVentas = FXCollections.observableArrayList();
            observableClientes = FXCollections.observableArrayList();
            observableInventarios = FXCollections.observableArrayList();
            observableVendedores = FXCollections.observableArrayList();
        }

        tblVentas.getItems().clear();
        tblClientes.getItems().clear();
        tblInventario.getItems().clear();
        tblAfiliados.getItems().clear();

        // Crear instancias de los DAOs
        DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        InventarioDAO inventarioDAO = new InventarioDAO();
        VendedorDAO vendedorDAO = new VendedorDAO();

        // Obtener los datos usando los métodos de los DAOs
        // Obtener ventas desde el DAO
        List<DetalleVenta> listaVentas = detalleVentaDAO.obtenerTodosLosDetallesVenta();
        observableVentas.addAll(listaVentas);

        // Obtener clientes desde el DAO
        List<Cliente> listaClientes = clienteDAO.obtenerTodosLosClientes();
        observableClientes.addAll(listaClientes);

        // Obtener inventarios desde el DAO
        List<Inventario> listaInventarios = inventarioDAO.obtenerTodosLosInventarios();
        observableInventarios.addAll(listaInventarios);

        // Obtener vendedores desde el DAO
        List<Vendedor> listaVendedores = vendedorDAO.obtenerTodosLosVendedores();
        observableVendedores.addAll(listaVendedores);

        // Actualizar las TableViews con las listas observables
        tblVentas.setItems(observableVentas);
        tblClientes.setItems(observableClientes);
        tblInventario.setItems(observableInventarios);
        tblAfiliados.setItems(observableVendedores);

    }




    // Métodos onAction
    @FXML
    private void crearVenta(ActionEvent event) {
        // Obtener los valores de los campos de texto
        String strIdVenta = txtIdVenta.getText();
        String strIdLibro = txtLibroVenta.getText();
        String strCantidad = txtCantidadVenta.getText();
        String strIdCliente = txtClienteVenta.getText();

        // Validación de campos: asegurarse de que los campos no estén vacíos
        if (strIdVenta.isEmpty() || strIdLibro.isEmpty() || strCantidad.isEmpty() || strIdCliente.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos antes de continuar.");
        }else {

            // Intentar parsear los valores
            try {
                int idVenta = Integer.parseInt(strIdVenta);
                int idLibro = Integer.parseInt(strIdLibro);
                int cantidad = Integer.parseInt(strCantidad);
                int idCliente = Integer.parseInt(strIdCliente);

                // Validar que la cantidad sea positiva
                if (cantidad <= 0) {
                    mostrarAlerta("Cantidad inválida", "La cantidad debe ser un número positivo.");
                    return;
                }

                // Verificar si la venta ya existe
                if (ventaDAO.obtenerVentaPorId(idVenta) == null) {
                    Venta venta = new Venta(idVenta, Date.valueOf(LocalDate.now()), "Pendiente", 0);
                    ventaDAO.insertarVenta(venta);
                }

                // Obtener el precio de venta del libro
                double precioventa = libroDAO.obtenerPrecioVentaPorId(idLibro);
                if (precioventa > 0) {
                    // Crear el detalle de la venta y agregarlo
                    DetalleVenta dv = new DetalleVenta(idVenta, idCliente, cuenta.getPersonaId(), idLibro, cantidad, cantidad * precioventa);
                    detalleVentaDAO.insertarDetalleVenta(dv);
                } else {
                    mostrarAlerta("Precio no disponible", "No se pudo obtener el precio de venta del libro.");
                }

            } catch (NumberFormatException e) {
                mostrarAlerta("Datos inválidos", "Por favor ingrese valores numéricos válidos en los campos correspondientes.");
            }

            // Volver a cargar los datos después de la operación
            inicializarDatos();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void buscarClientePorNombre(ActionEvent event) {
        String nombre = txtNombreCiente.getText();

        if (nombre != null && !nombre.isEmpty()) {
            List<Cliente> clientes = clienteDAO.buscarClientesPorNombre(nombre);

            if (clientes != null) {
                observableClientes.clear();
                observableClientes.addAll(clientes);
            }
        } else {
            inicializarDatos();
        }
    }


    @FXML
    private void buscarClientePorId(ActionEvent event) {
        String idClienteStr = txtIdCliente.getText();

        // Validar que el ID no esté vacío
        if (idClienteStr.isEmpty()) {
            inicializarDatos();
        }else {

            try {
                // Convertir el ID a entero
                int idCliente = Integer.parseInt(idClienteStr);

                // Obtener el cliente desde el DAO
                Cliente cliente = clienteDAO.obtenerClientePorId(idCliente);

                // Limpiar la lista observable antes de agregar el cliente
                observableClientes.clear();

                if (cliente != null) {
                    // Agregar el cliente encontrado a la lista observable
                    observableClientes.add(cliente);
                } else {
                    // Si no se encuentra el cliente, mostrar una alerta
                    mostrarAlerta("Cliente no encontrado", "No se encontró un cliente con el ID ingresado.");
                    // Llamar al método inicializarDatos para recargar todos los clientes si no se encuentra
                    inicializarDatos();
                }

            } catch (NumberFormatException e) {
                // En caso de que el ID no sea un número válido
                mostrarAlerta("ID inválido", "Por favor ingrese un ID de cliente válido.");
                // Llamar al método inicializarDatos para recargar la lista completa en caso de error
                inicializarDatos();
            }
        }
    }

    @FXML
    private void crearAfiliado(ActionEvent event) {
        String idVendedorStr = txtIdVendedorAfiliados.getText();
        String nombre = txtNombreVendedorAfiliados.getText();
        String fechaNacimientoStr = dpFechaNacimientoAfiliados.getValue() != null ? dpFechaNacimientoAfiliados.getValue().toString() : null;

        // Validar que todos los campos estén diligenciados
        if (idVendedorStr.isEmpty() || nombre.isEmpty() || fechaNacimientoStr == null) {
            mostrarAlerta("Campos incompletos", "Por favor complete todos los campos.");
        }else {
            try {
                // Convertir el ID de vendedor a entero
                int idVendedor = Integer.parseInt(idVendedorStr);

                // Convertir la fecha de nacimiento de String a Date
                Date fechaNacimiento = Date.valueOf(fechaNacimientoStr);

                // Validar que el ID de vendedor no exista ya
                if (vendedorDAO.obtenerVendedorPorId(idVendedor) != null) {
                    mostrarAlerta("ID de vendedor existente", "El ID de vendedor ya está registrado.");
                    return;
                }


                int jefeId = vendedor.getVendedorId();
                String estado = "Activo";

                // Llamar al método del DAO para insertar el nuevo afiliado
                boolean exito = vendedorDAO.insertarAfiliado(idVendedor, nombre, fechaNacimiento, jefeId, estado);

                if (exito) {
                    // Si la inserción fue exitosa, mostrar mensaje de éxito
                    mostrarAlerta("Afiliado creado", "El afiliado ha sido creado exitosamente.");
                    inicializarDatos(); // Recargar la lista de afiliados
                } else {
                    // Si la inserción falló, mostrar mensaje de error
                    mostrarAlerta("Error", "Hubo un problema al crear el afiliado.");
                }

            } catch (NumberFormatException e) {
                // En caso de que el ID de vendedor no sea un número válido
                mostrarAlerta("ID inválido", "Por favor ingrese un ID de vendedor válido.");
            } catch (Exception e) {
                // Capturar cualquier otra excepción
                mostrarAlerta("Error", "Ocurrió un error inesperado.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void actualizarDatos(ActionEvent event) {
        String idVendedorStr = txtIdVendedor.getText();
        String nombre = txtNombreVendedor.getText();
        String fechaNacimientoStr = dpFechaNacimiento.getValue() != null ? dpFechaNacimiento.getValue().toString() : null;

        // Validar que todos los campos estén diligenciados
        if (idVendedorStr.isEmpty() || nombre.isEmpty() || fechaNacimientoStr == null) {
            mostrarAlerta("Campos incompletos", "Por favor complete todos los campos.");
            return; // Detener la ejecución si algún campo está vacío
        }

        try {
            // Convertir el ID de vendedor a entero
            int idVendedor = Integer.parseInt(idVendedorStr);

            // Convertir la fecha de nacimiento de String a Date
            Date fechaNacimiento = Date.valueOf(fechaNacimientoStr);

            // Validar que el vendedor exista en la base de datos antes de actualizar
            Vendedor vendedorExistente = vendedorDAO.obtenerVendedorPorId(idVendedor);
            if (vendedorExistente == null) {
                mostrarAlerta("Vendedor no encontrado", "No se encontró un vendedor con el ID proporcionado.");
                return;
            }

            // Llamar al método del DAO para actualizar los datos del vendedor
            boolean exito = vendedorDAO.actualizarVendedor(idVendedor, nombre, fechaNacimiento);

            if (exito) {
                // Si la actualización fue exitosa, mostrar mensaje de éxito
                mostrarAlerta("Datos actualizados", "Los datos del vendedor han sido actualizados exitosamente.");
                inicializarDatos(); // Recargar la lista de vendedores
            } else {
                // Si la actualización falló, mostrar mensaje de error
                mostrarAlerta("Error", "Hubo un problema al actualizar los datos del vendedor.");
            }

        } catch (NumberFormatException e) {
            // En caso de que el ID de vendedor no sea un número válido
            mostrarAlerta("ID inválido", "Por favor ingrese un ID de vendedor válido.");
        } catch (Exception e) {
            // Capturar cualquier otra excepción
            mostrarAlerta("Error", "Ocurrió un error inesperado.");
            e.printStackTrace();
        }
    }


    @FXML
    private void darseDeBaja(ActionEvent event) {
        String idVendedor = txtIdVendedor.getText();


        if (idVendedor == null || idVendedor.trim().isEmpty()) {
            mostrarAlerta("Campo vacío", "Por favor ingrese un ID de vendedor.");
            return;
        }


        try {
            int id = Integer.parseInt(idVendedor);
            vendedorDAO.desvincularVendedor(vendedor.getVendedorId());

        } catch (NumberFormatException e) {
            mostrarAlerta("ID inválido", "El ID del vendedor debe ser un número válido.");
        }
    }


    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
