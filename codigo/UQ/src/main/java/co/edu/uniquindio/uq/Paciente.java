package co.edu.uniquindio.uq;

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

    // Métodos del co.edu.uniquindio.uq.Paciente
    public void solicitarCita(Medico medico, String fechaHora) {
        // Lógica para solicitar una cita con un médico
        citasProgramadas = fechaHora;  // Ejemplo simplificado
        System.out.println("Cita solicitada con el Dr. " + medico.getNombre() + " para el " + fechaHora);
    }

    public void cancelarCita() {
        // Lógica para cancelar una cita
        citasProgramadas = "";  // Limpiar la cita programada
        System.out.println("Cita cancelada.");
    }

    public void consultarHistorial() {
        // Lógica para consultar el historial médico
        System.out.println("Historial Médico: " + historialMedico);
    }

    public void actualizarDatosPersonales(String nuevoNombre, String nuevaCedula, String nuevaDireccion, String nuevoTelefono) {
        setNombre(nuevoNombre);
        setCedula(nuevaCedula);
        setDireccion(nuevaDireccion);
        setTelefono(nuevoTelefono);
        System.out.println("Datos personales actualizados.");
    }
//FALTA POR CREAR LA PARTE DE LAS NOTIFICACIONES --PENDIENTE
    public void notificarCita() {
        if (!citasProgramadas.isEmpty()) {
            System.out.println("Recordatorio: Tu cita está programada para el " + citasProgramadas);
        } else {
            System.out.println("No tienes citas programadas.");
        }
    }
}

