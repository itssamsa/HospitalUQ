package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Cita;
import co.edu.uniquindio.uq.model.Paciente;
import co.edu.uniquindio.uq.model.SistemaHospitalario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SolicitudCitaController {

    private SistemaHospitalario sistemaHospitalario = SistemaHospitalario.getInstance();

    @FXML
    private TextField txtCedula;

    @FXML
    private ComboBox<String> comboEspecialidad;

    @FXML
    private ComboBox<String> comboMedico;

    @FXML
    private ComboBox<String> comboHorario;

    @FXML
    void onBuscarPaciente(ActionEvent event) {
        String cedula = txtCedula.getText();
        Paciente paciente = sistemaHospitalario.buscarPaciente(cedula);

        if (paciente != null) {
            mostrarAlerta("Éxito", "Paciente encontrado: " + paciente.getNombre());
        } else {
            mostrarAlerta("Error", "Paciente no encontrado.");
        }
    }

    @FXML
    void onGuardarCita(ActionEvent event) {
        String cedula = txtCedula.getText();
        String especialidad = comboEspecialidad.getValue();
        String medico = comboMedico.getValue();
        String horario = comboHorario.getValue();

        if (cedula.isEmpty() || especialidad == null || medico == null || horario == null) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        Paciente paciente = sistemaHospitalario.buscarPaciente(cedula);
        if (paciente != null) {
            Cita nuevaCita = new Cita(especialidad, medico, horario, cedula);
            if (sistemaHospitalario.registrarCita(nuevaCita)) {
                paciente.setHistorialMedico(paciente.getHistorialMedico() + "\n" + nuevaCita);
                mostrarAlerta("Éxito", "Cita guardada correctamente.");
            } else {
                mostrarAlerta("Error", "No se pudo registrar la cita.");
            }
        } else {
            mostrarAlerta("Error", "El paciente no está registrado.");
        }
    }

    @FXML
    public void initialize() {
        comboEspecialidad.getItems().addAll("Medicina General", "Pediatría", "Cardiología");
        comboMedico.setDisable(true);
        comboHorario.setDisable(true);
        comboEspecialidad.setOnAction(e -> {
            comboMedico.getItems().clear();
            String especialidad = comboEspecialidad.getValue();
            switch (especialidad) {
                case "Medicina General":
                    comboMedico.getItems().addAll("Dr. Juan Pérez", "Dra. Ana Ruiz");
                    break;
                case "Pediatría":
                    comboMedico.getItems().addAll("Dra. Marta López", "Dr. Pedro Ortega");
                    break;
                case "Cardiología":
                    comboMedico.getItems().addAll("Dr. Carlos Gómez", "Dra. Laura Gutiérrez");
                    break;
                default:
                    mostrarAlerta("Advertencia", "Especialidad no válida.");
                    break;
            }
            comboMedico.setDisable(false);
        });
        comboMedico.setOnAction(e -> {
            comboHorario.getItems().clear();
            comboHorario.getItems().addAll(
                    "Lunes de 09:00 - 09:30",
                    "Martes de 10:00 - 10:30",
                    "Miércoles de 11:00 - 11:30",
                    "Viernes de 15:00 - 15:30"
            );
            comboHorario.setDisable(false);
        });
    }

    @FXML
    private void onVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/IngresarPaciente.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert.AlertType alertType;


        switch (titulo) {
            case "Error":
                alertType = Alert.AlertType.ERROR;
                break;
            case "Éxito":
                alertType = Alert.AlertType.CONFIRMATION;
                break;
            case "Advertencia":
                alertType = Alert.AlertType.WARNING;
                break;
            default:
                alertType = Alert.AlertType.INFORMATION;
                break;
        }

        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
