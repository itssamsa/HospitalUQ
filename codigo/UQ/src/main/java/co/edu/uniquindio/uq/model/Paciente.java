package co.edu.uniquindio.uq.model;


import javafx.beans.property.SimpleStringProperty;


public class Paciente extends Usuario {


    private SimpleStringProperty nombre;
    private SimpleStringProperty cedula;
    private SimpleStringProperty direccion;
    private SimpleStringProperty telefono;
    private SimpleStringProperty historialMedico;
    private SimpleStringProperty citasProgramadas;


    public Paciente(String nombre, String cedula, String direccion, String telefono) {
        super(nombre, cedula, direccion, telefono);
        this.nombre = new SimpleStringProperty(nombre);
        this.cedula = new SimpleStringProperty(cedula);
        this.direccion = new SimpleStringProperty(direccion);
        this.telefono = new SimpleStringProperty(telefono);
        this.historialMedico = new SimpleStringProperty("");
        this.citasProgramadas = new SimpleStringProperty("");
    }


    // Getters y setters con SimpleStringProperty
    public String getNombre() {
        return nombre.get();
    }


    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }


    public SimpleStringProperty nombreProperty() {
        return nombre;
    }


    public String getCedula() {
        return cedula.get();
    }


    public void setCedula(String cedula) {
        this.cedula.set(cedula);
    }


    public SimpleStringProperty cedulaProperty() {
        return cedula;
    }


    public String getDireccion() {
        return direccion.get();
    }


    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }


    public SimpleStringProperty direccionProperty() {
        return direccion;
    }


    public String getTelefono() {
        return telefono.get();
    }


    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }


    public SimpleStringProperty telefonoProperty() {
        return telefono;
    }


    public String getHistorialMedico() {
        return historialMedico.get();
    }


    public void setHistorialMedico(String historialMedico) {
        this.historialMedico.set(historialMedico);
    }


    public String getCitasProgramadas() {
        return citasProgramadas.get();
    }


    public void setCitasProgramadas(String citasProgramadas) {
        this.citasProgramadas.set(citasProgramadas);
    }


    public SimpleStringProperty historialMedicoProperty() {
        return historialMedico;
    }


    public SimpleStringProperty citasProgramadasProperty() {
        return citasProgramadas;
    }


    public void actualizarDatosPersonales(String nuevoNombre, String nuevaCedula, String nuevaDireccion, String nuevoTelefono) {
        setNombre(nuevoNombre);
        setCedula(nuevaCedula);
        setDireccion(nuevaDireccion);
        setTelefono(nuevoTelefono);
        System.out.println("Datos personales actualizados.");
    }


}
