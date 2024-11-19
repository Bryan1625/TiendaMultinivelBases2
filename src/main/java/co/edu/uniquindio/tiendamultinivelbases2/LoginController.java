package co.edu.uniquindio.tiendamultinivelbases2;

import co.edu.uniquindio.tiendamultinivelbases2.dao.CuentaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {
    @FXML
    public TextField txtUsuario;
    @FXML
    public PasswordField txtContrasenia;
    @FXML
    public Text txtLog;
    private CuentaDAO cuentaDAO;
    @FXML
    private Label welcomeText;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void login(ActionEvent actionEvent) {
        cuentaDAO = new CuentaDAO();
        String tipoUsuario = cuentaDAO.login(txtUsuario.getText(),txtContrasenia.getText());
        if(tipoUsuario != null){
            if(tipoUsuario == "admin"){
                admin();
            }else if(tipoUsuario == "vendedor"){
                vendedor();
            } else if (tipoUsuario == "cliente") {
                cliente();
            }else {

            }
        }else{
            txtLog.setText("usuario o contraseña incorrectos");
        }
    }

    public void cliente(){

    }

    public void vendedor(){

    }

    public void admin(){

    }
}