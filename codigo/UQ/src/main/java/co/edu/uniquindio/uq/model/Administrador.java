package co.edu.uniquindio.uq.model;

import co.edu.uniquindio.uq.controller.IAdministrador;

public class Administrador extends Usuario implements IAdministrador {

    public Administrador(String nombre, String cedula, String direccion, String telefono) {
        super(nombre, cedula, direccion, telefono);
    }

    @Override
    public void gestionarSalas(String sala, String horario) {
        System.out.println("Gestionando sala: " + sala + " en horario: " + horario);
    }

    @Override
    public void monitorearDisponibilidadMedicos() {
        System.out.println("Monitoreando disponibilidad de médicos...");
    }

    @Override
    public void asignarPaciente(String cedulaPaciente, String cedulaMedico) {
        System.out.println("Asignando paciente con cédula " + cedulaPaciente + " al médico con cédula " + cedulaMedico);
    }

    @Override
    public void generarReporteCitas() {
        System.out.println("Generando reporte de citas médicas...");
    }

    @Override
    public void generarReporteOcupacion() {
        System.out.println("Generando reporte de ocupación hospitalaria...");
    }
}
