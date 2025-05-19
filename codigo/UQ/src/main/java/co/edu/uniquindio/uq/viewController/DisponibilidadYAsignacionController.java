package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Medico;
import co.edu.uniquindio.uq.model.Paciente;
import co.edu.uniquindio.uq.model.Cita;
import co.edu.uniquindio.uq.model.SistemaHospitalario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DisponibilidadYAsignacionController {

    @FXML
    private ComboBox<Medico> comboMedicos;

    @FXML
    private ComboBox<Paciente> comboPacientes;

    @FXML
    private Button btnAsignar;

    @FXML
    private TextField campoHorario;

    @FXML
    private Button btnVolver;

    private SistemaHospitalario sistema = SistemaHospitalario.getInstance();

    @FXML
    public void initialize() {
        comboMedicos.setItems(sistema.obtenerMedicos());
        comboPacientes.setItems(sistema.obtenerPacientes());
    }

    @FXML
    void asignarCita(ActionEvent event) {
        Medico medico = comboMedicos.getSelectionModel().getSelectedItem();
        Paciente paciente = comboPacientes.getSelectionModel().getSelectedItem();

        if (medico == null || paciente == null) {
            mostrarAlerta("Error", "Debe seleccionar tanto el médico como el paciente.");
            return;
        }

        // Obtener la especialidad del médico y un horario predeterminado
        String especialidad = medico.getEspecialidad();
        String horario = "Consulta General";  // Puedes cambiar el horario según corresponda

        // Crear la cita con todos los parámetros requeridos
        Cita nuevaCita = new Cita(especialidad, medico.getNombre(), horario, paciente.getCedula());

        // Registrar la cita en el sistema
        sistema.registrarCita(nuevaCita);

        mostrarAlerta("Asignación Exitosa", "La cita ha sido asignada exitosamente.");
    }

    @FXML
    void onVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/Administrador.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista.");
        }
    }

    @FXML
    void actualizarHorario(ActionEvent event) {
        Medico medico = comboMedicos.getSelectionModel().getSelectedItem();
        String nuevoHorario = campoHorario.getText();

        if (medico == null || nuevoHorario.isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar un médico y proporcionar el nuevo horario.");
            return;
        }

        medico.setHorario(nuevoHorario);
        mostrarAlerta("Horario Actualizado", "El horario del médico ha sido actualizado exitosamente.");
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
