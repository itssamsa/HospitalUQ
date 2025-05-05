package co.edu.uniquindio.uq.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class IngresarPacienteController {

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnHistorial;
    @FXML
    private Button btnCitas;
    @FXML
    private Button btnSolicitarCita;
    @FXML
    private Button btnCancelarCita;

    @FXML
    private void handleActualizar() {
        System.out.println("Actualizar información del paciente");
        // Aquí se puede cargar una ventana para actualizar los datos del paciente
    }

    @FXML
    private void handleHistorial() {
        System.out.println("Consultar historial médico");
        // Aquí se puede mostrar el historial médico del paciente
    }

    @FXML
    private void handleCitas() {
        System.out.println("Ver citas programadas");
        // Aquí se pueden listar las citas programadas del paciente
    }

    @FXML
    private void handleSolicitarCita() {
        System.out.println("Solicitar nueva cita");
        // Aquí se podría abrir un formulario para pedir una cita
    }

    @FXML
    private void handleCancelarCita() {
        System.out.println("Cancelar una cita");
        // Aquí se puede gestionar la cancelación de una cita
    }
}
