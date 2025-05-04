package co.edu.uniquindio.uq.model;

public class Sala {

    private String nombre;
    private String tipo;
    private EstadoSala estado;

    public Sala(String nombre, String tipo, EstadoSala estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EstadoSala getEstado() {
        return estado;
    }

    public void setEstado(EstadoSala estado) {
        this.estado = estado;
    }

    // Metodo para mostrar información de la sala
    public void mostrarInformacion() {
        System.out.println("co.edu.uniquindio.uq.model.Sala: " + nombre + ", Tipo: " + tipo + ", Estado: " + estado);
    }
}
