package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import co.edu.uniquindio.uq.App;
import co.edu.uniquindio.uq.model.SistemaHospitalario;


public class ConsultorioMedicoController {

    @FXML
    void onConsultarHistoria(ActionEvent event) {
        try {
            // Cargar la vista de ConsultarHistoria.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/ConsultarHistoria.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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