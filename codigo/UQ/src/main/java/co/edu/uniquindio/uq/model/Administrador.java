package co.edu.uniquindio.uq.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Administrador extends Usuario implements IAdministrador {

    private StringProperty password;

    public Administrador(String nombre, String cedula, String direccion, String telefono, String password) {
        super(nombre, cedula, direccion, telefono);
        this.password = new SimpleStringProperty(password);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
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
    public String toString() {
        return  " Admin: " + nombre;
    }
}
