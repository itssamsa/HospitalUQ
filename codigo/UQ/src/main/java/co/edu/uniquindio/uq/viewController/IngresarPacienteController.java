// Controlador actualizado: IngresarPacienteController.java

package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Paciente;
import co.edu.uniquindio.uq.model.SistemaHospitalario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    private TextField txtBuscarCedula;

    private SistemaHospitalario sistemaHospitalario;

    @FXML
    void onActualizar(ActionEvent event) {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            sistemaHospitalario.actualizarPaciente(
                    seleccionado.getCedula(),
                    txtNombre.getText(),
                    txtCedula.getText(),
                    txtDireccion.getText(),
                    txtTelefono.getText()
            );
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
            sistemaHospitalario.eliminarPaciente(seleccionado.getCedula());
            tblPacientes.getItems().remove(seleccionado);
            mostrarAlerta("Éxito", "Paciente eliminado.");
        } else {
            mostrarAlerta("Error", "Seleccione un paciente para eliminar.");
        }
    }

    @FXML
    private void setBtnVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/SeleccionarRegistrarIngresar.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/ConsultarHistoria.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de consulta de historial.");
        }
    }

    @FXML
    void onSolicitudCita(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/SolicitudCita.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista.");
        }
    }

    @FXML
    void onCancelarCita(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/CancelarCita.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de consulta de historial.");
        }
    }

    @FXML
    void onBuscarPorCedula(ActionEvent event) {
        String cedulaIngresada = txtBuscarCedula.getText();
        Paciente paciente = sistemaHospitalario.buscarPaciente(cedulaIngresada);

        if (paciente != null) {
            ObservableList<Paciente> resultado = FXCollections.observableArrayList(paciente);
            tblPacientes.setItems(resultado);

            txtNombre.setText(paciente.getNombre());
            txtCedula.setText(paciente.getCedula());
            txtDireccion.setText(paciente.getDireccion());
            txtTelefono.setText(paciente.getTelefono());
        } else {
            tblPacientes.setItems(FXCollections.observableArrayList());
            mostrarAlerta("No encontrado", "No existe un paciente con esa cédula.");
        }
    }

    @FXML
    void initialize() {
        sistemaHospitalario = SistemaHospitalario.getInstance();

        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colCedula.setCellValueFactory(cellData -> cellData.getValue().cedulaProperty());
        colDireccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());

        tblPacientes.setItems(FXCollections.observableArrayList()); // Se inicia vacía
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
