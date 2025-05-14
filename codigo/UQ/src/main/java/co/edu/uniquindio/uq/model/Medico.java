package co.edu.uniquindio.uq.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medico extends Usuario {

    private StringProperty especialidad;
    private StringProperty horario;

    public Medico(String nombre, String cedula, String direccion, String telefono, String especialidad, String horario) {
        super(nombre, cedula, direccion, telefono);
        this.especialidad = new SimpleStringProperty(especialidad);
        this.horario = new SimpleStringProperty(horario);
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

    @Override
    public String toString() {
        return nombre + " - " + especialidad.get();
    }
}
