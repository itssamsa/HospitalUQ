package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.CRUDPaciente;
import co.edu.uniquindio.uq.model.Paciente;
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

    @FXML
    void onActualizar(ActionEvent event) {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            CRUDPaciente.actualizarPaciente(seleccionado, txtNombre.getText(), txtCedula.getText(), txtDireccion.getText(), txtTelefono.getText());
            tblPacientes.refresh();
            mostrarAlerta("Éxito", "Paciente actualizado correctamente.");
        }
    }

    @FXML
    void onEliminar(ActionEvent event) {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            CRUDPaciente.eliminarPaciente(seleccionado);
            tblPacientes.getItems().remove(seleccionado);
            mostrarAlerta("Éxito", "Paciente eliminado.");
        }
    }

    @FXML
    void initialize() {
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colCedula.setCellValueFactory(cellData -> cellData.getValue().cedulaProperty());
        colDireccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());

        ObservableList<Paciente> pacientes = CRUDPaciente.obtenerPacientes();
        tblPacientes.setItems(pacientes);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
