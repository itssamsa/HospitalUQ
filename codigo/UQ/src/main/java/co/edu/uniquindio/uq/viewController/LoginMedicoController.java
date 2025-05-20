package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Medico;
import co.edu.uniquindio.uq.model.SistemaHospitalario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
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
    private void onIniciarSecion() {  // debe coincidir exactamente con el FXML
        Medico medicoSeleccionado = comboMedicos.getValue();
        String password = txtPassword.getText().trim();

        if (medicoSeleccionado == null || password.isEmpty()) {
            lblMensaje.setText("Debe seleccionar un médico e ingresar la contraseña.");
            return;
        }

        if (medicoSeleccionado.getPassword().equals(password)) {
            lblMensaje.setText("Bienvenido, Dr(a). " + medicoSeleccionado.getNombre());
            ConsultorioMedico();
        } else {
            lblMensaje.setText("Contraseña incorrecta.");
        }
    }

    private void ConsultorioMedico() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/uq/ConsultorioMedico.fxml")); // Ajusta la ruta según tu estructura de carpetas
            Parent root = loader.load();


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


            // Cierra la ventana actual de login
            Stage ventanaActual = (Stage) btnIniciarSecion.getScene().getWindow();
            ventanaActual.close();

        } catch (IOException e) {
            lblMensaje.setText("Error al cargar el consultorio.");
            e.printStackTrace();
        }
    }




}

