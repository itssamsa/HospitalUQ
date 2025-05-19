package co.edu.uniquindio.uq.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SistemaHospitalario {

    private static SistemaHospitalario instancia;
    private ObservableList<Paciente> listaPacientes;
    private ObservableList<Cita> listaCitas;
    private ObservableList<Medico> listaMedicos;

    // Constructor privado para implementar el patrón Singleton
    private SistemaHospitalario() {
        listaPacientes = FXCollections.observableArrayList();
        listaCitas = FXCollections.observableArrayList();
        listaMedicos = FXCollections.observableArrayList();
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


    // MÉDICOS


    // Verificar si un médico ya está registrado (por cédula)
    public boolean existeMedico(String cedula) {
        for (Medico medico : listaMedicos) {
            if (medico.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }


    // Registrar un nuevo médico
    public boolean registrarMedico(String nombre, String cedula, String direccion, String telefono, String especialidad, String horario) {
        if (existeMedico(cedula)) {
            return false;
        }
        Medico medico = new Medico(nombre, cedula, direccion, telefono, especialidad, horario);
        agregarMedico(medico);
        return true;
    }


    // Buscar un médico por cédula
    public Medico buscarMedicoPorCedula(String cedula) {
        for (Medico medico : listaMedicos) {
            if (medico.getCedula().equals(cedula)) {
                return medico;
            }
        }
        return null;
    }


    // agregar un médico a la lista
    public void agregarMedico(Medico medico) {
        listaMedicos.add(medico);
    }


    // Obtener la lista de médicos
    public ObservableList<Medico> obtenerMedicos() {
        return listaMedicos;
    }


    // Buscar un médico por nombre
    public Medico buscarMedicoPorNombre(String nombre) {
        for (Medico medico : listaMedicos) {
            if (medico.getNombre().equalsIgnoreCase(nombre)) {
                return medico;
            }
        }
        return null;
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
                    // Filtrar solo las líneas que contienen información de la cita
                    if (cita.startsWith("Cita: ")) {
                        // Agregar la cita completa, incluyendo el horario
                        citas.add(cita);
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

    // Gestión de salas
    public void gestionarSalas(String sala, String horario) {
        System.out.println("Sala: " + sala + " asignada en horario: " + horario);
    }

    // Monitoreo de disponibilidad de médicos
    public void monitorearDisponibilidadMedicos() {
        for (Medico medico : listaMedicos) {
            System.out.println("Médico: " + medico.getNombre() + " - Especialidad: " + medico.getEspecialidad());
        }
    }

    // Asignación de pacientes a médicos
    public void asignarPacienteAMedico(String cedulaPaciente, String cedulaMedico) {
        Paciente paciente = buscarPaciente(cedulaPaciente);
        Medico medico = buscarMedicoPorCedula(cedulaMedico);

        if (paciente != null && medico != null) {
            System.out.println("Asignación exitosa: " + paciente.getNombre() + " -> " + medico.getNombre());
        } else {
            System.out.println("Error en la asignación: paciente o médico no encontrado.");
        }
    }

    // Generación de reportes
    public void generarReporteCitas() {
        for (Cita cita : listaCitas) {
            System.out.println("Cita: " + cita.getCedulaPaciente() + " con el médico: " + cita.getCedulaMedico());
        }
    }

    public void generarReporteOcupacion() {
        System.out.println("Número de médicos disponibles: " + listaMedicos.size());
        System.out.println("Número de pacientes registrados: " + listaPacientes.size());
        System.out.println("Número de citas activas: " + listaCitas.size());
    }
}
