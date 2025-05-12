package co.edu.uniquindio.uq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import co.edu.uniquindio.uq.model.SistemaHospitalario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class SolicitarCitaController {

    private SistemaHospitalario sistemaHospitalario = SistemaHospitalario.getInstance();

    @FXML
    private ComboBox<String> cmbEspecialidad;

    @FXML
    private ComboBox<String> cmbMedico;

    @FXML
    private ComboBox<String> cmbFecha;

    @FXML
    private ComboBox<String> cmbHorario;

    @FXML
    private Button btnSolicitarCita;

    @FXML
    void initialize() {
        cargarEspecialidades();
        cmbMedico.setDisable(true);
        cmbHorario.setDisable(true);
    }

    private void cargarEspecialidades() {
        cmbEspecialidad.getItems().addAll("Medicina General", "Pediatría", "Cardiología");
    }

    @FXML
    void onEspecialidadSeleccionada(ActionEvent event) {
        String especialidad = cmbEspecialidad.getValue();
        if (especialidad != null) {
            List<String> medicos = sistemaHospitalario.obtenerMedicosPorEspecialidad(especialidad);
            cmbMedico.getItems().clear();
            cmbMedico.getItems().addAll(medicos);
            cmbMedico.setDisable(false);
        }
    }

    @FXML
    void onMedicoSeleccionado(ActionEvent event) {
        String medico = cmbMedico.getValue();
        if (medico != null) {
            // Cargar fechas disponibles para el médico seleccionado (puedes personalizar)
            cmbFecha.getItems().clear();
            cmbFecha.getItems().addAll("Lunes", "Martes", "Miércoles");
            cmbFecha.setDisable(false);
        }
    }

    @FXML
    void onFechaSeleccionada(ActionEvent event) {
        String medico = cmbMedico.getValue();
        String fecha = cmbFecha.getValue();
        if (medico != null && fecha != null) {
            List<String> horarios = sistemaHospitalario.obtenerHorariosDisponibles(medico, fecha);
            cmbHorario.getItems().clear();
            cmbHorario.getItems().addAll(horarios);
            cmbHorario.setDisable(false);
        }
    }

    @FXML
    void onSolicitarCita(ActionEvent event) {
        String especialidad = cmbEspecialidad.getValue();
        String medico = cmbMedico.getValue();
        String fecha = cmbFecha.getValue();
        String horario = cmbHorario.getValue();

        if (especialidad == null || medico == null || fecha == null || horario == null) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        boolean citaRegistrada = sistemaHospitalario.registrarCita("123", especialidad, medico, fecha, horario);
        if (citaRegistrada) {
            mostrarAlerta("Éxito", "Cita registrada con éxito.");
        } else {
            mostrarAlerta("Error", "No se pudo registrar la cita.");
        }
    }

    @FXML
    void setBtnVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/SeleccionarRegistrarIngresar.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
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
