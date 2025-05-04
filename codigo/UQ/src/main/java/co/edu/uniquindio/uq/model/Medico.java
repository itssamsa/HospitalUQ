package co.edu.uniquindio.uq.model;

public class Medico extends Usuario {

    private String especialidad;
    private String horariosDisponibles;

    public Medico(String nombre, String cedula, String direccion, String telefono, String especialidad, String horariosDisponibles) {
        super(nombre, cedula, direccion, telefono);
        this.especialidad = especialidad;
        this.horariosDisponibles = horariosDisponibles;
    }

    // Getters y setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getHorariosDisponibles() {
        return horariosDisponibles;
    }

    public void setHorariosDisponibles(String horariosDisponibles) {
        this.horariosDisponibles = horariosDisponibles;
    }

    // Métodos específicos de co.edu.uniquindio.uq.model.Medico
    public void accederHistorial(Paciente paciente) {
        // Lógica para acceder al historial médico de un paciente
    }

    public void registrarDiagnostico(Paciente paciente, String diagnostico) {
        // Lógica para registrar el diagnóstico de un paciente
    }

    public void administrarHorarios(String nuevosHorarios) {
        // Lógica para administrar horarios de consulta
    }
}
