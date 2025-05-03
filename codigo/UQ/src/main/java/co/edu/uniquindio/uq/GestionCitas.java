package co.edu.uniquindio.uq;

public interface GestionCitas {

    void asignarCita(Paciente paciente, Medico medico, String fechaHora);
    void cancelarCita(Paciente paciente);
    void modificarCita(Paciente paciente, String nuevaFechaHora);
}
