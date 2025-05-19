package co.edu.uniquindio.uq.controller;

public interface IAdministrador {


    void gestionarSalas(String sala, String horario);
    void monitorearDisponibilidadMedicos();
    void asignarPaciente(String cedulaPaciente, String cedulaMedico);
    void generarReporteCitas();
    void generarReporteOcupacion();
}
