package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Medico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import co.edu.uniquindio.uq.App;

import java.io.IOException;

public class ConsultorioMedicoController {

    // Acción para registrar diagnóstico
    @FXML
    private void onRegistrarDiagnostico(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/uq/Diagnostico.fxml"));
            Parent root = loader.load();

            // Obtener el controlador y pasar los datos del médico
            DiagnosticoController controller = loader.getController();
            controller.inicializarDatosMedico("Dr. Juan Pérez", "Cirugía General");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista.");
        }
    }


    // Acción para administrar horarios
    @FXML
    private void onAdministrarHorario(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/uq/AdministrarHorarioMedico.fxml"));
            Parent root = loader.load();

            // Obtener el controlador y pasarle el médico actual
            AdministrarHorarioMedicoController controller = loader.getController();
            controller.setMedicoActual(medicoSeleccionado); // Asegúrate de que 'medicoSeleccionado' esté inicializado correctamente

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Administrar Horario Médico");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de Administrar Horario Médico.");
        }
    }


    private Medico medicoSeleccionado;


    public void setMedico(Medico medico) {
        this.medicoSeleccionado = medico;
        System.out.println("🟢 Médico recibido en ConsultorioMedicoController: " + medico.getNombre());

    }





    // Acción para ver notificaciones de citas
    @FXML
    private void onVerNotificaciones(ActionEvent event) {
        try {
            // Cargar la vista reportes
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/Notificaciones.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de consulta de historial.");
        }
    }

    // Acción para volver al login
    @FXML
    private void onVolverAlLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de selección.");
        }
    }

    @FXML
    void onConsultarHistoria(ActionEvent event) {
        try {
            // Cargar la vista de ConsultarHistoria.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/ConsultarHistoriaVistaMedico.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de consulta de historial.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

