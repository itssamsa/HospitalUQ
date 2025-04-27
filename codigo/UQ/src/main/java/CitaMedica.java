public class CitaMedica {

    private String paciente;
    private Medico medico;
    private String fechaHora;
    private EstadoCita estado;

    public CitaMedica(String paciente, Medico medico, String fechaHora) {
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estado = EstadoCita.PENDIENTE;  // Estado inicial
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

}
