package co.edu.uniquindio.uq.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medico extends Usuario {

    private StringProperty especialidad;
    private StringProperty horario;
    private StringProperty password; // Nuevo atributo

    public Medico(String nombre, String cedula, String direccion, String telefono, String especialidad, String horario, String password) {
        super(nombre, cedula, direccion, telefono);
        this.especialidad = new SimpleStringProperty(especialidad);
        this.horario = new SimpleStringProperty(horario);
        this.password = new SimpleStringProperty(password); // Inicialización
    }

    public StringProperty especialidadProperty() {
        return especialidad;
    }

    public String getEspecialidad() {
        return especialidad.get();
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad.set(especialidad);
    }

    public StringProperty horarioProperty() {
        return horario;
    }

    public String getHorario() {
        return horario.get();
    }

    public void setHorario(String horario) {
        this.horario.set(horario);
    }

    // Getters y Setters para password
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
        return nombre + " - " + especialidad.get();
    }
}
