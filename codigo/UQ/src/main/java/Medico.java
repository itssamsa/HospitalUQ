public class Medico extends Usuario implements HistorialMedico {

    private String especialidad;
    private String numeroLicencia;

    public Medico(String nombre, String cedula, String direccion, String telefono, String especialidad, String numeroLicencia) {
        super(nombre, cedula, direccion, telefono);
        this.especialidad = especialidad;
        this.numeroLicencia = numeroLicencia;
    }

    @Override
    public void registrarDiagnostico(String diagnostico) {
        System.out.println("Registrando diagnóstico para el paciente: " + diagnostico);
    }

    @Override
    public void actualizarHistorial(String actualizacion) {
        System.out.println("Actualizando historial médico: " + actualizacion);
    }

    // Getters y setters

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }
}
