public class Administrador extends Usuario {

    private String puesto;
    private String email;

    public Administrador(String nombre, String cedula, String direccion, String telefono, String puesto, String email) {
        super(nombre, cedula, direccion, telefono);
        this.puesto = puesto;
        this.email = email;
    }
    // Getters y Setters

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}