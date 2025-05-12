package co.edu.uniquindio.uq.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SistemaHospitalario {

    private static SistemaHospitalario instancia;
    private ObservableList<Paciente> listaPacientes;


    // Constructor privado para implementar el patrón Singleton
    public SistemaHospitalario() {
        listaPacientes = FXCollections.observableArrayList();
    }

    // Metodo para obtener la instancia única de SistemaHospitalario
    public static SistemaHospitalario getInstance() {
        if (instancia == null) {
            instancia = new SistemaHospitalario();
        }
        return instancia;
    }

    // Metodo para verificar si un paciente ya existe por su cédula
    public boolean existePaciente(String cedula) {
        return listaPacientes.stream().anyMatch(p -> p.getCedula().equals(cedula));
    }

    // Metodo para agregar un paciente
    public void agregarPaciente(Paciente paciente) {
        if (!existePaciente(paciente.getCedula())) {
            listaPacientes.add(paciente);
        }
    }

    // Metodo para actualizar los datos de un paciente
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

    // Metodo para eliminar un paciente
    public void eliminarPaciente(String cedula) {
        listaPacientes.removeIf(p -> p.getCedula().equals(cedula));
    }

    // Metodo para obtener la lista de pacientes
    public ObservableList<Paciente> obtenerPacientes() {
        return listaPacientes;
    }

    // Metodo para obtener un paciente por su cédula
    public Paciente obtenerPaciente(String cedula) {
        return listaPacientes.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    //registrar paciente
    public boolean registrarPaciente(String nombre, String cedula, String direccion, String telefono, String historialMedico) {
        if (existePaciente(cedula)) {
            return false;
        }
        Paciente nuevoPaciente = new Paciente(nombre, cedula, direccion, telefono);
        nuevoPaciente.setHistorialMedico(historialMedico);  // Guardar el historial correctamente
        listaPacientes.add(nuevoPaciente);
        return true;
    }

    public Paciente buscarPaciente(String cedula) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getCedula().equals(cedula)) {
                return paciente;
            }
        }
        return null;
    }

    // metodo consultar el hitorial medico
    public String consultarHistorialMedico(String cedula) {
        Paciente paciente = buscarPaciente(cedula);
        if (paciente != null) {
            System.out.println("Historial del paciente: " + paciente.getHistorialMedico());
            return paciente.getHistorialMedico();
        }
        System.out.println("Historial médico no encontrado.");
        return "Historial médico no encontrado.";
    }


}
