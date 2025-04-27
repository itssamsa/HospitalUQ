public class Paciente extends Usuario {
    private String historialMedico;
    private String citasProgramadas;

    public Paciente(String nombre, String cedula, String direccion, String telefono, String historialMedico) {
        super(nombre, cedula, direccion, telefono);
        this.historialMedico = historialMedico;
        this.citasProgramadas = "";
    }

    // Getters y setters
    public String getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
    }

    public String getCitasProgramadas() {
        return citasProgramadas;
    }

    public void setCitasProgramadas(String citasProgramadas) {
        this.citasProgramadas = citasProgramadas;
    }

    // Métodos específicos de Paciente
    public void solicitarCita(Medico medico, String fechaHora) {
        // Lógica para solicitar una cita con un médico
        citasProgramadas = fechaHora;  // Ejemplo simplificado
    }

    public void cancelarCita() {
        // Lógica para cancelar una cita
        citasProgramadas = "";  // Limpiar la cita programada
    }

    public void consultarHistorial() {
        // Lógica para consultar el historial médico
        System.out.println("Historial Médico: " + historialMedico);
    }
}

