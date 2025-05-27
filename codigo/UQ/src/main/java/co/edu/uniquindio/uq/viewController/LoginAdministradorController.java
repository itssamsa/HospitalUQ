package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Administrador;
import co.edu.uniquindio.uq.controller.SistemaHospitalario;
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

import java.io.IOException;

public class LoginAdministradorController {

    @FXML
    private ComboBox<Administrador> comboAdministradores;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnIniciarSecion;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {
        ObservableList<Administrador> administradores = FXCollections.observableArrayList(
                SistemaHospitalario.getInstance().getListaAdministradores()
        );
        comboAdministradores.setItems(administradores);
    }

    @FXML
    private void onIniciarSecion() {
        Administrador administradorSeleccionado = comboAdministradores.getValue();
        String password = txtPassword.getText().trim();

        if (administradorSeleccionado == null || password.isEmpty()) {
            lblMensaje.setText("Debe seleccionar un Administrador e ingresar la contraseña.");
            return;
        }

        if (administradorSeleccionado.getPassword().equals(password)) {
            lblMensaje.setText("Bienvenido, Dr(a). " + administradorSeleccionado.getNombre());
            abrirAdministrador();
        } else {
            lblMensaje.setText("Contraseña incorrecta.");
        }
    }

    private void abrirAdministrador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/uq/Administrador.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Administrador");
            stage.show();

            // Cierra la ventana actual de login
            Stage ventanaActual = (Stage) btnIniciarSecion.getScene().getWindow();
            ventanaActual.close();

        } catch (IOException e) {
            lblMensaje.setText("Error al cargar el consultorio.");
            e.printStackTrace();
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

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

