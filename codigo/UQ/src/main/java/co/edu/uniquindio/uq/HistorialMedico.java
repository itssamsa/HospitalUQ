package co.edu.uniquindio.uq;

public class HistorialMedico {

    private String fecha;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;

    public HistorialMedico(String fecha, String diagnostico, String tratamiento, String observaciones) {
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
    }

    // Getters y setters

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // mostrar info

    @Override
    public String toString() {
        return "co.edu.uniquindio.uq.HistorialMedico{" +
                "fecha='" + fecha + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
