package co.edu.uniquindio.uq.model;

import co.edu.uniquindio.uq.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CRUDPaciente {

    private static ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList();

    public static void agregarPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
    }

    public static ObservableList<Paciente> obtenerPacientes() {
        return listaPacientes;
    }

    public static void eliminarPaciente(Paciente paciente) {
        listaPacientes.remove(paciente);
    }

    public static void actualizarPaciente(Paciente paciente, String nombre, String cedula, String direccion, String telefono) {
        paciente.actualizarDatosPersonales(nombre, cedula, direccion, telefono);
    }
}
