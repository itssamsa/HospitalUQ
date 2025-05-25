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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DiagnosticoController {

    private final SistemaHospitalario sistemaHospitalario = SistemaHospitalario.getInstance();

    @FXML
    private TextField txtCedula;

    @FXML
    private TextArea txtHistorial;

    @FXML
    void onBuscar(ActionEvent event) {
        String cedula = txtCedula.getText();

        if (cedula.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese una cédula.");
            return;
        }

        String historial = sistemaHospitalario.consultarHistorialMedico(cedula);

        if (!historial.equals("Historial médico no encontrado.")) {
            txtHistorial.setText(historial);
        } else {
            txtHistorial.setText("No se encontró el historial médico para la cédula ingresada.");
        }
    }

    @FXML
    void onGuardar(ActionEvent event) {
        String cedula = txtCedula.getText();
        String nuevoHistorial = txtHistorial.getText();

        if (cedula.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar una cédula.");
            return;
        }

        Paciente paciente = sistemaHospitalario.obtenerPaciente(cedula);

        if (paciente == null) {
            mostrarAlerta("Error", "No se encontró un paciente con esa cédula.");
            return;
        }

        paciente.setHistorialMedico(nuevoHistorial);
        mostrarAlerta("Éxito", "Historial médico actualizado correctamente.");
    }

    @FXML
    void onVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/ConsultorioMedico.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de selección.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
