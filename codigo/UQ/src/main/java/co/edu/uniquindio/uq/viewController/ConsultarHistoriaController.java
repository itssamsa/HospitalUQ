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

    public SistemaHospitalario sistemaHospitalario = SistemaHospitalario.getInstance();

    @FXML
    private TextField txtCedulaConsulta;

    @FXML
    private TextArea txtHistoria;

    @FXML
    void onConsultar(ActionEvent event) {
        String cedula = txtCedulaConsulta.getText();

        if (cedula.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese una cédula.");
            return;
        }

        // Consultar el historial médico usando el metodo del sistema hospitalario
        String historial = sistemaHospitalario.consultarHistorialMedico(cedula);

        // Verificar el contenido del historial
        System.out.println("Historial obtenido: " + historial);

        // Mostrar el historial en el TextArea
        if (!historial.equals("Historial médico no encontrado.")) {
            txtHistoria.setText("Historial Médico:\n" + historial); // Mostrar en el cuadro de texto
        } else {
            txtHistoria.setText("No se encontró el historial médico para la cédula ingresada.");
        }
    }

    @FXML
    private void setBtnVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/IngresarPaciente.fxml"));
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
