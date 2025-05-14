package co.edu.uniquindio.uq.model;

public class Cita {

    private String especialidad;
    private String medico;
    private String cedulaPaciente;
    private String horario;

    public Cita(String especialidad, String medico, String horario, String cedulaPaciente) {
        this.especialidad = especialidad;
        this.medico = medico;
        this.horario = horario;
        this.cedulaPaciente = cedulaPaciente;
    }


    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Especialidad: " + especialidad + ", Médico: " + medico + ", Horario: " + horario;
    }
}