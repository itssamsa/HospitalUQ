package co.edu.uniquindio.uq.viewController;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import co.edu.uniquindio.uq.model.Paciente;
import co.edu.uniquindio.uq.controller.SistemaHospitalario;
import javafx.stage.Stage;


public class AdmIngresoPacienteController {


    private SistemaHospitalario sistemaHospitalario;


    @FXML
    private TableView<Paciente> tblPacientes;
    @FXML
    private TableColumn<Paciente, String> colNombre, colCedula, colDireccion, colTelefono;
    @FXML
    private TextField txtNombre, txtCedula, txtDireccion, txtTelefono, txtPassword;
    @FXML
    private Button btnActualizar, btnEliminar;


    @FXML
    void initialize() {
        sistemaHospitalario = SistemaHospitalario.getInstance();


        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colCedula.setCellValueFactory(cellData -> cellData.getValue().cedulaProperty());
        colDireccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());


        cargarTablaPacientes();
    }


    private void cargarTablaPacientes() {
        ObservableList<Paciente> pacientes = sistemaHospitalario.obtenerPacientes();
        tblPacientes.setItems(pacientes);
    }


    @FXML
    void onActualizarPaciente(ActionEvent event) {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Actualizar el paciente en el sistema hospitalario
            sistemaHospitalario.actualizarPaciente(seleccionado.getCedula(), txtNombre.getText(), txtCedula.getText(), txtDireccion.getText(), txtTelefono.getText(), txtPassword.getText());
            tblPacientes.refresh();
            mostrarAlerta("Éxito", "Paciente actualizado correctamente.");
        } else {
            mostrarAlerta("Error", "Seleccione un paciente para actualizar.");
        }
    }


    @FXML
    void onEliminarPaciente(ActionEvent event) {
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
    private void handleRegistrarPaciente(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/RegistroAdm.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            mostrarAlerta("Error", "No se pudo cargar la vista de selección.");
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

