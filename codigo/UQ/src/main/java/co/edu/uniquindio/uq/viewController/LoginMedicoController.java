package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Medico;
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

import java.io.IOException;

public class LoginMedicoController {

    @FXML
    private ComboBox<Medico> comboMedicos;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnIniciarSecion;

    @FXML
    private Label lblMensaje;



    @FXML
    public void initialize() {
        ObservableList<Medico> medicos = FXCollections.observableArrayList(
                SistemaHospitalario.getInstance().getListaMedicos()
        );
        comboMedicos.setItems(medicos);

    }



    @FXML
    private void onIniciarSecion() {
        Medico medicoSeleccionado = comboMedicos.getValue();
        String password = txtPassword.getText().trim();

        if (medicoSeleccionado == null || password.isEmpty()) {
            lblMensaje.setText("Debe seleccionar un médico e ingresar la contraseña.");
            return;
        }

        if (medicoSeleccionado.getPassword().equals(password)) {
            // ✅ Guardar el médico que inició sesión
            SistemaHospitalario.getInstance().setMedicoActual(medicoSeleccionado);

            lblMensaje.setText("Bienvenido, Dr(a). " + medicoSeleccionado.getNombre());
            abrirConsultorioMedico();
        } else {
            lblMensaje.setText("Contraseña incorrecta.");
        }

    }


    private void abrirConsultorioMedico() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/uq/ConsultorioMedico.fxml"));
            Parent root = loader.load();

            // ✅ Obtener el controlador y pasar el médico logueado
            ConsultorioMedicoController controller = loader.getController();
            controller.setMedico(SistemaHospitalario.getInstance().getMedicoActual());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Consultorio Médico");
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
