package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Paciente;
import co.edu.uniquindio.uq.model.SistemaHospitalario;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class IngresarPacienteController {

    @FXML
    private TableView<Paciente> tblPacientes;
    @FXML
    private TableColumn<Paciente, String> colNombre, colCedula, colDireccion, colTelefono;
    @FXML
    private TextField txtNombre, txtCedula, txtDireccion, txtTelefono;
    @FXML
    private Button btnActualizar, btnEliminar;

    private SistemaHospitalario sistemaHospitalario;

    @FXML
    void onActualizar(ActionEvent event) {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Actualizar el paciente en el sistema hospitalario
            sistemaHospitalario.actualizarPaciente(seleccionado.getCedula(), txtNombre.getText(), txtCedula.getText(), txtDireccion.getText(), txtTelefono.getText());
            tblPacientes.refresh();
            mostrarAlerta("Éxito", "Paciente actualizado correctamente.");
        } else {
            mostrarAlerta("Error", "Seleccione un paciente para actualizar.");
        }
    }

    @FXML
    void onEliminar(ActionEvent event) {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Eliminar el paciente en el sistema hospitalario
            sistemaHospitalario.eliminarPaciente(seleccionado.getCedula());
            tblPacientes.getItems().remove(seleccionado);
            mostrarAlerta("Éxito", "Paciente eliminado.");
        } else {
            mostrarAlerta("Error", "Seleccione un paciente para eliminar.");
        }
    }

    @FXML
    void initialize() {
        sistemaHospitalario = SistemaHospitalario.getInstance(); // Acceso a la instancia única

        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colCedula.setCellValueFactory(cellData -> cellData.getValue().cedulaProperty());
        colDireccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());

        // Obtener la lista de pacientes desde el sistema hospitalario
        ObservableList<Paciente> pacientes = sistemaHospitalario.obtenerPacientes();
        tblPacientes.setItems(pacientes);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
