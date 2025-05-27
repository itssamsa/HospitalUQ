package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.model.Agenda;
import co.edu.uniquindio.uq.model.Medico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AdministrarHorarioMedicoController {

    @FXML
    private void onDiaSeleccionado(ActionEvent event) {
        // contenido
    }


    // CheckBoxes para los días
    @FXML private CheckBox chkLunes, chkMartes, chkMiercoles, chkJueves, chkViernes, chkSabado, chkDomingo;

    // CheckBoxes para los turnos de cada día
    @FXML private CheckBox chkLunesTurno1, chkLunesTurno2, chkLunesTurno3;
    @FXML private CheckBox chkMartesTurno1, chkMartesTurno2, chkMartesTurno3;
    @FXML private CheckBox chkMiercolesTurno1, chkMiercolesTurno2, chkMiercolesTurno3;
    @FXML private CheckBox chkJuevesTurno1, chkJuevesTurno2, chkJuevesTurno3;
    @FXML private CheckBox chkViernesTurno1, chkViernesTurno2, chkViernesTurno3;
    @FXML private CheckBox chkSabadoTurno1, chkSabadoTurno2, chkSabadoTurno3;
    @FXML private CheckBox chkDomingoTurno1, chkDomingoTurno2, chkDomingoTurno3;

    // Botones
    @FXML private Button btnActualizar, btnVolver;

    // Médico actual cuyo horario se modifica
    private Medico medicoActual;

    // Mapa para relacionar días con sus checkboxes de turnos
    private final Map<String, List<CheckBox>> mapaTurnos = new HashMap<>();


    // Metodo para recibir el médico seleccionado desde la vista anterior
    public void setMedicoActual(Medico medicoSeleccionado) {
        this.medicoActual = medicoSeleccionado;

        if (this.medicoActual != null) {
            cargarAgendaEnVista();
        } else {
            System.err.println("⚠️ medicoActual es null en AdministrarHorarioMedicoController");
        }
    }

    @FXML
    public void initialize() {
        inicializarMapaTurnos();
        asociarEventosDias();
        desactivarTodosLosTurnos();

        btnActualizar.setOnAction(e -> guardarAgenda());
        btnVolver.setOnAction(this::onVolver);
    }


    // Carga la agenda del médico actual en la interfaz
    private void cargarAgendaEnVista() {
        Agenda agenda = medicoActual.getAgenda();
        if (agenda == null) return;

        Map<String, List<String>> disponibilidad = agenda.getDisponibilidad();

        for (String dia : mapaTurnos.keySet()) {
            CheckBox chkDia = obtenerCheckBoxDia(dia);
            List<CheckBox> turnos = mapaTurnos.get(dia);

            if (disponibilidad.containsKey(dia)) {
                chkDia.setSelected(true);
                turnos.forEach(cb -> cb.setDisable(false));

                List<String> horas = disponibilidad.get(dia);

                for (CheckBox turnoCB : turnos) {
                    String rango = obtenerRango(turnoCB); // Ejemplo: "06:00-14:00"
                    boolean seleccionado = horas.stream().anyMatch(h -> estaHoraEnRango(h, rango));
                    turnoCB.setSelected(seleccionado);
                }
            } else {
                chkDia.setSelected(false);
                turnos.forEach(cb -> {
                    cb.setDisable(true);
                    cb.setSelected(false);
                });
            }
        }
    }

    // Verifica si una hora está dentro de un rango horario
    private boolean estaHoraEnRango(String hora, String rango) {
        String[] partes = rango.split("-");
        if (partes.length != 2) return false;

        LocalTime h = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime inicio = LocalTime.parse(partes[0], DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime fin = LocalTime.parse(partes[1], DateTimeFormatter.ofPattern("HH:mm"));

        if (inicio.isBefore(fin)) {
            return !h.isBefore(inicio) && h.isBefore(fin);
        } else {
            // Caso rango nocturno, ej. 22:00-06:00
            return !h.isBefore(inicio) || h.isBefore(fin);
        }
    }


    // Inicializa el mapa de días a lista de turnos (CheckBoxes)
    private void inicializarMapaTurnos() {
        mapaTurnos.put("Lunes", Arrays.asList(chkLunesTurno1, chkLunesTurno2, chkLunesTurno3));
        mapaTurnos.put("Martes", Arrays.asList(chkMartesTurno1, chkMartesTurno2, chkMartesTurno3));
        mapaTurnos.put("Miércoles", Arrays.asList(chkMiercolesTurno1, chkMiercolesTurno2, chkMiercolesTurno3));
        mapaTurnos.put("Jueves", Arrays.asList(chkJuevesTurno1, chkJuevesTurno2, chkJuevesTurno3));
        mapaTurnos.put("Viernes", Arrays.asList(chkViernesTurno1, chkViernesTurno2, chkViernesTurno3));
        mapaTurnos.put("Sábado", Arrays.asList(chkSabadoTurno1, chkSabadoTurno2, chkSabadoTurno3));
        mapaTurnos.put("Domingo", Arrays.asList(chkDomingoTurno1, chkDomingoTurno2, chkDomingoTurno3));
    }

    // Asocia eventos a los checkboxes de días para habilitar/deshabilitar turnos
    private void asociarEventosDias() {
        Map<CheckBox, String> dias = Map.of(
                chkLunes, "Lunes",
                chkMartes, "Martes",
                chkMiercoles, "Miércoles",
                chkJueves, "Jueves",
                chkViernes, "Viernes",
                chkSabado, "Sábado",
                chkDomingo, "Domingo"
        );

        dias.forEach((chkDia, nombreDia) -> chkDia.setOnAction(e -> {
            boolean activo = chkDia.isSelected();
            mapaTurnos.get(nombreDia).forEach(cb -> cb.setDisable(!activo));
        }));
    }

    // Desactiva y deselecciona todos los turnos (al inicio)
    private void desactivarTodosLosTurnos() {
        mapaTurnos.forEach((dia, turnos) -> {
            turnos.forEach(cb -> {
                cb.setDisable(true);
                cb.setSelected(false);
            });
        });
    }

    // Obtiene el CheckBox correspondiente a un día
    private CheckBox obtenerCheckBoxDia(String dia) {
        return switch (dia) {
            case "Lunes" -> chkLunes;
            case "Martes" -> chkMartes;
            case "Miércoles" -> chkMiercoles;
            case "Jueves" -> chkJueves;
            case "Viernes" -> chkViernes;
            case "Sábado" -> chkSabado;
            case "Domingo" -> chkDomingo;
            default -> null;
        };
    }

    // Devuelve el rango horario asociado a un turno
    private String obtenerRango(CheckBox turno) {
        if (turno.getText().contains("06")) return "06:00-14:00";
        if (turno.getText().contains("14")) return "14:00-22:00";
        return "22:00-06:00"; // Por defecto último turno nocturno
    }

    // Guarda la agenda modificada en el objeto medicoActual
    @FXML
    private void guardarAgenda() {
        Set<String> diasSeleccionados = new HashSet<>();
        Map<String, List<String>> turnosPorDia = new HashMap<>();

        for (Map.Entry<String, List<CheckBox>> entry : mapaTurnos.entrySet()) {
            String dia = entry.getKey();
            CheckBox chkDia = obtenerCheckBoxDia(dia);

            if (chkDia != null && chkDia.isSelected()) {
                diasSeleccionados.add(dia);
                List<String> turnos = new ArrayList<>();
                for (CheckBox cb : entry.getValue()) {
                    if (cb.isSelected()) {
                        turnos.add(obtenerRango(cb));
                    }
                }
                if (!turnos.isEmpty()) {
                    turnosPorDia.put(dia, turnos);
                }
            }
        }

        // Actualiza la agenda del médico
        medicoActual.setAgenda(new Agenda(turnosPorDia));
        mostrarAlerta("Éxito", "Agenda actualizada correctamente.");
    }

    // Maneja la acción para volver a la vista anterior
    @FXML
    private void onVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/ConsultorioMedico.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Consultorio Médico");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de selección.");
        }
    }

    // Muestra alertas informativas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
