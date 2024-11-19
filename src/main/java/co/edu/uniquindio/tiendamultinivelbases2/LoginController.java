package co.edu.uniquindio.tiendamultinivelbases2;

import co.edu.uniquindio.tiendamultinivelbases2.dao.CuentaDAO;
import co.edu.uniquindio.tiendamultinivelbases2.modelo.Cuenta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    Cuenta cuenta;
    @FXML
    public TextField txtUsuario;
    @FXML
    public PasswordField txtContrasenia;
    @FXML
    public Text txtLog;
    private CuentaDAO cuentaDAO;

    public void login(ActionEvent actionEvent) {
        cuentaDAO = new CuentaDAO();
        cuenta = cuentaDAO.login(txtUsuario.getText(), txtContrasenia.getText());

        if (cuenta != null) {
            switch (cuenta.getTipoUsuario()) {
                case "admin":
                    admin();
                    break;
                case "vendedor":
                    vendedor();
                    break;
                case "cliente":
                    cliente();
                    break;
                default:
                    // Alerta para tipo de usuario incorrecto
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error de autenticación");
                    alert.setHeaderText("Tipo de usuario desconocido");
                    alert.setContentText("El tipo de usuario '" + cuenta.getTipoUsuario() + "' no es válido. Contacte al administrador.");
                    alert.showAndWait();
                    break;
            }
        } else {
            // Mensaje de error para usuario o contraseña incorrectos
            txtLog.setText("Usuario o contraseña incorrectos");
        }
    }


    public void cliente(){

    }

    public void vendedor() {
        try{
            // Cargar la vista FXML para el vendedor

        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("vendedor-view.fxml"));
        Parent root = fxmlLoader.load();
        VendedorController vendedorController = fxmlLoader.getController();
        vendedorController.setCuenta(cuenta);
        vendedorController.establecerVendedor();
        // Crear una nueva escena con el contenido cargado
        Scene scene = new Scene(root, 600, 400);

        // Crear un nuevo Stage (ventana)
        Stage newStage = new Stage();
        newStage.setTitle("Vendedor - Vista");  // Establecer título de la nueva ventana

        // Asignar la nueva escena al nuevo Stage
        newStage.setScene(scene);

        // Mostrar la nueva ventana
        newStage.show();

        }catch(IOException e){
            e.printStackTrace();
        }

    }



    public void admin(){

    }
}