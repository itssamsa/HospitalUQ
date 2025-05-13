package co.edu.uniquindio.uq.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Paciente extends Usuario {

    private StringProperty historialMedico;

    public Paciente(String nombre, String cedula, String direccion, String telefono) {
        super(nombre, cedula, direccion, telefono);
        this.historialMedico = new SimpleStringProperty("");
    }

    // Getters y Setters para los Property
    public StringProperty nombreProperty() {
        return new SimpleStringProperty(getNombre());
    }

    public StringProperty cedulaProperty() {
        return new SimpleStringProperty(getCedula());
    }

    public StringProperty direccionProperty() {
        return new SimpleStringProperty(getDireccion());
    }

    public StringProperty telefonoProperty() {
        return new SimpleStringProperty(getTelefono());
    }

    public StringProperty historialMedicoProperty() {
        return historialMedico;
    }

    public String getHistorialMedico() {
        return historialMedico.get();
    }

    public void setHistorialMedico(String historialMedico) {
        this.historialMedico.set(historialMedico);
    }

    public void accederHistorial() {
        System.out.println("Accediendo al historial médico: " + getHistorialMedico());
    }
}
