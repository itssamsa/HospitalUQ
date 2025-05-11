package co.edu.uniquindio.uq.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SistemaHospitalario {

    private static SistemaHospitalario instancia;
    private ObservableList<Paciente> listaPacientes;

    // Constructor privado para implementar el patrón Singleton
    private SistemaHospitalario() {
        listaPacientes = FXCollections.observableArrayList();
    }

    // Método para obtener la instancia única de SistemaHospitalario
    public static SistemaHospitalario getInstance() {
        if (instancia == null) {
            instancia = new SistemaHospitalario();
        }
        return instancia;
    }

    // Método para verificar si un paciente ya existe por su cédula
    public boolean existePaciente(String cedula) {
        return listaPacientes.stream().anyMatch(p -> p.getCedula().equals(cedula));
    }

    // Método para agregar un paciente
    public void agregarPaciente(Paciente paciente) {
        if (!existePaciente(paciente.getCedula())) {
            listaPacientes.add(paciente);
        }
    }

    // Método para actualizar los datos de un paciente
    public void actualizarPaciente(String cedula, String nombre, String nuevaCedula, String direccion, String telefono) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getCedula().equals(cedula)) {
                paciente.setNombre(nombre);
                paciente.setCedula(nuevaCedula);
                paciente.setDireccion(direccion);
                paciente.setTelefono(telefono);
                break;
            }
        }
    }

    // Método para eliminar un paciente
    public void eliminarPaciente(String cedula) {
        listaPacientes.removeIf(p -> p.getCedula().equals(cedula));
    }

    // Método para obtener la lista de pacientes
    public ObservableList<Paciente> obtenerPacientes() {
        return listaPacientes;
    }

    // Método para obtener un paciente por su cédula
    public Paciente obtenerPaciente(String cedula) {
        return listaPacientes.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    public boolean registrarPaciente(String nombre, String cedula, String direccion, String telefono, String historialMedico, String otraInformacion) {
        // Verificar si el paciente ya existe
        if (existePaciente(cedula)) {
            return false; // El paciente ya existe
        }

        // Crear una nueva instancia de Paciente
        Paciente nuevoPaciente = new Paciente(nombre, cedula, direccion, telefono);

        // Si necesitas agregar más información como historial médico, puedes usar setters o el constructor de Paciente
        nuevoPaciente.setHistorialMedico(historialMedico);

        // Registrar el paciente (puedes agregarlo a la lista o base de datos según tu lógica)
        listaPacientes.add(nuevoPaciente);
        return true; // El paciente fue registrado correctamente
    }

}
