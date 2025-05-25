package co.edu.uniquindio.uq.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Paciente extends Usuario {

    private StringProperty historialMedico;
    private StringProperty password;

    public Paciente(String nombre, String cedula, String direccion, String telefono,String password) {
        super(nombre, cedula, direccion, telefono);
        this.historialMedico = new SimpleStringProperty("");
        this.password = new SimpleStringProperty(password);
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
    public String toString() {
        return nombre + " - " + cedula;
    }

}
