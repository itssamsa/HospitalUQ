package co.edu.uniquindio.uq.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegistrarPacienteController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextArea txtHistorial;

    @FXML
    private void handleRegistrarPaciente() {
        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String historial = txtHistorial.getText();

        // Aquí puedes guardar el paciente en una base de datos o lista
        System.out.println("Paciente registrado: " + nombre);
    }
}
