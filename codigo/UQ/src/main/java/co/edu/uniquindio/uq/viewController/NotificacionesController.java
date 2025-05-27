package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Medico;
import co.edu.uniquindio.uq.model.Paciente;
import co.edu.uniquindio.uq.controller.SistemaHospitalario;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NotificacionesController {

    @FXML
    private TextField txtCedulaMedico;

    @FXML
    private TextArea txtNotificaciones;

    private final SistemaHospitalario sistema = SistemaHospitalario.getInstance();

    @FXML
    public void generarNotificacionesMedico() {
        String cedulaMedico = txtCedulaMedico.getText();

        if (cedulaMedico.isEmpty()) {
            txtNotificaciones.setText("Por favor, ingresa la cédula del médico.");
            return;
        }

        Medico medico = sistema.buscarMedicoPorCedula(cedulaMedico);
        if (medico == null) {
            txtNotificaciones.setText("No se encontró un médico con esa cédula.");
            return;
        }

        ObservableList<String> notificaciones = FXCollections.observableArrayList();

        for (Paciente paciente : sistema.getListaPacientes()) {
            String historial = paciente.getHistorialMedico();
            if (historial != null && !historial.isEmpty()) {
                String[] lineas = historial.split("\n");
                for (String linea : lineas) {
                    if (linea.contains("Médico: " + medico.getNombre())) {
                        notificaciones.add("Paciente: " + paciente.getNombre() + "\n" + linea + "\n--------------------------");
                    }
                }
            }
        }

        if (notificaciones.isEmpty()) {
            txtNotificaciones.setText("No hay notificaciones disponibles para este médico.");
        } else {
            StringBuilder resultado = new StringBuilder();
            for (String n : notificaciones) {
                resultado.append(n).append("\n");
            }
            txtNotificaciones.setText(resultado.toString());
        }
    }

    @FXML
    private void onVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/ConsultorioMedico.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
