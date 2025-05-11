package co.edu.uniquindio.uq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import co.edu.uniquindio.uq.App;

public class ConsultorioMedicoController {

    // Acción para ver historial médico
    @FXML
    private void onConsultarHistorial(ActionEvent event) {
        // Aquí puedes enlazar con la vista de historial médico
        // App.cambiarEscena("/path/to/VerHistorialPaciente.fxml", "Historial Médico");
    }

    // Acción para registrar diagnóstico
    @FXML
    private void onRegistrarDiagnostico(ActionEvent event) {
        // Aquí puedes enlazar con la vista de registro de diagnóstico
        // App.cambiarEscena("/path/to/RegistrarDiagnostico.fxml", "Registrar Diagnóstico");
    }

    // Acción para administrar horarios
    @FXML
    private void onAdministrarHorario(ActionEvent event) {
        // Aquí puedes enlazar con la vista de administración de horarios
        // App.cambiarEscena("/path/to/HorarioConsulta.fxml", "Administrar Horarios");
    }

    // Acción para ver notificaciones de citas
    @FXML
    private void onVerNotificaciones(ActionEvent event) {
        // Aquí puedes enlazar con la vista de notificaciones
        // App.cambiarEscena("/path/to/NotificacionesCitas.fxml", "Notificaciones de Citas");
    }

    // Acción para volver al login
    @FXML
    private void onVolverAlLogin(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/uq/Login.fxml", "Login");
    }
}

