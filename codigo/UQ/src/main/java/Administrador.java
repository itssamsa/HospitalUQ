public class Administrador extends Usuario {

    public Administrador(String nombre, String cedula, String direccion, String telefono) {
        super(nombre, cedula, direccion, telefono);
    }

    public void registrarMedico(Medico medico) {
        System.out.println("Médico registrado: " + medico.getNombre());
    }

    public void registrarPaciente(Paciente paciente) {
        System.out.println("Paciente registrado: " + paciente.getNombre());
    }

    public void generarReporte() {
        System.out.println("Generando reporte de citas médicas.");
    }
}
