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


public class RegistroAdmMedicosController {


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
    private TextField txtEspecialidad;


    @FXML
    private TextField txtHorario;

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
        String especialidad = txtEspecialidad.getText();
        String horario = txtHorario.getText();
        String password = txtPassword.getText();


        if (nombre.isEmpty() || cedula.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || especialidad.isEmpty() || horario.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }


        // Verificar si el paciente ya existe
        if (sistemaHospitalario.existePaciente(cedula)) {
            mostrarAlerta("Error", "El paciente con esta cédula ya está registrado.");
            return;
        }


        // Registrar el paciente en el sistema hospitalario
        boolean registrado = sistemaHospitalario.registrarMedico(nombre, cedula, direccion, telefono, especialidad, horario,password);


        if (registrado) {
            mostrarAlerta("Éxito", "Medico registrado correctamente.");
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "No se pudo registrar el medico.");
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
        txtEspecialidad.clear();
        txtHorario.clear();
        txtPassword.clear();
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
