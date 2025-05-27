package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Medico;
import co.edu.uniquindio.uq.model.Paciente;
import co.edu.uniquindio.uq.controller.SistemaHospitalario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DiagnosticoController {

    private String nombreMedico;
    private String especialidadMedico;

    private final SistemaHospitalario sistemaHospitalario = SistemaHospitalario.getInstance();

    @FXML
    private TextField txtCedulaConsulta;

    @FXML
    private TextArea txtHistorial;

    @FXML
    void onConsultar(ActionEvent event) {
        String cedula = txtCedulaConsulta.getText();

        if (cedula.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese una cédula.");
            return;
        }

        // Consultar el historial médico usando el metodo del sistema hospitalario
        String historial = sistemaHospitalario.consultarHistorialMedico(cedula);

        // Verificar el contenido del historial
        System.out.println("Historial obtenido: " + historial);

        // Mostrar el historial en el TextArea
        if (!historial.equals("Historial médico no encontrado.")) {
            txtHistorial.setText("Historial Médico:\n" + historial); // Mostrar en el cuadro de texto
        } else {
            txtHistorial.setText("No se encontró el historial médico para la cédula ingresada.");
        }
    }



    @FXML
    void onGuardar(ActionEvent event) {
        String cedula = txtCedulaConsulta.getText();
        String diagnostico = txtHistorial.getText();

        if (cedula.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar una cédula.");
            return;
        }

        Paciente paciente = sistemaHospitalario.obtenerPaciente(cedula);

        if (paciente == null) {
            mostrarAlerta("Error", "No se encontró un paciente con esa cédula.");
            return;
        }

        // Obtener el médico autenticado
        Medico medico = sistemaHospitalario.getMedicoActual();

        if (medico == null) {
            mostrarAlerta("Error", "No se pudo obtener el médico actual.");
            return;
        }

        // Obtener la fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String entrada = "\n--- Diagnóstico registrado ---\n"
                + "Fecha: " + ahora.format(formato) + "\n"
                + "Médico: " + medico.getNombre() + "\n"
                + "Especialidad: " + medico.getEspecialidad() + "\n"
                + "Diagnóstico:\n" + diagnostico + "\n";

        // Concatenar al historial existente
        String historialActual = paciente.getHistorialMedico();
        paciente.setHistorialMedico(historialActual + entrada);

        mostrarAlerta("Éxito", "Diagnóstico registrado correctamente.");
    }




    @FXML
    void onVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/ConsultorioMedico.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de selección.");
        }
    }

    @FXML
    void onIrADiagnostico(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/uq/Diagnostico.fxml"));
            Parent root = loader.load();

            // Accede al controlador
            DiagnosticoController controller = loader.getController();

            // Pasa el nombre y la especialidad del médico que inició sesión
            controller.inicializarDatosMedico("Dr. Juan Pérez", "Medicina Interna");

            // Muestra la nueva vista
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void inicializarDatosMedico(String nombre, String especialidad) {
        this.nombreMedico = nombre;
        this.especialidadMedico = especialidad;
    }

}
