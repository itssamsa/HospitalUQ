package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.SistemaHospitalario;
import co.edu.uniquindio.uq.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CancelarCitaController {

    @FXML
    private TextField txtCedula;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCancelarCita;

    @FXML
    private TableView<String> tablaCitas;

    @FXML
    private TableColumn<String, String> colCita;

    private ObservableList<String> citasList;

    // Inicializa la tabla
    @FXML
    public void initialize() {
        colCita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()));
        citasList = FXCollections.observableArrayList();
        tablaCitas.setItems(citasList);
    }

    // Buscar y cargar las citas del paciente
    @FXML
    public void onBuscar(ActionEvent event) {
        String cedula = txtCedula.getText().trim();

        if (cedula.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar una cédula.", Alert.AlertType.ERROR);
            return;
        }

        Paciente paciente = SistemaHospitalario.getInstance().buscarPaciente(cedula);

        if (paciente != null) {
            ObservableList<String> citas = SistemaHospitalario.getInstance().obtenerDetallesCita(cedula);

            if (citas.isEmpty()) {
                mostrarAlerta("Sin Citas", "El paciente no tiene citas registradas.", Alert.AlertType.INFORMATION);
                citasList.clear();
            } else {
                citasList.setAll(citas);
                mostrarAlerta("Éxito", "Citas cargadas correctamente.", Alert.AlertType.CONFIRMATION);
            }
        } else {
            mostrarAlerta("Paciente no encontrado", "No se encontró el paciente con la cédula ingresada.", Alert.AlertType.WARNING);
            citasList.clear();
        }
    }

    // Cancelar la cita seleccionada
    @FXML
    public void onCancelarCita(ActionEvent event) {
        String cedula = txtCedula.getText().trim();
        String citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada == null) {
            mostrarAlerta("Error", "Debe seleccionar una cita para cancelar.", Alert.AlertType.ERROR);
            return;
        }
        boolean exito = SistemaHospitalario.getInstance().cancelarCita(cedula, citaSeleccionada);
        if (exito) {
            citasList.remove(citaSeleccionada);
            mostrarAlerta("Éxito", "La cita ha sido cancelada correctamente.", Alert.AlertType.CONFIRMATION);
        } else {
            mostrarAlerta("Error", "No se pudo cancelar la cita.", Alert.AlertType.ERROR);
        }
    }

    // Mostrar mensajes de alerta
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
