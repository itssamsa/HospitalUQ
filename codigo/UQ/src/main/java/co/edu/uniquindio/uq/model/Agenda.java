package co.edu.uniquindio.uq.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Agenda {

    private final Map<String, List<String>> disponibilidad; // Día -> Lista de horas disponibles

    private static final int TOTAL_CITAS = 40;
    private static final int INTERVALO_MINUTOS = 20;
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Constructor de Agenda. Crea las citas disponibles por cada día y hora de inicio.
     *

     */
    public Agenda(Set<String> diasSeleccionados, Map<String, String> turnosPorDia) {
        disponibilidad = new HashMap<>();
        for (Map.Entry<String, String> entry : turnosPorDia.entrySet()) {
            String dia = entry.getKey();
            String horaInicio = entry.getValue();
            disponibilidad.put(dia, generarCitasDiarias(horaInicio));
        }
    }

    /**
     * Genera una lista de 40 horarios con intervalos de 20 minutos.
     */
    private List<String> generarCitasDiarias(String inicio) {
        List<String> citas = new ArrayList<>();
        LocalTime hora = LocalTime.parse(inicio);
        for (int i = 0; i < TOTAL_CITAS; i++) {
            citas.add(hora.format(FORMATO_HORA));
            hora = hora.plusMinutes(INTERVALO_MINUTOS);
        }
        return citas;
    }

    public Map<String, List<String>> getDisponibilidad() {
        return disponibilidad;
    }

    /**
     * Consulta si una cita específica está disponible para un día.
     */
    public boolean estaDisponible(String dia, String hora) {
        List<String> horas = disponibilidad.get(dia);
        return horas != null && horas.contains(hora);
    }

    /**
     * Marca una cita como ocupada (la elimina de la lista).
     */
    public boolean agendarCita(String dia, String hora) {
        if (estaDisponible(dia, hora)) {
            disponibilidad.get(dia).remove(hora);
            return true;
        }
        return false;
    }

    /**
     * Libera una cita cancelada (la vuelve a agregar a la lista).
     */
    public void liberarCita(String dia, String hora) {
        List<String> horas = disponibilidad.get(dia);
        if (horas != null && !horas.contains(hora)) {
            horas.add(hora);
            horas.sort(Comparator.comparing(h -> LocalTime.parse(h, FORMATO_HORA))); // Ordenar las horas
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Agenda:\n");
        for (Map.Entry<String, List<String>> entry : disponibilidad.entrySet()) {
            sb.append(entry.getKey()).append(" → ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }


}
