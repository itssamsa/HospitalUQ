package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.IRegistroAdmMedicosController;
import co.edu.uniquindio.uq.model.Agenda;
import co.edu.uniquindio.uq.controller.SistemaHospitalario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class RegistroAdmMedicosController implements IRegistroAdmMedicosController {

    private SistemaHospitalario sistemaHospitalario = SistemaHospitalario.getInstance();

    @FXML private TextField txtNombre;
    @FXML private TextField txtCedula;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtPassword;
    @FXML private TextField txtEspecialidad;

    @FXML private Button btnGuardar;
    @FXML private Button btnVolver;

    @FXML private CheckBox chkLunes, chkMartes, chkMiercoles, chkJueves, chkViernes, chkSabado, chkDomingo;
    @FXML private CheckBox chkLunesTurno1, chkLunesTurno2, chkLunesTurno3;
    @FXML private CheckBox chkMartesTurno1, chkMartesTurno2, chkMartesTurno3;
    @FXML private CheckBox chkMiercolesTurno1, chkMiercolesTurno2, chkMiercolesTurno3;
    @FXML private CheckBox chkJuevesTurno1, chkJuevesTurno2, chkJuevesTurno3;
    @FXML private CheckBox chkViernesTurno1, chkViernesTurno2, chkViernesTurno3;
    @FXML private CheckBox chkSabadoTurno1, chkSabadoTurno2, chkSabadoTurno3;
    @FXML private CheckBox chkDomingoTurno1, chkDomingoTurno2, chkDomingoTurno3;

    @FXML
    private void onDiaSeleccionado(ActionEvent event) {
        Object source = event.getSource();

        if (source == chkLunes) {
            boolean seleccionado = chkLunes.isSelected();
            chkLunesTurno1.setDisable(!seleccionado);
            chkLunesTurno2.setDisable(!seleccionado);
            chkLunesTurno3.setDisable(!seleccionado);
        } else if (source == chkMartes) {
            boolean seleccionado = chkMartes.isSelected();
            chkMartesTurno1.setDisable(!seleccionado);
            chkMartesTurno2.setDisable(!seleccionado);
            chkMartesTurno3.setDisable(!seleccionado);
        } else if (source == chkMiercoles) {
            boolean seleccionado = chkMiercoles.isSelected();
            chkMiercolesTurno1.setDisable(!seleccionado);
            chkMiercolesTurno2.setDisable(!seleccionado);
            chkMiercolesTurno3.setDisable(!seleccionado);
        } else if (source == chkJueves) {
            boolean seleccionado = chkJueves.isSelected();
            chkJuevesTurno1.setDisable(!seleccionado);
            chkJuevesTurno2.setDisable(!seleccionado);
            chkJuevesTurno3.setDisable(!seleccionado);
        } else if (source == chkViernes) {
            boolean seleccionado = chkViernes.isSelected();
            chkViernesTurno1.setDisable(!seleccionado);
            chkViernesTurno2.setDisable(!seleccionado);
            chkViernesTurno3.setDisable(!seleccionado);
        } else if (source == chkSabado) {
            boolean seleccionado = chkSabado.isSelected();
            chkSabadoTurno1.setDisable(!seleccionado);
            chkSabadoTurno2.setDisable(!seleccionado);
            chkSabadoTurno3.setDisable(!seleccionado);
        } else if (source == chkDomingo) {
            boolean seleccionado = chkDomingo.isSelected();
            chkDomingoTurno1.setDisable(!seleccionado);
            chkDomingoTurno2.setDisable(!seleccionado);
            chkDomingoTurno3.setDisable(!seleccionado);
        }
    }

    @FXML
    private void onGuardar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String especialidad = txtEspecialidad.getText();
        String password = txtPassword.getText();

        if (nombre.isEmpty() || cedula.isEmpty() || direccion.isEmpty() ||
                telefono.isEmpty() || especialidad.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        diasSeleccionados.clear();
        turnosPorDia.clear();

        agregarDiaYTurnos("Lunes", chkLunes, chkLunesTurno1, chkLunesTurno2, chkLunesTurno3);
        agregarDiaYTurnos("Martes", chkMartes, chkMartesTurno1, chkMartesTurno2, chkMartesTurno3);
        agregarDiaYTurnos("Miércoles", chkMiercoles, chkMiercolesTurno1, chkMiercolesTurno2, chkMiercolesTurno3);
        agregarDiaYTurnos("Jueves", chkJueves, chkJuevesTurno1, chkJuevesTurno2, chkJuevesTurno3);
        agregarDiaYTurnos("Viernes", chkViernes, chkViernesTurno1, chkViernesTurno2, chkViernesTurno3);
        agregarDiaYTurnos("Sábado", chkSabado, chkSabadoTurno1, chkSabadoTurno2, chkSabadoTurno3);
        agregarDiaYTurnos("Domingo", chkDomingo, chkDomingoTurno1, chkDomingoTurno2, chkDomingoTurno3);

        try {
            Agenda agenda = new Agenda(diasSeleccionados, turnosPorDia);
            System.out.println("Agenda generada:\n" + agenda);

            boolean registrado = sistemaHospitalario.registrarMedico(nombre, cedula, direccion, telefono, especialidad, password, agenda);

            if (registrado) {
                mostrarAlerta("Éxito", "Médico registrado correctamente.");
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo registrar el médico.");
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo crear la agenda: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void agregarDiaYTurnos(String dia, CheckBox chkDia, CheckBox turno1, CheckBox turno2, CheckBox turno3) {
        if (chkDia.isSelected()) {
            diasSeleccionados.add(dia);
            List<String> turnos = new ArrayList<>();

            if (turno1.isSelected()) turnos.add("06:00-14:00");
            if (turno2.isSelected()) turnos.add("14:00-22:00");
            if (turno3.isSelected()) turnos.add("22:00-06:00");

            if (!turnos.isEmpty()) {
                // Guardar los turnos separados por coma (o como lo espere tu clase Agenda)
                turnosPorDia.put(dia, String.join(",", turnos));
            }
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

    private void limpiarCampos() {
        txtNombre.clear();
        txtCedula.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        txtPassword.clear();
        txtEspecialidad.clear();
        chkLunes.setSelected(false);
        chkMartes.setSelected(false);
        chkMiercoles.setSelected(false);
        chkJueves.setSelected(false);
        chkViernes.setSelected(false);
        chkSabado.setSelected(false);
        chkDomingo.setSelected(false);

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private final Set<String> diasSeleccionados = new HashSet<>();
    private final Map<String, String> turnosPorDia = new HashMap<>(); // Día -> Hora de inicio del turno

}
