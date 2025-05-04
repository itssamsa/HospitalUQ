package co.edu.uniquindio.uq.model;

public class CitaMedica {

    private Paciente paciente;
    private Medico medico;
    private String fechaHora;
    private EstadoCita estado;

    public CitaMedica(Paciente paciente, Medico medico, String fechaHora) {
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estado = EstadoCita.PENDIENTE;  // pendiente pa no dejar que el estado de la cita sea nulo
    }

    public void confirmarCita() {
        this.estado = EstadoCita.CONFIRMADA;
    }

    public void cancelarCita() {
        this.estado = EstadoCita.CANCELADA;
    }

    public void finalizarCita() {
        this.estado = EstadoCita.FINALIZADA;
    }

    // Getters (no setters pa no modificar)

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    // mostrar la info

    @Override
    public String toString() {
        return "" +
                "co.edu.uniquindio.uq.model.CitaMedica{" +
                "paciente=" + paciente.getNombre() +
                ", medico=" + medico.getNombre() +
                ", fechaHora='" + fechaHora + '\'' +
                ", estado=" + estado +
                '}';
    }
}
