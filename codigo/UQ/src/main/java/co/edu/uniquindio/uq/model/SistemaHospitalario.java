package co.edu.uniquindio.uq.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SistemaHospitalario {

    private static SistemaHospitalario instancia;
    private ObservableList<Paciente> listaPacientes;
    private ObservableList<Cita> listaCitas;

    // Constructor privado para implementar el patrón Singleton
    private SistemaHospitalario() {
        listaPacientes = FXCollections.observableArrayList();
        listaCitas = FXCollections.observableArrayList();
    }

    //  obtener la instancia única de SistemaHospitalario
    public static SistemaHospitalario getInstance() {
        if (instancia == null) {
            instancia = new SistemaHospitalario();
        }
        return instancia;
    }

    // verificar si un paciente ya existe por su cédula
    public boolean existePaciente(String cedula) {
        return listaPacientes.stream().anyMatch(p -> p.getCedula().equals(cedula));
    }

    // agregar un paciente
    public void agregarPaciente(Paciente paciente) {
        if (!existePaciente(paciente.getCedula())) {
            listaPacientes.add(paciente);
        }
    }

    // actualizar los datos de un paciente
    public void actualizarPaciente(String cedula, String nombre, String nuevaCedula, String direccion, String telefono) {
        Paciente paciente = buscarPaciente(cedula);
        if (paciente != null) {
            paciente.setNombre(nombre);
            paciente.setCedula(nuevaCedula);
            paciente.setDireccion(direccion);
            paciente.setTelefono(telefono);
        }
    }

    // eliminar un paciente
    public void eliminarPaciente(String cedula) {
        listaPacientes.removeIf(p -> p.getCedula().equals(cedula));
    }

    // obtener la lista de pacientes
    public ObservableList<Paciente> obtenerPacientes() {
        return listaPacientes;
    }

    // obtener un paciente por su cédula
    public Paciente obtenerPaciente(String cedula) {
        return listaPacientes.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    // registrar un paciente
    public boolean registrarPaciente(String nombre, String cedula, String direccion, String telefono, String historialMedico) {
        if (existePaciente(cedula)) {
            return false;
        }
        Paciente nuevoPaciente = new Paciente(nombre, cedula, direccion, telefono);
        nuevoPaciente.setHistorialMedico(historialMedico);
        listaPacientes.add(nuevoPaciente);
        return true;
    }

    //  buscar un paciente
    public Paciente buscarPaciente(String cedula) {
        return listaPacientes.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    // consultar el historial médico
    public String consultarHistorialMedico(String cedula) {
        Paciente paciente = buscarPaciente(cedula);
        if (paciente != null) {
            return paciente.getHistorialMedico();
        }
        return "Historial médico no encontrado.";
    }

    // registrar una cita
    public boolean registrarCita(Cita cita) {
        Paciente paciente = buscarPaciente(cita.getCedulaPaciente());
        if (paciente != null) {
            listaCitas.add(cita);
            paciente.setHistorialMedico(paciente.getHistorialMedico() + "\n" + "Cita: " + cita);
            return true;
        }
        return false;
    }

    // Metodo para obtener las citas de un paciente
    public ObservableList<Cita> obtenerCitas(String cedula) {
        ObservableList<Cita> citasPaciente = FXCollections.observableArrayList();
        for (Cita cita : listaCitas) {
            if (cita.getCedulaPaciente().equals(cedula)) {
                citasPaciente.add(cita);
            }
        }
        return citasPaciente;
    }

    // Metodo para eliminar una cita específica
    public boolean eliminarCita(Cita cita) {
        return listaCitas.remove(cita);
    }


    // Metodo para obtener todas las citas registradas
    public ObservableList<Cita> obtenerTodasLasCitas() {
        return listaCitas;
    }

    // Obtener detalles de las citas de un paciente
    public ObservableList<String> obtenerDetallesCita(String cedula) {
        Paciente paciente = buscarPaciente(cedula);
        if (paciente != null) {
            ObservableList<String> citas = FXCollections.observableArrayList();
            String historial = paciente.getHistorialMedico();
            if (!historial.isEmpty()) {
                String[] citasArray = historial.split("\n");
                for (String cita : citasArray) {
                    // Filtrar solo las líneas que contienen información de la cita (no el diagnóstico)
                    if (cita.startsWith("Cita: ")) {
                        // Extraer solo la parte que contiene la especialidad, médico y horario
                        String[] partes = cita.split(", Diagnóstico: ");
                        citas.add(partes[0]);  // Solo agregar la parte antes del diagnóstico
                    }
                }
            }
            return citas;
        }
        return FXCollections.observableArrayList();
    }

    // Cancelar una cita específica
    public boolean cancelarCita(String cedula, String cita) {
        Paciente paciente = buscarPaciente(cedula);
        if (paciente != null) {
            String historial = paciente.getHistorialMedico();
            if (historial.contains(cita)) {
                String nuevoHistorial = historial.replace(cita + "\n", "").trim();
                paciente.setHistorialMedico(nuevoHistorial);
                return true;
            }
        }
        return false;
    }
}
