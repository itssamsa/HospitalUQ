package co.edu.uniquindio.uq.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalTime;
import java.util.*;

public class Medico extends Usuario {

    private StringProperty especialidad;
    private StringProperty password;

    private List<LocalTime> agendaTurnos; // Nueva agenda

    public Medico(String nombre, String cedula, String direccion, String telefono, String especialidad,  String password) {
        super(nombre, cedula, direccion, telefono);
        this.especialidad = new SimpleStringProperty(especialidad);
        this.password = new SimpleStringProperty(password);
        this.agendaTurnos = generarAgenda(); // Genera automáticamente la agenda
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



    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public List<LocalTime> getAgendaTurnos() {
        return agendaTurnos;
    }

    // Generar 40 turnos de 20 minutos entre 8:00 y 16:00
    private List<LocalTime> generarAgenda() {
        List<LocalTime> turnos = new ArrayList<>();
        LocalTime inicio = LocalTime.of(8, 0);
        for (int i = 0; i < 40; i++) {
            turnos.add(inicio.plusMinutes(i * 20));
        }
        return turnos;
    }

    @Override
    public String toString() {
        return nombre + " - " + especialidad.get();
    }



    public void setAgendaTurnos(String nuevoHorario) {
    }

    public void agendarTurno(LocalTime turno) {
        agendaTurnos.add(turno);
    }

    private Agenda agenda;

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Agenda getAgenda() {
        return agenda;
    }


    public List<String> getTurnosDisponibles() {
        List<String> turnos = new ArrayList<>();
        for (LocalTime turno : agendaTurnos) {
            turnos.add(turno.toString());
        }
        return turnos;
    }

    public void setDiasDisponibles(Set<String> diasSeleccionados) {
        agenda.setDiasDisponibles(diasSeleccionados);
    }

    public void setHorario(String horario) {
        agenda.setHorario(horario);
    }


}
