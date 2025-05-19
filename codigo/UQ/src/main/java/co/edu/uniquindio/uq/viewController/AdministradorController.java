package co.edu.uniquindio.uq.viewController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AdministradorController {


    @FXML
    private Button btnControlSalasYHorarios;


    @FXML
    private Button btnReportes;


    @FXML
    private Button btnAdministracionMedicosYPacientes;


    @FXML
    private Button btnDisponibilidadYAsignacion;


    @FXML
    private Button btnVolver;


    @FXML
    void onControlSalasYHorarios(ActionEvent event) {
        try {
            // Cargar la vista reportes
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/GestionSalas.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de consulta de historial.");
        }
    }


    @FXML
    void onReportes(ActionEvent event) {
        mostrarAlerta("Control de Salas y Horarios", "Aquí se gestionan las salas y los horarios.");
        // Llamar a la vista correspondiente
    }


    @FXML
    void administrarAPaciente(ActionEvent event) {
        try {
            // Cargar la vista reportes
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/AdmIngresoPaciente.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de consulta de historial.");
        }
    }


    @FXML
    void administrarAMedico(ActionEvent event) {
        try {
            // Cargar la vista reportes
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/RegistroAdmMedicos.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de consulta de historial.");
        }
    }


    @FXML
    void onAdministracion(ActionEvent event) {
        mostrarAlerta("Administración de Médicos y Pacientes", "Aquí se administran médicos y pacientes.");
        // Llamar a la vista correspondiente
    }


    @FXML
    void onDisponibilidadYAsiganacion(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/DisponibilidadYAsignacion.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de consulta de historial.");
        }
    }


    @FXML
    private void onVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista.");
        }
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
