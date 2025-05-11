package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Paciente;
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

public class RegistrarPacienteController {

    // Usar la instancia única de SistemaHospitalario
    private SistemaHospitalario sistemaHospitalario = SistemaHospitalario.getInstance();

    @FXML
    private TextField txtNombre, txtCedula, txtDireccion, txtTelefono;

    @FXML
    private Button btnGuardar, btnVolver;

    @FXML
    void onGuardar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();

        if (nombre.isEmpty() || cedula.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        // Verificar si el paciente ya existe
        if (sistemaHospitalario.existePaciente(cedula)) {
            mostrarAlerta("Error", "El paciente con esta cédula ya está registrado.");
            return;
        }

        // Registrar el paciente en el sistema hospitalario
        boolean registrado = sistemaHospitalario.registrarPaciente(nombre, cedula, direccion, telefono, "", "");

        if (registrado) {
            mostrarAlerta("Éxito", "Paciente registrado correctamente.");
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "No se pudo registrar el paciente.");
        }
    }

    @FXML
    private void setBtnVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/SeleccionarRegistrarIngresar.fxml"));
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
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
