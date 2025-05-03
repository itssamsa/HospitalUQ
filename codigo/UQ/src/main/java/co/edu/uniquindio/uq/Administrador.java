package co.edu.uniquindio.uq;

public class Administrador extends Usuario implements GestionSalas, GestionCitas {

    public Administrador(String nombre, String cedula, String direccion, String telefono) {
        super(nombre, cedula, direccion, telefono);
    }

    // Métodos del co.edu.uniquindio.uq.Administrador
    public void registrarMedico(Medico medico) {
        System.out.println("Médico registrado: " + medico.getNombre());
    }

    public void registrarPaciente(Paciente paciente) {
        System.out.println("co.edu.uniquindio.uq.Paciente registrado: " + paciente.getNombre());
    }

    public void generarReporte() {
        System.out.println("Generando reporte de citas médicas.");
    }

    // metodos de co.edu.uniquindio.uq.GestionSalas
    @Override
    public void asignarSala(Sala sala) {
        // Lógica para asignar la sala
        System.out.println("co.edu.uniquindio.uq.Sala " + sala.getNombre() + " asignada.");
    }

    @Override
    public void liberarSala(Sala sala) {
        // Lógica para liberar la sala
        System.out.println("co.edu.uniquindio.uq.Sala " + sala.getNombre() + " liberada.");
    }

    @Override
    public void cambiarEstadoSala(Sala sala, EstadoSala nuevoEstado) {
        // Lógica para cambiar el estado de la sala
        sala.setEstado(nuevoEstado);
        System.out.println("Estado de la sala " + sala.getNombre() + " cambiado a: " + nuevoEstado);
    }

    // métodos de co.edu.uniquindio.uq.GestionCitas
    @Override
    public void asignarCita(Paciente paciente, Medico medico, String fechaHora) {
        // Lógica para asignar una cita médica
        System.out.println("Cita asignada a " + paciente.getNombre() + " con el médico " + medico.getNombre() + " en: " + fechaHora);
    }

    @Override
    public void cancelarCita(Paciente paciente) {
        // Lógica para cancelar una cita
        System.out.println("Cita de " + paciente.getNombre() + " cancelada.");
    }

    @Override
    public void modificarCita(Paciente paciente, String nuevaFechaHora) {
        // Lógica para modificar la cita
        System.out.println("Cita de " + paciente.getNombre() + " modificada a: " + nuevaFechaHora);
    }
}
