package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void ingresarComoPaciente(ActionEvent event) {
        try {
            App.cambiarEscena("/resources/SeleccionarRegistrarIngresar.fxml", "Opciones de Paciente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ingresarComoAdministrador(ActionEvent event) {
        // Aquí también puedes agregar navegación a la vista de administrador
    }

    @FXML
    private void ingresarComoMedico(ActionEvent event) {
        // Aquí también puedes agregar navegación a la vista de médico
    }
}
