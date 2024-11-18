module co.edu.uniquindio.tiendamultinivelbases2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens co.edu.uniquindio.tiendamultinivelbases2 to javafx.fxml;
    exports co.edu.uniquindio.tiendamultinivelbases2;
}