package co.edu.uniquindio.uq.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Agenda {

    private final Map<String, List<String>> disponibilidad; // Día -> Lista de horas disponibles

    private static final int INTERVALO_MINUTOS = 20;
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Constructor tradicional: genera las citas disponibles para cada día según los rangos de turno.
     * Cada rango se expresa como "HH:mm-HH:mm", separados por coma si hay más de uno.
     * Ejemplo: "06:00-14:00,14:00-22:00"
     */
    public Agenda(Set<String> diasSeleccionados, Map<String, String> turnosPorDia) {
        disponibilidad = new HashMap<>();
        for (Map.Entry<String, String> entry : turnosPorDia.entrySet()) {
            String dia = entry.getKey();
            String rangos = entry.getValue(); // Ej: "06:00-14:00,14:00-22:00"
            List<String> citasDelDia = new ArrayList<>();

            for (String rango : rangos.split(",")) {
                String[] partes = rango.split("-");
                if (partes.length == 2) {
                    String horaInicio = partes[0].trim();
                    String horaFin = partes[1].trim();
                    citasDelDia.addAll(generarCitasEntreHoras(horaInicio, horaFin));
                }
            }

            disponibilidad.put(dia, citasDelDia);
        }
    }

    /**
     * Constructor alternativo: recibe directamente un mapa de días con sus horarios disponibles.
     */
    public Agenda(Map<String, List<String>> mapaDisponibilidad) {
        disponibilidad = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : mapaDisponibilidad.entrySet()) {
            disponibilidad.put(entry.getKey(), new ArrayList<>(entry.getValue())); // Copia profunda
        }
    }

    /**
     * Genera una lista de horarios con intervalos de 20 minutos entre la hora de inicio y fin.
     */
    private List<String> generarCitasEntreHoras(String inicio, String fin) {
        List<String> citas = new ArrayList<>();
        LocalTime horaInicio = LocalTime.parse(inicio);
        LocalTime horaFin = LocalTime.parse(fin);

        while (horaInicio.isBefore(horaFin)) {
            citas.add(horaInicio.format(FORMATO_HORA));
            horaInicio = horaInicio.plusMinutes(INTERVALO_MINUTOS);
        }

        return citas;
    }

    public Map<String, List<String>> getDisponibilidad() {
        return disponibilidad;
    }

    public List<String> get(String dia) {
        return disponibilidad.get(dia);
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

    public Set<String> getDiasDisponibles() {
        return disponibilidad.keySet();
    }

    public void setDiasDisponibles(Set<String> diasSeleccionados) {
        disponibilidad.keySet().retainAll(diasSeleccionados);
    }

    public void setHorario(String horario) {

    }

    public void actualizarDisponibilidadDia(String dia, List<String> nuevaDisponibilidad) {
        disponibilidad.put(dia, new ArrayList<>(nuevaDisponibilidad));
    }

}
