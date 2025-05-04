package co.edu.uniquindio.uq.viewController;

import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void ingresarComoPaciente() {
        // Lógica cuando se hace clic en el botón de "Paciente"
        System.out.println("Iniciando sesión como Paciente");
    }

    @FXML
    private void ingresarComoMedico() {
        // Lógica cuando se hace clic en el botón de "Médico"
        System.out.println("Iniciando sesión como Médico");
    }

    @FXML
    private void ingresarComoAdministrador() {
        // Lógica cuando se hace clic en el botón de "Administrador"
        System.out.println("Iniciando sesión como Administrador");
    }
}
