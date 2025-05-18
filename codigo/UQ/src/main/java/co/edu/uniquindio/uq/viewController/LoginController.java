package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void ingresarComoPaciente(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/uq/SeleccionarRegistrarIngresar.fxml", "Opciones de ingreso del paciente");
    }

    @FXML
    private void ingresarComoAdministrador(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/uq/Administrador.fxml", "");
    }


    @FXML
    private void ingresarComoMedico(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/uq/ConsultorioMedico.fxml", "Opciones de ingreso del medico");
    }


}
