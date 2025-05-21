package co.edu.uniquindio.uq.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SistemaHospitalario {

    private static SistemaHospitalario instancia;
    private ObservableList<Paciente> listaPacientes;
    private ObservableList<Cita> listaCitas;
    private ObservableList<Medico> listaMedicos;
    private ObservableList<Administrador> listaAdministradores;
    private ObservableList<Sala> listaSalas;

    // Constructor privado para implementar el patrón Singleton
    private SistemaHospitalario() {
        listaPacientes = FXCollections.observableArrayList();
        listaCitas = FXCollections.observableArrayList();
        listaMedicos = FXCollections.observableArrayList();
        listaAdministradores = FXCollections.observableArrayList();
        listaSalas = FXCollections.observableArrayList();
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

    public ObservableList<Medico> getListaMedicos() {
        return listaMedicos;
    }

    public ObservableList<Administrador> getListaAdministradores() {
        return listaAdministradores;
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
    public boolean registrarMedico(String nombre, String cedula, String direccion, String telefono, String especialidad, String horario, String password) {
        if (existeMedico(cedula)) {
            return false;
        }
        Medico medico = new Medico(nombre, cedula, direccion, telefono, especialidad, horario,password);
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


    // Administradores


    // Verificar si un Administrador ya está registrado (por cédula)
    public boolean existeAdministrador(String cedula) {
        for (Administrador administrador : listaAdministradores) {
            if (administrador.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }


    // Registrar un nuevo Administrador
    public boolean RegistrarAdministrador(String nombre, String cedula, String direccion, String telefono,  String password) {
        if (existeAdministrador(cedula)) {
            return false;
        }
        Administrador administrador = new Administrador(nombre, cedula, direccion, telefono, password);
        agregarAdministrador(administrador);
        return true;
    }


    // agregar un Administrador a la lista
    public void agregarAdministrador(Administrador administrador) {
        listaAdministradores.add(administrador);
    }


    // Obtener la lista de Administradores
    public ObservableList<Administrador> obtenerAdministradores() {
        return listaAdministradores;
    }


    // Buscar un Administrador por nombre
    public Administrador buscarAdministradorPorNombre(String nombre) {
        for (Administrador administrador : listaAdministradores) {
            if (administrador.getNombre().equalsIgnoreCase(nombre)) {
                return administrador;
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

    //GESTION DE LAS SALAS

    // agregar una sala
    public void agregarSala(Sala sala) {
        listaSalas.add(sala);
    }

    // obtener la lista de salas
    public ObservableList<Sala> obtenerSalas() {
        return listaSalas;
    }

    // Verificar si una sala está disponible
    public boolean estaSalaDisponible(String nombreSala, String horario) {
        if (listaSalas == null) {
            listaSalas = FXCollections.observableArrayList();
        }
        return listaSalas.stream().anyMatch(s -> s.getNombreSala().equals(nombreSala) && s.getHorarioSala().equals(horario) && s.getEstadoSala() == EstadoSala.DISPONIBLE);
    }

    // Reservar una sala
    public boolean reservarSala(String nombreSala, String horario) {
        for (Sala sala : listaSalas) {
            if (sala.getNombreSala().equals(nombreSala) && sala.getHorarioSala().equals(horario) && sala.getEstadoSala() == EstadoSala.DISPONIBLE) {
                sala.setEstadoSala(EstadoSala.OCUPADA);
                return true;
            }
        }
        return false;
    }

    // Gestionar una sala
    public void gestionarSalas(String nombreSala, String horario, EstadoSala estado) {
        if (listaSalas == null) {
            listaSalas = FXCollections.observableArrayList();
        }
        Sala nuevaSala = new Sala(nombreSala, horario, estado);
        listaSalas.add(nuevaSala);
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

    public boolean validarLoginMedico(String nombre, String password) {
        Medico medico = buscarMedicoPorNombre(nombre);
        if (medico != null && medico.getPassword().equals(password)) {
            return true;
        }
        return false;
    }


    public boolean validarLoginAdministrador(String nombre, String password) {
        Administrador administrador = buscarAdministradorPorNombre(nombre);
        if (administrador != null && administrador.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
