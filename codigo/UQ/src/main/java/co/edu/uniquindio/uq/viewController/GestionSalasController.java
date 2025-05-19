package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.SistemaHospitalario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GestionSalasController {

    @FXML
    private ComboBox<String> comboSalas;
    @FXML
    private ComboBox<String> comboHorarios;
    @FXML
    private Label labelEstado;
    @FXML
    private Button btnReservar;

    private SistemaHospitalario sistema = SistemaHospitalario.getInstance();

    @FXML
    public void initialize() {
        cargarSalasYHorarios();
    }

    private void cargarSalasYHorarios() {
        comboSalas.getItems().clear();  // Limpiar items por si ya hay algo
        sistema.obtenerSalas().forEach(sala -> comboSalas.getItems().add(sala.getNombreSala()));

        comboHorarios.getItems().clear();
        sistema.obtenerSalas().forEach(sala -> comboHorarios.getItems().add(sala.getHorarioSala()));
    }

    @FXML
    private void onVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/Administrador.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reservarSala() {
        String salaSeleccionada = comboSalas.getValue();
        String horarioSeleccionado = comboHorarios.getValue();

        if (salaSeleccionada != null && horarioSeleccionado != null) {
            boolean disponible = sistema.estaSalaDisponible(salaSeleccionada, horarioSeleccionado);
            if (disponible) {
                sistema.reservarSala(salaSeleccionada, horarioSeleccionado);
                labelEstado.setText("Sala reservada con éxito.");
            } else {
                labelEstado.setText("Sala no disponible en el horario seleccionado.");
            }
        } else {
            labelEstado.setText("Por favor, seleccione una sala y un horario.");
        }
    }
}
