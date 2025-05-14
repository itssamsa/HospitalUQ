package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Cita;
import co.edu.uniquindio.uq.model.Medico;
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

        if (cedula.isEmpty() || especialidad == null || medico == null || horario == null || horario.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        Paciente paciente = sistemaHospitalario.buscarPaciente(cedula);
        if (paciente != null) {
            Medico medicoSeleccionado = sistemaHospitalario.buscarMedicoPorNombre(medico);
            if (medicoSeleccionado != null) {
                // Crear la cita con el horario correctamente asignado
                Cita nuevaCita = new Cita(especialidad, medico, horario, cedula);

                // Verificar si la cita ya está registrada en el historial
                String citaInfo = "Cita: Especialidad: " + especialidad + ", Médico: " + medico + ", Horario: " + horario;
                String historial = paciente.getHistorialMedico();

                if (!historial.contains(citaInfo)) {  // Verificar duplicidad
                    paciente.setHistorialMedico(historial + "\n" + citaInfo);
                    mostrarAlerta("Éxito", "Cita guardada correctamente.");
                } else {
                    mostrarAlerta("Advertencia", "La cita ya está registrada.");
                }
            } else {
                mostrarAlerta("Error", "Médico no encontrado.");
            }
        } else {
            mostrarAlerta("Error", "El paciente no está registrado.");
        }
    }

    @FXML
    void onSeleccionarMedico(ActionEvent event) {
        String nombreMedico = comboMedico.getValue();
        if (nombreMedico != null) {
            Medico medicoSeleccionado = sistemaHospitalario.buscarMedicoPorNombre(nombreMedico);
            if (medicoSeleccionado != null) {
                String horario = medicoSeleccionado.getHorario();
                if (horario != null && !horario.isEmpty()) {
                    comboHorario.getItems().clear();
                    comboHorario.getItems().add(horario);
                    comboHorario.setValue(horario);
                    comboHorario.setDisable(false);
                } else {
                    mostrarAlerta("Error", "El médico seleccionado no tiene un horario disponible.");
                }
            } else {
                mostrarAlerta("Error", "No se encontró el médico seleccionado.");
            }
        }
    }

    @FXML
    public void initialize() {
        comboEspecialidad.getItems().addAll("Medicina General", "Pediatria", "Cardiologia");
        comboMedico.setDisable(true);
        comboHorario.setDisable(true);

        comboEspecialidad.setOnAction(e -> {
            comboMedico.getItems().clear();
            String especialidad = comboEspecialidad.getValue();
            for (Medico medico : sistemaHospitalario.obtenerMedicos()) {
                if (medico.getEspecialidad().equals(especialidad)) {
                    comboMedico.getItems().add(medico.getNombre());
                }
            }
            comboMedico.setDisable(false);
        });

        comboMedico.setOnAction(this::onSeleccionarMedico);
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
