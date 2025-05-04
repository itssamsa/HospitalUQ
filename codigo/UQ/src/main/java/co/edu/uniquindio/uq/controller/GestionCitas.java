package co.edu.uniquindio.uq.controller;

import co.edu.uniquindio.uq.model.Medico;
import co.edu.uniquindio.uq.model.Paciente;

public interface GestionCitas {

    void asignarCita(Paciente paciente, Medico medico, String fechaHora);
    void cancelarCita(Paciente paciente);
    void modificarCita(Paciente paciente, String nuevaFechaHora);
}
