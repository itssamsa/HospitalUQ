
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

import java.util.List;
import java.util.Map;

public class SolicitudCitaController {

    private final SistemaHospitalario sistemaHospitalario = SistemaHospitalario.getInstance();

    @FXML
    private TextField txtCedula;

    @FXML
    private ComboBox<String> comboEspecialidad;

    @FXML
    private ComboBox<String> comboMedico;

    @FXML
    private ComboBox<String> comboDia;

    @FXML
    private ComboBox<String> comboHora;

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
        String medicoNombre = comboMedico.getValue();
        String dia = comboDia.getValue();
        String hora = comboHora.getValue();

        if (cedula.isEmpty() || especialidad == null || medicoNombre == null || dia == null || hora == null) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        Paciente paciente = sistemaHospitalario.buscarPaciente(cedula);
        if (paciente != null) {
            Medico medico = sistemaHospitalario.buscarMedicoPorNombre(medicoNombre);
            if (medico != null) {
                // Registrar la cita
                Cita nuevaCita = new Cita(especialidad, medicoNombre, dia + " " + hora, cedula);

                // Verificación en historial
                String citaInfo = "Cita: Especialidad: " + especialidad + ", Médico: " + medicoNombre + ", Día y hora: " + dia + " " + hora;
                String historial = paciente.getHistorialMedico();

                if (!historial.contains(citaInfo)) {
                    paciente.setHistorialMedico(historial + "\n" + citaInfo);
                    // Eliminar horario de la agenda del médico
                    medico.getAgenda().get(dia).remove(hora);
                    mostrarAlerta("Éxito", "Cita registrada correctamente.");
                    limpiarCampos();
                } else {
                    mostrarAlerta("Advertencia", "La cita ya está registrada.");
                }
            } else {
                mostrarAlerta("Error", "Médico no encontrado.");
            }
        } else {
            mostrarAlerta("Error", "Paciente no encontrado.");
        }
    }

    private void limpiarCampos() {
        txtCedula.clear();
        comboEspecialidad.setValue(null);
        comboMedico.getItems().clear();
        comboDia.getItems().clear();
        comboHora.getItems().clear();
        comboMedico.setDisable(true);
        comboDia.setDisable(true);
        comboHora.setDisable(true);
    }

    @FXML
    public void initialize() {
        comboEspecialidad.getItems().addAll("Medicina General", "Pediatria", "Cardiologia");

        comboMedico.setDisable(true);
        comboDia.setDisable(true);
        comboHora.setDisable(true);

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

        comboMedico.setOnAction(e -> {
            comboDia.getItems().clear();
            comboHora.getItems().clear();
            String nombreMedico = comboMedico.getValue();
            Medico medico = sistemaHospitalario.buscarMedicoPorNombre(nombreMedico);
            if (medico != null) {
                Map<String, List<String>> agenda = medico.getAgenda();
                comboDia.getItems().addAll(agenda.keySet());
                comboDia.setDisable(false);
                comboHora.setDisable(true);
            }
        });

        comboDia.setOnAction(e -> {
            comboHora.getItems().clear();
            String nombreMedico = comboMedico.getValue();
            String dia = comboDia.getValue();
            Medico medico = sistemaHospitalario.buscarMedicoPorNombre(nombreMedico);
            if (medico != null && dia != null) {
                List<String> horas = medico.getAgenda().get(dia);
                if (horas != null && !horas.isEmpty()) {
                    comboHora.getItems().addAll(horas);
                    comboHora.setDisable(false);
                } else {
                    mostrarAlerta("Advertencia", "No hay horarios disponibles para ese día.");
                }
            }
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
