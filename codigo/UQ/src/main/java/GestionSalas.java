public interface GestionSalas {
    void asignarSala(Medico medico, String fecha, String hora);
    void liberarSala(String fecha, String hora);
    void consultarDisponibilidadSala(String fecha, String hora);
}
