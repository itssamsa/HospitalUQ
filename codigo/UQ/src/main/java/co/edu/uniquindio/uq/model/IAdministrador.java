package co.edu.uniquindio.uq.model;

public interface IAdministrador {


    void gestionarSalas(String sala, String horario);
    void monitorearDisponibilidadMedicos();
    void asignarPaciente(String cedulaPaciente, String cedulaMedico);
    void generarReporteCitas();
}
