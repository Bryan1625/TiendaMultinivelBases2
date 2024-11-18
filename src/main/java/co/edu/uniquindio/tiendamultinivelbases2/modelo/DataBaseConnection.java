package co.edu.uniquindio.tiendamultinivelbases2.modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static String user = "ProyectoFinal";
    private static String password = "1234";

    // Método para obtener una conexión sin modificar las credenciales
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
    }

    // Método para obtener una conexión con credenciales personalizadas
    public static Connection loginDataBase(String user, String password) throws SQLException {
        DataBaseConnection.user = user;
        DataBaseConnection.password = password;
        return DriverManager.getConnection(URL, user, password);
    }
}
