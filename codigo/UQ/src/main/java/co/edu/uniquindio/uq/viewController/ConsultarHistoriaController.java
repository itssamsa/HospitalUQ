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

public class ConsultarHistoriaController {

    public SistemaHospitalario sistemaHospitalario = new SistemaHospitalario();

    @FXML
    private TextField txtCedulaConsulta;

    @FXML
    private TextArea txtHistoria;

    @FXML
    void onConsultar(ActionEvent event) {
        String cedula = txtCedulaConsulta.getText();

        if (cedula.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingrese una cédula.");
            return;
        }

        Paciente paciente = sistemaHospitalario.buscarPaciente(cedula);
        if (paciente != null) {
            String historia = paciente.getHistorialMedico();
            txtHistoria.setText(historia.isEmpty() ? "No hay historial disponible." : historia);
        } else {
            mostrarAlerta("Error", "Paciente no encontrado.");
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

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
