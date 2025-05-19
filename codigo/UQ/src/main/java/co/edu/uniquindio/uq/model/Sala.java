package co.edu.uniquindio.uq.model;

public class Sala {

    private String horarioSala;
    private EstadoSala estadoSala;
    private String nombreSala;

    public Sala(String nombreSala, String horarioSala, EstadoSala estadoSala) {
        this.nombreSala = nombreSala;
        this.horarioSala = horarioSala;
        this.estadoSala = estadoSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public String getHorarioSala() {
        return horarioSala;
    }

    public void setHorarioSala(String horarioSala) {
        this.horarioSala = horarioSala;
    }

    public EstadoSala getEstadoSala() {
        return estadoSala;
    }

    public void setEstadoSala(EstadoSala estadoSala) {
        this.estadoSala = estadoSala;
    }
}