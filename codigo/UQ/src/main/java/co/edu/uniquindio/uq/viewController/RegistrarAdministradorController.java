package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Medico;
import co.edu.uniquindio.uq.model.SistemaHospitalario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegistrarAdministradorController {


    private SistemaHospitalario sistemaHospitalario = SistemaHospitalario.getInstance();


    @FXML
    private TextField txtNombre;


    @FXML
    private TextField txtCedula;


    @FXML
    private TextField txtDireccion;


    @FXML
    private TextField txtTelefono;


    @FXML
    private TextField txtPassword;


    @FXML
    private Button btnGuardar;


    @FXML
    private Button btnVolver;


    @FXML
    void onGuardar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String password = txtPassword.getText();


        if (nombre.isEmpty() || cedula.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }


        // Registrar el Administrador en el sistema hospitalario
        boolean registrado = sistemaHospitalario.RegistrarAdministrador(nombre, cedula, direccion, telefono,password);


        if (registrado) {
            mostrarAlerta("Éxito", "Administrador registrado correctamente.");
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "No se pudo registrar el Administrador.");
        }
    }


    @FXML
    private void onVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/Administrador.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de selección.");
        }
    }


    private void limpiarCampos() {
        txtNombre.clear();
        txtCedula.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        txtPassword.clear();
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

